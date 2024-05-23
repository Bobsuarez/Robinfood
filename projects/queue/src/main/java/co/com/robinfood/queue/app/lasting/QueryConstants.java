package co.com.robinfood.queue.app.lasting;

public class QueryConstants {

    public static final String SEARCH_ALL_ORDERS_BY_RANGE_DATES = """
            WITH filteredorders AS (SELECT o.id
                                    FROM orders.orders o
                                    WHERE o.local_date = __DATE__
                                      AND o.status_id NOT IN (6, 7, 8, 9)
                                      AND o.paid = TRUE),
                 rankedbillings AS (SELECT oeb.id,
                                           oeb.order_id,
                                           oeb.status_code,
                                           oeb.response_payload,
                                           oeb.request_payload,
                                           amount_order.amount                                                AS retry,
                                           ROW_NUMBER() OVER (PARTITION BY oeb.order_id ORDER BY oeb.id DESC) AS rn
                                    FROM order_electronic_billings oeb
                                             INNER JOIN (SELECT oeb2.order_id, COUNT(*) AS amount
                                                         FROM order_electronic_billings oeb2
                                                         GROUP BY order_id) AS amount_order ON oeb.order_id = amount_order.order_id
                                    WHERE oeb.order_id IN (SELECT id FROM filteredorders)
                                      AND oeb.status_code <> 202
                                      AND amount_order.amount <= 3
                                      AND NOT EXISTS(SELECT 1
                                                     FROM order_electronic_billings oeb2
                                                     WHERE oeb2.order_id = oeb.order_id
                                                       AND oeb2.status_code < 400))
            SELECT CONCAT(JSON_ARRAYAGG(JSON_OBJECT(
                    'orderId', order_id,
                    'requestPayload', request_payload,
                    'responsePayload', response_payload,
                    'statusCode', status_code,
                    'retry', retry
                )), '') AS info
            FROM rankedbillings
            WHERE rn = 1;
            """;
}
