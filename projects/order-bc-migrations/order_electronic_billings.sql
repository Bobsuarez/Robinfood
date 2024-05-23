CREATE TABLE `order_electronic_billings` (
    `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'Consecutivo que almacena las órdenes realizadas',
    `order_id` int unsigned NOT NULL COMMENT 'Id de la orden',
    `store_id` int unsigned NOT NULL COMMENT 'Id de la tienda',
    `store_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Nombre de la tienda',
    `request_payload` json NOT NULL COMMENT 'json de la orden',
    `response_payload` json COMMENT 'respuesta de la ejecucion en el proveedor de facturacion electronica',
    `status_code` int unsigned NOT NULL COMMENT 'codigo de respuesta de la peticion que se le envia al proveedor de facturacion electronica',
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de creación del registro',
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de modificación del registro',
    PRIMARY KEY (`id`),
    KEY `order_electronic_order_id_index` (`order_id`),
    KEY `order_electronic_store_id_index` (`store_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Tabla encargada de almacenar la peticion que se le envia al proveedor de facturacion electronica';
