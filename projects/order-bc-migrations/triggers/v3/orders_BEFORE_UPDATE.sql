create trigger orders_BEFORE_UPDATE
    before update
    on orders
    for each row
BEGIN
    DECLARE _invoiceNumber int(10);
    DECLARE _orderNumber int(10);

    DECLARE _currentSequenceInvoiceNumber int(10);
    DECLARE _currentSequenceOrderNumber int(10);
    DECLARE _totalResolution int(10);

    DECLARE _initialInvoiceNumber int(10);
    DECLARE _initialOrderNumber int(10);
    DECLARE _resolutionId int(10);

    DECLARE errorResolutionDoesNotExists CONDITION FOR SQLSTATE '10001';
    DECLARE errorResolutionThereIsMoreThanOneActive CONDITION FOR SQLSTATE '10002';
    SET @messageErrorResolutionDoesNotExists = CONCAT('The resolution does not exists by pos_id ', NEW.pos_id);
    SET @messageErrorResolutionThereIsMoreThanOneActive =
            CONCAT('The pos_id ', NEW.pos_id, ' has more than two active resolutions');

    SET @increment := 1;

    SET @message :=
            CONCAT('[UPDATE] [Before] [orders] order_id -> ', NEW.id, ' OLD.paid -> ', OLD.paid, ' NEW.paid -> ',
                   NEW.paid,
                   ' NEW.pos_id -> ', NEW.pos_id,
                   ' NEW.order_number -> ', NEW.order_number, ' NEW.order_invoice_number -> ',
                   NEW.order_invoice_number);

    IF OLD.paid = 0 AND NEW.paid = 1 THEN

        -- Get resolution_id and initial invoice number and order number from pos_resolution table
        SELECT p.invoice_number_initial, p.order_number_initial, p.resolution_id, COUNT(*)
        INTO _initialInvoiceNumber, _initialOrderNumber, _resolutionId, _totalResolution
        FROM orders.pos_resolutions AS p
        WHERE p.pos_id = NEW.pos_id
          AND p.current = 1
          AND p.dic_status_id = 1;

        IF _resolutionId IS NULL THEN

            -- Throw error if pos resolution doesn't exists
            SET @message = CONCAT(@message, ', Throw error if pos resolution does not exists');

            SIGNAL errorResolutionDoesNotExists
                SET MESSAGE_TEXT = @messageErrorResolutionDoesNotExists;
        END IF;

        IF _totalResolution > 1 THEN

            -- Throw error if there is more than one active resolution
            SET @message = CONCAT(@message, ', Throw error if there is more than one active resolution');

            SIGNAL errorResolutionThereIsMoreThanOneActive
                SET MESSAGE_TEXT = @messageErrorResolutionThereIsMoreThanOneActive;
        END IF;

        SET @message = CONCAT(@message, ' resolution_id -> ', _resolutionId);

        -- Get invoice number and order number from sequence_order_numbers table
        SELECT s.order_invoice_number, s.order_number
        INTO _currentSequenceInvoiceNumber, _currentSequenceOrderNumber
        FROM orders.sequence_order_numbers AS s
        WHERE s.pos_id = NEW.pos_id
            FOR
        UPDATE;

        IF _currentSequenceInvoiceNumber IS NULL THEN

            -- Sequence for pos_id didn't find
            SET @message = CONCAT(@message, ' Sequence for pos_id did not find');
            SET _currentSequenceInvoiceNumber = _initialInvoiceNumber;
            SET _currentSequenceOrderNumber = _initialOrderNumber;
        END IF;

        SET @message =
                CONCAT(@message, ' Invoice number found -> ', _currentSequenceInvoiceNumber, ' Order number found -> ',
                       _currentSequenceOrderNumber);

        -- Set sequences by update row
        SET _invoiceNumber = _currentSequenceInvoiceNumber + @increment;
        SET _orderNumber = _currentSequenceOrderNumber + @increment;

        SET NEW.order_invoice_number = _invoiceNumber;
        SET NEW.order_number = _orderNumber;
        SET NEW.billing_resolution_id = _resolutionId;

        SET @message = CONCAT(@message, ' Values updated order_invoice_number -> ', _invoiceNumber, ' order_number -> ',
                              _orderNumber, ' billing_resolution_id -> ', _resolutionId);

        -- Insert or update sequences
        INSERT INTO orders.sequence_order_numbers (pos_id, order_number, order_invoice_number)
        values (NEW.pos_id, _orderNumber, _invoiceNumber)
        ON DUPLICATE KEY UPDATE order_number         = _orderNumber,
                                order_invoice_number = _invoiceNumber;

    END IF;

    -- Register trigger log
    INSERT INTO sequence_order_number_logs (message, order_id, order_uid, pos_id, order_number,
                                            order_invoice_number)
    VALUES (@message,
            NEW.id,
            NEW.uid,
            NEW.pos_id,
            NEW.order_number,
            NEW.order_invoice_number);
END;