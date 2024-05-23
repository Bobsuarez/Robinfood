package com.robinfood.constants;

public class QueryConstants {

    public static final String SAVE_THIRD = """
            INSERT INTO orders.order_third_parties
            (order_id,
             full_name,
             email,
             document_type,
             document_number,
             phone,
             created_at,
             updated_at)
            VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
            """;
    public static final String SAVE_ELECTRONIC_BILLING = """
            INSERT INTO orders.order_electronic_billings
            (order_id,
             store_id,
             store_name,
             request_payload,
             response_payload,
             status_code,
             created_at,
             updated_at)
            VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
            """;

    private QueryConstants() {
        throw new IllegalStateException("Utility class");
    }
}
