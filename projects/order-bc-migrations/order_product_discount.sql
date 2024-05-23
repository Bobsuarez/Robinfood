CREATE TABLE `order_product_discount` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `order_external_discount_id` int(10) unsigned NOT NULL COMMENT 'Identificador de la orden external (relación virtual con tabla orders.order_external_discount)',
  `final_product_size_id` int(10) unsigned NOT NULL,
  `size_id` int(10) unsigned NOT NULL,
  `quantity` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `order_product_discount_order_external_discount_id_index` (`order_external_discount_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
