CREATE TABLE `order_history` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Almacena la historia de estados por los que pasa la orden',
  `order_id` int(10) unsigned NOT NULL COMMENT 'Id de la orden (Relación virtual tabla -orders- BD ORDERS)',
  `order_status_id` int(10) unsigned NOT NULL COMMENT 'Id del estado asociado a la orden (Relación vietual tabla -order_status- DB ORDERS)',
  `user_id` int(10) unsigned NOT NULL COMMENT 'Usuario que realiza la acción (Relación virtual con users deb BD SGI)',
  `date` date NOT NULL COMMENT 'Fecha en la que se realiza la acción',
  `observation` text COLLATE utf8mb4_unicode_ci COMMENT 'Cualquier comentario referente al cambio de estado',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `order_history_user_id_index` (`user_id`),
  KEY `order_history_order_id_index` (`order_id`),
  KEY `order_history_order_status_id_index` (`order_status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
