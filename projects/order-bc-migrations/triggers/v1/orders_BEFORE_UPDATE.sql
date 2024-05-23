CREATE TRIGGER `orders`.`orders_BEFORE_UPDATE` BEFORE UPDATE ON `orders` FOR EACH ROW

BEGIN

    DECLARE _currentOrderNumberInSecuence int(11);
    DECLARE _currentOrderInvoiceNumberInSecuence int(11);

    SET @lastOrderNumber := NULL;
    SET @lastOrderInvoiceNumber := NULL;

    SET @incrementOrderNumber := 1;
    SET @incrementOrderInvoiceNumber := 1;

    # Self-management Sync

    # Consult and block the row associated with the POS, to calculate and avoid the repetition of consecutive ones
    SELECT
        CAST(IFNULL(son.order_number, pr.order_number_initial) AS UNSIGNED),
        CAST(IFNULL(son.order_invoice_number, pr.invoice_number_initial) AS UNSIGNED)
    INTO
        _currentOrderNumberInSecuence,
        _currentOrderInvoiceNumberInSecuence
    FROM
        orders.pos_resolutions pr
    LEFT JOIN
        orders.sequence_order_numbers son ON pr.pos_id = son.pos_id
    WHERE
        pr.pos_id = NEW.pos_id
        AND pr.current = 1
    LIMIT 1
    FOR UPDATE;

    #If the conscecutives already exist in the sequence_order_numbers table, it updates the resulting consecutives

    # Update order_number from Self-management Sync
    IF NEW.order_number > _currentOrderNumberInSecuence THEN

        UPDATE sequence_order_numbers
            SET
                order_number = NEW.order_number
        WHERE pos_id = NEW.pos_id;

    END IF;

    # Update order_invoice_number from Self-management Sync
    IF NEW.order_invoice_number > _currentOrderInvoiceNumberInSecuence THEN

        UPDATE sequence_order_numbers
            SET
                order_invoice_number = NEW.order_invoice_number
        WHERE pos_id = NEW.pos_id;

    END IF;

END;