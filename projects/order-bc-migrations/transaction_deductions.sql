CREATE TABLE `transaction_deductions` (
`id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Deduccion aplicados a la orden',
`type_deduction_id` int(11) unsigned NOT NULL COMMENT 'Esta columna indica el tipo de deduccion que se aplico, tiene relación con la tabla order_type_deduction en orders',
`transaction_id` int(11) unsigned NOT NULL COMMENT 'Id de la trasaction (Relación virtual tabla transactions DB ORDERS)',
`value` decimal(13,4) unsigned NOT NULL COMMENT 'Valor deduccion de la orden (o productos finales, etc)',
`created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
`updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`id`),
KEY `order_deduction_deduction_type_id_index` (`type_deduction_id`),
KEY `order_deduction_transaction_id_index` (`transaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
