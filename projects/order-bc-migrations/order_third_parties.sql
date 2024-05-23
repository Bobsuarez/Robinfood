CREATE TABLE `order_third_parties` (
    `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'Consecutivo que almacena las órdenes realizadas',
    `order_id` int unsigned NOT NULL COMMENT 'Id de la orden',
    `full_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Nombre del cliente a facturar',
    `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Email del cliente a facturar',
    `document_type` int NOT NULL COMMENT 'Id del tipo de documento',
    `document_number` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'numero de documento del cliente a facturar',
    `phone` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT 'Telefono del cliente a facturar',
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de creación del registro',
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de modificación del registro',
    `deleted_at` timestamp NULL DEFAULT NULL COMMENT 'Fecha de eliminación del registro',
    PRIMARY KEY (`id`),
    KEY `order_third_parties_order_id_index` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Tabla encargada de almacenar el cliente al que se le va a facturar';
