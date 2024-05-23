CREATE TABLE `order_flag_togo` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Órdenes que el cliente marca como “Recoger” y “Para llevar”',
  `order_id` int(10) unsigned NOT NULL COMMENT 'Identificador de la orden (Relación viertual tabla -orders- BD ORDERS)',
  `status_id` tinyint(3) unsigned NOT NULL COMMENT 'Indica si se eligió o no para llevar (0:NO, 1:SI)',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `order_flag_togo_order_id_index` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
