CREATE TABLE `order_status` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Estados por los que pasar la orden (NO incluye los de walker)',
  `name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `code` varchar(50) NULL COMMENT 'Este código será usado en los diferentes sistemas para identificar el estado',
  `parent_id` int(10) NULL COMMENT 'Hace referencia al campo id de esta misma tabla, si es un subestado',
  `order` decimal(4, 2) NULL COMMENT 'Orden jerarquico de los estados, funciona para el paso entre estados y su validación',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
