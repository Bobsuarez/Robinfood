create trigger orders_BEFORE_INSERT
    before insert
    on orders
    for each row
BEGIN

    BEGIN
        DECLARE _invoiceNumber int(10);
        DECLARE _orderNumber int(10);

        DECLARE _currentSequenceInvoiceNumber int(10);
        DECLARE _currentSequenceOrderNumber int(10);
        DECLARE _totalResolution int(10);

        DECLARE _initialInvoiceNumber int(10);
        DECLARE _initialOrderNumber int(10);
        DECLARE _resolutionId int(10);

        DECLARE _lastOrderSequenceInvoiceNumber int(10);
        DECLARE _lastOrderSequenceOrderNumber int(10);

        DECLARE errorResolutionDoesNotExists CONDITION FOR SQLSTATE '10001';
        DECLARE errorResolutionThereIsMoreThanOneActive CONDITION FOR SQLSTATE '10002';
        DECLARE errorOrderInvoiceNumberDoesNotExists CONDITION FOR SQLSTATE '20001';

        SET @messageErrorResolutionDoesNotExists = CONCAT('The resolution does not exists by pos_id ', NEW.pos_id);
        SET @messageErrorOrderInvoiceNumberDoesNotExists =
                CONCAT('The order invoice number does not exists by order_id ', NEW.id);
        SET @messageErrorResolutionThereIsMoreThanOneActive =
                CONCAT('The pos_id ', NEW.pos_id, ' has more than two active resolutions');

        SET @increment := 1;

        SET NEW.order_number = 0;
        SET NEW.order_invoice_number = 0;

        SET @message :=
                CONCAT('[INSERT] [Before] [orders] order_id -> ', NEW.id, ' NEW.paid -> ', NEW.paid,
                       ' NEW.pos_id -> ', NEW.pos_id,
                       ' NEW.order_number -> ', NEW.order_number, ' NEW.order_invoice_number -> ',
                       NEW.order_invoice_number);

        IF NEW.paid = 1 AND NEW.id = 0 THEN

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
                    CONCAT(@message, ' Invoice number found -> ', _currentSequenceInvoiceNumber,
                           ' Order number found -> ',
                           _currentSequenceOrderNumber);

            -- Set sequences by update row
            SET _invoiceNumber = _currentSequenceInvoiceNumber + @increment;
            SET _orderNumber = _currentSequenceOrderNumber + @increment;

            SET NEW.order_invoice_number = _invoiceNumber;
            SET NEW.order_number = _orderNumber;
            SET NEW.billing_resolution_id = _resolutionId;

            SET @message =
                    CONCAT(@message, ' Values updated order_invoice_number -> ', _invoiceNumber, ' order_number -> ',
                           _orderNumber, ' billing_resolution_id -> ', _resolutionId);

            -- Insert or update sequences
            INSERT INTO orders.sequence_order_numbers (pos_id, order_number, order_invoice_number)
            values (NEW.pos_id, _orderNumber, _invoiceNumber)
            ON DUPLICATE KEY UPDATE order_number         = _orderNumber,
                                    order_invoice_number = _invoiceNumber;
        END IF;

        IF NEW.id != 0 THEN

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

            SET @message =
                    CONCAT(@message, ' Order exists id -> ', NEW.id);

            -- Get consecutive of an order from order_invoice_numbers table
            SELECT oin.order_number, oin.order_invoice_number
            INTO _lastOrderSequenceOrderNumber, _lastOrderSequenceInvoiceNumber
            FROM orders.order_invoice_numbers AS oin
            WHERE oin.order_id = NEW.id;

            IF _lastOrderSequenceInvoiceNumber IS NULL THEN

                -- Throw error if order invoice number doesn't exists
                SET @message =
                        CONCAT(@message, ', Throw error if order invoice number does not exists by order_id ', NEW.id);

                SIGNAL errorOrderInvoiceNumberDoesNotExists
                    SET MESSAGE_TEXT = @messageErrorOrderInvoiceNumberDoesNotExists;
            END IF;

            -- Set sequences by update row
            SET _invoiceNumber = _lastOrderSequenceInvoiceNumber;
            SET _orderNumber = _lastOrderSequenceOrderNumber;

            SET @message =
                    CONCAT(@message, ' Values consecutive of an order order_invoice_number -> ',
                           _lastOrderSequenceInvoiceNumber,
                           ' order_number -> ',
                           _lastOrderSequenceOrderNumber, ' billing_resolution_id -> ', _resolutionId);

            SET NEW.order_invoice_number = _invoiceNumber;
            SET NEW.order_number = _orderNumber;
            SET NEW.billing_resolution_id = _resolutionId;

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
END;