CREATE TABLE `delivery_types` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Contiene los tipos de entrega: Ej: domicilio, en punto y para llevar',
  `name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `is_internal_delivery` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Specifies if this order was made from an internal delivery (Mensajeros urbanos, etc.)',
  `is_integration` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Specifies if this order was made from an external integration (Rappi, UberEats, etc.)',
  `is_on_premise` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Specifies if this order was made from an establishment',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `delivery_types_is_on_premise_index` (`is_on_premise`),
  KEY `delivery_types_is_integration_index` (`is_integration`),
  KEY `delivery_types_is_internal_delivery_index` (`is_internal_delivery`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
