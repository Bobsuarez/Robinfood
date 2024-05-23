CREATE TABLE `order_user_data` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Almacena datos del usuario en el momento de la creación de la orden',
  `order_id` int(10) unsigned NOT NULL COMMENT 'Id de la orden (Relación virtual con -orders- DB ORDERS)',
  `user_id` int(10) unsigned NOT NULL COMMENT 'Id del cliente (Relación virtual con tabla users de SGI)',
  `first_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Nombre del usuario',
  `last_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Apellido del usuario',
  `mobile` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Célular del usuario',
  `email` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'correo del usuario',
  `store_id` int(10) unsigned DEFAULT NULL COMMENT 'Id de la tienda',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `order_user_data_order_id_index` (`order_id`),
  KEY `order_user_data_user_id_index` (`user_id`),
  KEY `order_user_data_store_id_index` (`store_id`),
  KEY `order_user_data_firstName_and_lastName_and_createdAt` (`first_name`,`last_name`,`store_id`,`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
