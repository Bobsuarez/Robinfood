CREATE TABLE `order_discount_types` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(80) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Contiene el nombre del tipo de descuento',
  `description` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Contiene la descripción del tipo de descuento',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
