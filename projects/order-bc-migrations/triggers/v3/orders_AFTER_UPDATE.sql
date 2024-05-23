create trigger orders_AFTER_UPDATE
    after update
    on orders
    for each row
BEGIN

    SET @message :=
            CONCAT('[UPDATE] [After] [orders] order_id -> ', NEW.id, ' NEW.paid -> ', NEW.paid,
                   ' NEW.pos_id -> ', NEW.pos_id,
                   ' NEW.order_number -> ', NEW.order_number, ' NEW.order_invoice_number -> ',
                   NEW.order_invoice_number);

    -- Register consecutive of an order
    INSERT INTO orders.order_invoice_numbers (order_id, order_number, order_invoice_number)
    values (NEW.id, NEW.order_number, NEW.order_invoice_number)
    ON DUPLICATE KEY UPDATE order_number         = NEW.order_number,
                            order_invoice_number = NEW.order_invoice_number;

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
