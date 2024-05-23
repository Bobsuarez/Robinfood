CREATE TABLE `order_delivery_discount` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `order_external_discount_id` int(10) unsigned NOT NULL COMMENT 'Identificador de la orden external (relación virtual con tabla orders.order_external_discount)',
  `value` decimal(13,4) unsigned NOT NULL,
  `percentage` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `order_delivery_discount_order_external_discount_id_index` (`order_external_discount_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
