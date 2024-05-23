create trigger orders_BEFORE_INSERT
    before insert
    on orders
    for each row
BEGIN

    DECLARE _lastOrderNumber int(10);
    DECLARE _lastInvoiceNumber int(10);
    DECLARE _invoiceNumberEnd int(10);
    DECLARE _resolutionId int(10);
    DECLARE _sequenceExists bool;
    DECLARE _resolutionExists bool;
    DECLARE _lastOrderNumberFromOrdersTable int(10);
    DECLARE _lastInvoiceNumberFromOrdersTable int(10);
    DECLARE _lastOrderDateFromOrdersTable date;
    DECLARE _currentOrderNumberInitial int(10);
    DECLARE _companyId int(10);
    DECLARE _logOrderNumber int(10);
    DECLARE _logOrderInvoiceNumber int(10);

    SET @lastOrderNumber := NULL;
    SET @lastOrderInvoiceNumber := NULL;
    SET @incrementOrderNumber := 1;
    SET @incrementOrderInvoiceNumber := 1;
    SET @message := CONCAT("[INSERT] [orders] order_id -> ", NEW.id, " NEW.paid -> ", NEW.paid, " NEW.pos_id -> ", NEW.pos_id);

    SET _logOrderNumber = 0;
    SET _logOrderInvoiceNumber = 0;

    SET NEW.order_number = 0;
    SET NEW.order_invoice_number = 0;

    -- Consult and block the row associated with the POS, to calculate and avoid the repetition of consecutive ones
    SELECT
        CAST(IFNULL(son.order_number, pr.order_number_initial) AS UNSIGNED),
        CAST(IFNULL(son.order_invoice_number, pr.invoice_number_initial) AS UNSIGNED),
        CAST(invoice_number_end AS UNSIGNED),
        IF(son.id IS NULL, false, true),
        IF(pr.id IS NULL, false, true),
        pr.order_number_initial,
        pr.resolution_id
    INTO
        _lastOrderNumber,
        _lastInvoiceNumber,
        _invoiceNumberEnd,
        _sequenceExists,
        _resolutionExists,
        _currentOrderNumberInitial,
        _resolutionId
    FROM
        orders.pos_resolutions pr
            LEFT JOIN
        orders.sequence_order_numbers son ON pr.pos_id = son.pos_id
    WHERE
            pr.pos_id = NEW.pos_id
      AND pr.current = 1
    LIMIT 1
    FOR UPDATE;

    # Obtain the consecutive numbers of the last order associated with the cash register, to reset the number of orders daily.
    SELECT
        CAST(order_number AS UNSIGNED),
        CAST(order_invoice_number AS UNSIGNED),
        IF(local_date IS NULL OR local_date = '0000-00-00', NOW(), date(local_date)),
        company_id
    INTO
        _lastOrderNumberFromOrdersTable,
        _lastInvoiceNumberFromOrdersTable,
        _lastOrderDateFromOrdersTable,
        _companyId
    FROM
        orders
    WHERE
            pos_id = NEW.pos_id
    ORDER BY id DESC
    LIMIT 1;

    -- If the resolution does not exist, the corresponding message is established
    IF !_resolutionExists THEN
        SET @message = CONCAT(@message, ", There is no resolution with pos_id No. ", new.pos_id);
    END IF;
    SET @lastOrderNumberExists := _lastOrderNumberFromOrdersTable IS NOT NULL AND _lastInvoiceNumberFromOrdersTable IS NOT NULL;

    -- If the consecutive ones exist in the table, sequence_order_numbers takes them and increments them
    IF _sequenceExists THEN
        SET @lastOrderNumber = _lastOrderNumber;
        SET @lastOrderInvoiceNumber = _lastInvoiceNumber;
        SET @message = CONCAT(@message, ", The consecutive ones are taken from the sequence_order_numbers table ");
    END IF;

    -- If there are no concecutives in the sequence_order_numbers table, it takes them from the last order associated with the POS
    IF !_sequenceExists AND @lastOrderNumberExists THEN
        SET @lastOrderNumber = _lastOrderNumberFromOrdersTable;
        SET @lastOrderInvoiceNumber = _lastInvoiceNumberFromOrdersTable;
        SET @message = CONCAT(@message, ", The consecutive ones of the last order associated to the pos");
    END IF;

    -- If there are no executives of the last order associated with the pos, they are taken from the pos_resolution table
    IF !@lastOrderNumberExists AND !_sequenceExists THEN
        SET @lastOrderNumber = _lastOrderNumber;
        SET @lastOrderInvoiceNumber = _lastInvoiceNumber;
        SET @incrementOrderNumber = 0;
        SET @incrementOrderInvoiceNumber = 0;
        SET @message = CONCAT(@message, ", The consecutive ones are taken from the pos_resolutions table and they are not incremented");
    END IF;

    -- In case to be a new day, the orderNumber is reset
    IF _lastOrderDateFromOrdersTable IS NOT NULL AND CURDATE() != _lastOrderDateFromOrdersTable THEN
        SET @lastOrderNumber = _currentOrderNumberInitial;
        SET @message = CONCAT(@message, ", The internal consecutive (order number) of the order is reset, created_at: ");
        SET @message = CONCAT(@message, IFNULL(_lastOrderDateFromOrdersTable, "Not found"));
        SET @message = CONCAT(@message, " - current_date: ");
        SET @message = CONCAT(@message, CURDATE());
    END IF;

    -- Unified order systems (OU)
    IF(NEW.id != 0 AND NEW.enabled_trigger = 1) THEN

        -- Find trigger logs from order and get order_number and order_invoice_number generated in the last execution
        SELECT log.order_number,
               log.order_invoice_number
        INTO _logOrderNumber,
             _logOrderInvoiceNumber
        FROM sequence_order_number_logs log
        WHERE log.order_id = NEW.id
        ORDER BY log.id DESC
        LIMIT 1;

        -- In case to consecutives already generated, then take it from sequence_order_numbers
        IF(_logOrderInvoiceNumber != 0) THEN
            SET NEW.order_invoice_number = @lastOrderInvoiceNumber;
            SET NEW.order_number = @lastOrderNumber;
            SET @message = CONCAT(@message, ", The consecutive ones are taken from sequence_order_numbers table considering trigger logs");
        END IF;

        -- If enter from OU, is paid, and nos exists trigger logs generated from order
        IF(NEW.enabled_trigger = 1 AND NEW.paid AND _logOrderInvoiceNumber = 0) THEN
            SET NEW.order_invoice_number = @lastOrderInvoiceNumber + @incrementOrderInvoiceNumber;
            SET @message = CONCAT(@message, ", The consecutive are incrementing from last order invoice number into OU");
        END IF;

    END IF;

    -- If is a new order and enter from OU
    IF(NEW.id = 0 AND NEW.enabled_trigger = 1) THEN
        SET NEW.order_invoice_number = @lastOrderInvoiceNumber;
        SET NEW.order_number = @lastOrderNumber;
        SET @message = CONCAT(@message, ", The consecutive ones are taken from sequence_order_numbers table from OU");
    END IF;

    SET NEW.order_number = @lastOrderNumber + @incrementOrderNumber;

    -- Legacy systems
    IF(NEW.paid AND NEW.enabled_trigger = 0 AND _logOrderInvoiceNumber = 0) THEN
        SET NEW.order_invoice_number = @lastOrderInvoiceNumber + @incrementOrderInvoiceNumber;
        SET @message = CONCAT(@message, ", The consecutive are incrementing from last order invoice number from legacy systems");
    END IF;

    -- If there are no consecutive numbers in the table sequence_order_numbers insert the resulting consecutive
    IF !_sequenceExists THEN
        INSERT INTO
            sequence_order_numbers (
            pos_id,
            order_number,
            order_invoice_number
        )
        VALUES (
           NEW.pos_id,
           NEW.order_number,
           NEW.order_invoice_number
       );

    -- If the consecutive already exist in the sequence_order_numbers table, it updates the resulting consecutive
    ELSE
        UPDATE sequence_order_numbers
        SET
            order_number = NEW.order_number,
            order_invoice_number = IF(NEW.paid, NEW.order_invoice_number, _lastInvoiceNumber)
        WHERE pos_id = NEW.pos_id;
    END IF;

    -- Update billing_resolution_id into POS
    SET NEW.billing_resolution_id = _resolutionId;

    -- Register trigger log
    INSERT INTO
        sequence_order_number_logs (message, order_id, order_uid, pos_id, order_number, order_invoice_number)
    VALUES (
               @message,
               NEW.id,
               NEW.uid,
               NEW.pos_id,
               NEW.order_number,
               NEW.order_invoice_number
           );
END;