CREATE TABLE `order_address` (
  `order_id` int(10) unsigned NOT NULL COMMENT 'Identificador de la orden (Relación viertual tabla -orders- BD ORDERS)',
  `latitude` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `longitude` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `notes` text COLLATE utf8mb4_unicode_ci COMMENT 'Notas asociadas a la orden',
  `zip_code` text COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Codigo postal de la ubicación actual',
  `transaction_id` int(10) unsigned DEFAULT NULL COMMENT 'Identificador de la transacción',
  `country_id` int(10) unsigned DEFAULT NULL COMMENT 'Identificador del país',
  `city_id` int(10) unsigned DEFAULT NULL COMMENT 'Identificador de la ciudad',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
