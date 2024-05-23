CREATE TRIGGER `orders`.`orders_BEFORE_INSERT` BEFORE INSERT ON `orders` FOR EACH ROW

BEGIN
    DECLARE _lastOrderNumber int(10);
    DECLARE _lastInvoiceNumber int(10);
    DECLARE _invoiceNumberEnd int(10);
    DECLARE _resolutionId int(10);
    DECLARE _secuenceExists bool;
    DECLARE _resolutionExists bool;

    DECLARE _lastOrderNumberFromOrdersTable int(10);
    DECLARE _lastInvoiceNumberFromOrdersTable int(10);
    DECLARE _lastOrderDateFromOrdersTable date;

    SET @lastOrderNumber := NULL;
    SET @lastOrderInvoiceNumber := NULL;

    SET @incrementOrderNumber := 1;
    SET @incrementOrderInvoiceNumber := 1;

    SET @message := "";

    #If the consecutive ones fill null in the insert, the calculation of the consecutive ones is performed
    IF NEW.order_number IS NULL AND NEW.order_invoice_number IS NULL AND NEW.id != 0 THEN

        # Consult and block the row associated with the POS, to calculate and avoid the repetition of consecutive ones
        SELECT
            CAST(IFNULL(son.order_number, pr.order_number_initial) AS UNSIGNED),
            CAST(IFNULL(son.order_invoice_number, pr.invoice_number_initial) AS UNSIGNED),
            CAST(invoice_number_end AS UNSIGNED),
            IF(son.id IS NULL, false, true),
            IF(pr.id IS NULL, false, true),
            pr.resolution_id
        INTO
            _lastOrderNumber,
            _lastInvoiceNumber,
            _invoiceNumberEnd,
            _secuenceExists,
            _resolutionExists,
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
            date(local_date)
        INTO
            _lastOrderNumberFromOrdersTable,
            _lastInvoiceNumberFromOrdersTable,
            _lastOrderDateFromOrdersTable
        FROM
            orders
        WHERE
                pos_id = NEW.pos_id
        ORDER BY id DESC
        LIMIT 1;

        #If the resolution does not exist, the corresponding message is established
        IF !_resolutionExists THEN
            SET @message = CONCAT(@message, ", There is no resolution with pos_id No. ", new.pos_id);
        END IF;

        SET @lastOrderNumberExists := _lastOrderNumberFromOrdersTable IS NOT NULL AND _lastInvoiceNumberFromOrdersTable IS NOT NULL;

        #If the consecutive ones exist in the table, sequence_order_numbers takes them and increments them
        IF _secuenceExists THEN
            SET @lastOrderNumber = _lastOrderNumber;
            SET @lastOrderInvoiceNumber = _lastInvoiceNumber;
            SET @message = CONCAT(@message, ", The consecutive ones are taken from the sequence_order_numbers table ");
        END IF;

        #If there are no concecutives in the sequence_order_numbers table, it takes them from the last order associated with the POS
        IF !_secuenceExists AND @lastOrderNumberExists THEN
            SET @lastOrderNumber = _lastOrderNumberFromOrdersTable;
            SET @lastOrderInvoiceNumber = _lastInvoiceNumberFromOrdersTable;
            SET @message = CONCAT(@message, ", The consecutive ones of the last order associated to the pos");
        END IF;

        #If there are no executives of the last order associated with the pos, they are taken from the pos_resolution table
        IF !@lastOrderNumberExists AND !_secuenceExists THEN
            SET @lastOrderNumber = _lastOrderNumber;
            SET @lastOrderInvoiceNumber = _lastInvoiceNumber;
            SET @incrementOrderNumber = 0;
            SET @incrementOrderInvoiceNumber = 0;
            SET @message = CONCAT(@message, ", The consecutive ones are taken from the pos_resolutions table and they are not incremented");
        END IF;

        #If there is no last order associated with the current date, the internal counter of the order is reset
        IF _lastOrderDateFromOrdersTable IS NOT NULL AND CURDATE() != _lastOrderDateFromOrdersTable THEN
            SET @lastOrderNumber = _lastOrderNumber;
            SET @incrementOrderNumber = 0;
            SET @message = CONCAT(@message, ", The internal consecutive (order number) of the order is reset, created_at: ");
            SET @message = CONCAT(@message, IFNULL(_lastOrderDateFromOrdersTable, "Not found"));
            SET @message = CONCAT(@message, " - current_date: ");
            SET @message = CONCAT(@message, CURDATE());
        END IF;

        SET NEW.order_number =  @lastOrderNumber + @incrementOrderNumber;
        SET NEW.order_invoice_number = @lastOrderInvoiceNumber + @incrementOrderInvoiceNumber;
        SET NEW.billing_resolution_id = _resolutionId;

        #If there are no consecutive numbers in the table sequence_order_numbers insert the resulting consecutive
        IF !_secuenceExists THEN
            INSERT INTO
                sequence_order_numbers (
                pos_id,
                order_number,
                order_invoice_number
            )
            VALUES (
                       new.pos_id,
                       NEW.order_number,
                       NEW.order_invoice_number
                   );
            #If the conscecutives already exist in the sequence_order_numbers table, it updates the resulting consecutives
        ELSE
            UPDATE sequence_order_numbers
            SET
                order_number = NEW.order_number,
                order_invoice_number = NEW.order_invoice_number
            WHERE pos_id = NEW.pos_id;
        END IF;

    ELSE

        # Self-management Sync

        # Consult and block the row associated with the POS, to calculate and avoid the repetition of consecutive ones
        SELECT
            CAST(IFNULL(son.order_number, pr.order_number_initial) AS UNSIGNED),
            CAST(IFNULL(son.order_invoice_number, pr.invoice_number_initial) AS UNSIGNED),
            CAST(invoice_number_end AS UNSIGNED),
            IF(son.id IS NULL, false, true),
            IF(pr.id IS NULL, false, true),
            pr.resolution_id
        INTO
            _lastOrderNumber,
            _lastInvoiceNumber,
            _invoiceNumberEnd,
            _secuenceExists,
            _resolutionExists,
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

        #If the resolution does not exist, the corresponding message is established
        IF !_resolutionExists THEN
            SET @message = CONCAT(@message, ", There is no resolution with pos_id No. ", new.pos_id);
        END IF;

        #If there are no consecutive numbers in the table sequence_order_numbers insert the resulting consecutive
        IF !_secuenceExists THEN
            INSERT INTO
                sequence_order_numbers (
                pos_id,
                order_number,
                order_invoice_number
            )
            VALUES (
                       new.pos_id,
                       NEW.order_number,
                       NEW.order_invoice_number
                   );

        END IF;

        # Update order_number from Self-management Sync
        IF NEW.order_number > _lastOrderNumber THEN

            UPDATE sequence_order_numbers
            SET
                order_number = NEW.order_number
            WHERE pos_id = NEW.pos_id;

            SET @message = "The consecutive of the orden ones are taken from self-management sync ";

        ELSE
            SET @message = "No action was taken, the consecutive numbers will not be calculated";

        END IF;

        # Update order_invoice_number from Self-management Sync
        IF NEW.order_invoice_number > _lastInvoiceNumber THEN

            UPDATE sequence_order_numbers
            SET
                order_invoice_number = NEW.order_invoice_number
            WHERE pos_id = NEW.pos_id;

            SET @message = "The consecutive of the invoice ones are taken from self-management sync ";

        ELSE
            SET @message = "No action was taken, the consecutive numbers will not be calculated ";

        END IF;

        -- Update billing_resolution_id into POS
        SET NEW.billing_resolution_id = _resolutionId;

    END IF;

    IF NEW.order_number IS NOT NULL AND NEW.order_invoice_number IS NOT NULL AND NEW.id != 0 THEN

        # Register trigger log
        INSERT INTO
            sequence_order_number_logs (message, order_uid, pos_id, order_number, order_invoice_number)
        VALUES (
                   @message,
                   NEW.uid,
                   NEW.pos_id,
                   NEW.order_number,
                   NEW.order_invoice_number
               );

    END IF;
END;