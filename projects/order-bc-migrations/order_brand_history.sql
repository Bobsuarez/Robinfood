CREATE TABLE `order_brand_history` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL COMMENT 'Identificador de la orden (relación virtual con tabla orders.orders)',
  `brand_id` int(11) NOT NULL COMMENT 'Identificador de la marca, relación virtual con tabla sgi.franchises',
  `order_status_id` int(11) NOT NULL COMMENT 'Identificador del estado (Relación virtual con la tabla',
  `user_id` int(11) NOT NULL COMMENT 'Identificador del usuario que realiza el cambio de estado (Relación virtual con la tabla sgi.users)',
  `status_change_date` date NOT NULL COMMENT 'Fecha en la que se realizó el cambio de estado',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha-hora en la que se realizó el cambio de estado',
  PRIMARY KEY (`id`),
  KEY `order_brand_history_order_id_index` (`order_id`),
  KEY `order_brand_history_brand_id_index` (`brand_id`),
  KEY `order_brand_history_order_status_id_index` (`order_status_id`),
  KEY `order_brand_history_user_id_index` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;