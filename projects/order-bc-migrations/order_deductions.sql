CREATE TABLE `order_deductions` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Deducciones aplicadas a la orden',
  `order_id` int(11) unsigned NOT NULL COMMENT 'Id de la orden (Relación virtual tabla orders DB ORDERS)',
  `value` decimal(13,4) unsigned NOT NULL COMMENT 'Valor deducido de la orden',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `orders_deductions_order_id_index` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
