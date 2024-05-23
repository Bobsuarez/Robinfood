CREATE TABLE `sequence_order_number_logs` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `message` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `order_id` INT DEFAULT NULL,
  `order_uid` varchar(50) COLLATE utf8mb4_unicode_ci NULL,
  `pos_id` int(11) NOT NULL,
  `order_number` int(11) NOT NULL,
  `order_invoice_number` int(11) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
