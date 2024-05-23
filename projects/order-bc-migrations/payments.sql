CREATE TABLE `payments` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `transaction_id` int(10) unsigned NOT NULL COMMENT 'Relación virtual con tabla -transactions- BD ORDERS',
  `payment_method_id` int(10) unsigned NOT NULL COMMENT 'Relación virtual con tabla -payment_methods- BD ORDERS',
  `value` decimal(13,4) unsigned NOT NULL COMMENT 'Valor pagado con el medio de pago',
  `origin_id` int(10) unsigned NOT NULL COMMENT 'Relación virtual con tabla -origins- BD ORDERS',
  PRIMARY KEY (`id`),
  KEY `payments_transaction_id_index` (`transaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
