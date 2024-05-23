CREATE TABLE `services` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Servicios adicionales aplicados a la orden Ej: domicilio',
  `company_id` int(10) unsigned NOT NULL COMMENT 'Id de la compañía (Relación virtual con companies de BD SGI)',
  `type_id` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '1: Servicio con valor fijo, 2: Servicio con valor variable',
  `type_service_id` int(10) unsigned COMMENT 'Id del tipo de servicio (Relación virtual con types_services)',
  `max_value_covered` decimal(13,4) unsigned DEFAULT NULL COMMENT 'Valor máximo cubierto por la plataforma prestadora del servicio Ej: Mensajero Urbanos',
  `name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `price` decimal(13,4) unsigned NOT NULL COMMENT 'Precio del servicio',
  `tax` decimal(13,4) unsigned NOT NULL COMMENT 'Impuesto que se debe aplicar al servicio',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `services_company_id_index` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
