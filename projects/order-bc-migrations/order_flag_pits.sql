CREATE TABLE `order_flag_pits` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Almacenas las órdenes que están asociadas al proyecto pits',
  `order_id` int(10) unsigned NOT NULL COMMENT 'Id de la orden (Relación virtual tabla -orders- BD ORDERS)',
  `car_plate` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Columna almacena el numero de placa de un vehiculo',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `order_flag_pits_order_id_index` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
