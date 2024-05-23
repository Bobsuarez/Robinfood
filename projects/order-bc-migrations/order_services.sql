CREATE TABLE `order_services` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Servicios adicionales aplicados a la orden Ej: domicilio',
  `order_id` int(10) unsigned NOT NULL COMMENT 'Id de la orden (Relación virtual tabla -orders- BD ORDERS)',
  `service_id` int(10) unsigned NOT NULL COMMENT 'Id del servicio que se agrega (Relación virtual tabla -services- BD ORDERS)',
  `price_nt` decimal(13,4) unsigned NOT NULL COMMENT 'Precio del servicio sin impuestos',
  `tax_price` decimal(13,4) unsigned NOT NULL COMMENT 'Valor de los impuestos aplicados al servicio',
  `tax_percentage` decimal(10,2) DEFAULT NULL,
  `discount` decimal(13,4) unsigned NOT NULL COMMENT 'Descuento si aplica',
  `total` decimal(13,4) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `order_services_order_id_index` (`order_id`),
  KEY `order_services_service_id_index` (`service_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
