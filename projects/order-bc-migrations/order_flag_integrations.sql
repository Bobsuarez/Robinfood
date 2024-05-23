CREATE TABLE `order_flag_integrations` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Almacenas las integraciones que estan asociadas a una orden',
  `order_id` int(10) unsigned NOT NULL COMMENT 'Id de la orden (Relación virtual tabla -orders- BD ORDERS)',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `order_flag_pits_order_id_index` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;