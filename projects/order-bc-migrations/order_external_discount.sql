CREATE TABLE `order_external_discount` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Almacena las órdenes externas realizadas',
  `order_id` int(11) NOT NULL COMMENT 'Identificador de la orden (relación virtual con tabla orders.orders)',
  `value` decimal(13,4) unsigned NOT NULL,
  `type` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `order_external_discount_order_id_index` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
