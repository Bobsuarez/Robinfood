CREATE TABLE `order_fiscal_identifiers` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Almacena los identificadores fiscales asociados a un cliente',
  `transaction_id` int(10) unsigned NOT NULL COMMENT 'Identificador de la transacción (Relación viertual tabla -transactions- BD ORDERS)',
  `identifier` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Identificador fiscal del usuario',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `orders_fiscal_transaction_id_index` (`transaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;