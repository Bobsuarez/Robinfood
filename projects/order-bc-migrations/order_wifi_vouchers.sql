CREATE TABLE `order_wifi_vouchers` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Almacena las órdenes realizadas',
  `order_id` int(10) unsigned NOT NULL COMMENT 'Id de la orden (Relación virtual tabla -orders- BD ORDERS)',
  `voucher` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Voucher asignado a la orden',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `order_wifi_vouchers_order_id_index` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
