CREATE TABLE `order_deduction_final_products` (
`id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Deduccion aplicados a la orden',
`final_product_id` int(11) unsigned NOT NULL COMMENT 'Id del producto final (Relación virtual con tabla final_products de DB SGI)',
`value` decimal(13,4) unsigned NOT NULL COMMENT 'Valor deduccion de la orden (o productos finales, etc)',
`order_id` int(11) unsigned NOT NULL COMMENT  'Id de la orden (Relación virtual tabla orders DB ORDERS)',
`created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
`updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`id`),
KEY `order_deduction_order_id_index` (`order_id`),
KEY `order_deduction_order_final_product_id_index` (`final_product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
