CREATE TABLE `order_dates` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Tabla con fechas asociadas a la orden (Marca fecha de eventos específicos)',
  `order_id` int(10) unsigned NOT NULL COMMENT 'Id de la orden (Relación virtual tabla -orders- BD ORDERS)',
  `operation_date` date NOT NULL COMMENT 'Fecha real de la creación de la orden',
  `create_time` time NOT NULL COMMENT 'Hora en la que la orden fue creada',
  `purchase_time` time DEFAULT NULL COMMENT 'Hora en la que la venta fue efectiva',
  `preparation_time` time DEFAULT NULL COMMENT 'Hora en la que la orden pasó a preparación',
  `delivery_time` time DEFAULT NULL COMMENT 'Hora en la que la orden fue entregada',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `order_dates_order_id_index` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
