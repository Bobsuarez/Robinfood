CREATE TABLE `transactions` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `uuid` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Identificador único de la trasacción',
  `user_id` int(10) unsigned NOT NULL COMMENT 'Identificador del usuario que generó la trasacción',
  `paid` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT 'Indica el estado de la orden. 0:No paga | 1:Pagada | 2:Pendiente',
  `value` decimal(13,4) unsigned NOT NULL COMMENT 'Valor de la trasacción',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `orders_uuid_index` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
