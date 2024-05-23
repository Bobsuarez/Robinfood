CREATE TABLE `type_payment_methods` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Tipos de métodos de pago Ej: Efectivo, Tarjeta',
  `status_id` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT 'Estado del tipo de método (0:Inactivo, 1:Activo, 2:standby)',
  `name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Nombre del tipo de método de pago',
  `name_short` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Nombre corto del tipo de método de pago',
  `icon` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
