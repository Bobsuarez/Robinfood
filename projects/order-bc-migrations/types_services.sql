CREATE TABLE `types_services` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Tipos de servicios adicionales aplicados a la orden',
  `name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Nombre para el tipo de servivios Ej: domicilio',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;