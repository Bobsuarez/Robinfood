CREATE TABLE `order_discounts` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Descuentos aplicados a la orden',
  `order_discount_type_id` int(11) DEFAULT NULL COMMENT 'Esta columna indica el tipo del descuento que se aplico, tiene relación con la tabla order_discounts en orders',
  `order_id` int(10) unsigned NOT NULL COMMENT 'Id de la orden (Relación virtual tabla -orders- DB ORDERS)',
  `order_final_product_id` int(10) unsigned DEFAULT NULL COMMENT 'Identificador del producto final (Si corresponde), relación virtual con tabla orders.order_final_products',
  `discount_id` int(10) unsigned NULL COMMENT 'Id del descuento (Relación virtual con discount_codes BD SGI)',
  `discount_value` decimal(13,4) unsigned NOT NULL COMMENT 'Valor descontado de la orden (o productos finales, etc)',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `order_discounts_discount_id_index` (`discount_id`),
  KEY `order_discounts_order_id_index` (`order_id`),
  KEY `order_discounts_order_final_product_id_index` (`order_final_product_id`),
  KEY `order_discounts_order_discount_type_id_index` (`order_discount_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
