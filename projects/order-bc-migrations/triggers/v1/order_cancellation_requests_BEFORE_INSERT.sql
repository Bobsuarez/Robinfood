DELIMITER //
CREATE TRIGGER set_origin_and_code_description BEFORE INSERT ON order_integration_cancellation_requests
    FOR EACH ROW
BEGIN
    DECLARE cancel_origin_value VARCHAR(255);
    DECLARE cancel_code_description_value VARCHAR(255);

    IF NEW.origin_id = 8 AND NEW.platform_payload IS NOT NULL THEN
        SET cancel_origin_value = JSON_UNQUOTE(JSON_EXTRACT(NEW.platform_payload, '$.metadata.CANCEL_ORIGIN'));
        SET cancel_code_description_value = JSON_UNQUOTE(JSON_EXTRACT(NEW.platform_payload, '$.metadata.CANCEL_CODE_DESCRIPTION'));

        IF cancel_origin_value IS NOT NULL THEN
            SET NEW.cancel_origin = cancel_origin_value;
        END IF;

        IF cancel_code_description_value IS NOT NULL THEN
            SET NEW.cancel_code_description = cancel_code_description_value;
        END IF;
    END IF;
END//
DELIMITER ;