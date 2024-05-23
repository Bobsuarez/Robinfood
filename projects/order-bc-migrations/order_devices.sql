CREATE TABLE `order_devices` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Dispositivo desde el cual se solicita la orden',
  `order_id` int(10) unsigned NOT NULL COMMENT 'Id de la orden (Relación virtual tabla -orders- BD ORDERS)',
  `platform_id` int(10) unsigned NOT NULL COMMENT 'Id de la plataforma desde la que se solició (Relación virtual dic_platforms de SGI)',
  `version` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Versión de la aplicación desde la que se solicitó la orden',
  `ip` varbinary(16) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `order_devices_order_id_index` (`order_id`),
  KEY `order_devices_platform_id_index` (`platform_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
