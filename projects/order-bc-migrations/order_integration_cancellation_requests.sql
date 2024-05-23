CREATE TABLE `order_integration_cancellation_requests`(
    `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'identificador de orden de cancelación',
    `integration_id` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Identificador del pedido del lado del tercero',
    `origin_id` int unsigned NOT NULL COMMENT 'Origen de la orden (Relación virtual con tabla -origins- BD ORDERS)',
    `platform_payload` JSON NOT NULL COMMENT 'Json que es enviado por la plataforma acerca de la cancelación',
    `reason` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'razón por la cual se dio la cancelación de la orden',
    `cancel_origin` varchar(255) COLLATE utf8mb4_unicode_ci NULL COMMENT 'origen de la cancelación de la orden',
    `cancel_code_description` varchar(255) COLLATE utf8mb4_unicode_ci NULL COMMENT 'descripción de la cancelación de la orden',
    `store_sku` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'identificador sku de la tienda',
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `oc_origin_idx` (`origin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;