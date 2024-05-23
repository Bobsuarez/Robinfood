CREATE TABLE `origins` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Almacena los diferentes orígenes desde los que se pueden crear órdenes',
  `color` varchar(45) NOT NULL DEFAULT 'F0F0F0' COMMENT 'Almacena el color hexadecimal para background del origen.',
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `code` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Este código será usado en los diferentes sistemas para identificar el origen',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
