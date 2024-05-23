CREATE TABLE `order_flags` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'flags asociados a una orden',
  `order_id` int(10) unsigned NOT NULL COMMENT 'Id de la orden (Relación virtual tabla -orders- BD ORDERS)',
  `flag` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'nombre del flag asociado a la orden',
  PRIMARY KEY (`id`),
  KEY `order_flags_order_id_index` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
