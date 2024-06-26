CREATE TABLE `order_changed_portions` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Almacena las órdenes realizadas',
  `order_id` int(10) unsigned NOT NULL COMMENT 'Identificador de la orden (Relación viertual tabla -orders- BD ORDERS)',
  `order_final_product_portion_id` int(10) unsigned NOT NULL COMMENT 'Identificador de la porción (Relación viertual tabla -order_final_product_portions- BD ORDERS)',
  `original_product_id` int(10) unsigned NOT NULL COMMENT 'Id del producto original asociado a un plato (Ej: frijol rojo)',
  `original_product_name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Nombre del producto original asociado a un plato (Ej: frijol rojo)',
  `changed_product_id` int(10) unsigned NOT NULL COMMENT 'Id del producto por el cual fue cambiado (Ej: Lenteja)',
  `changed_product_name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Nombre del producto por el cual fue cambiado (Ej: Lenteja)',
  `original_portion_id` int(10) unsigned NOT NULL COMMENT 'Este campo identifica el id de la porción original del plato, tienen una relación virtual con sgi.portions',
  `original_portion_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Este campo identifica el nombre de la porción original del plato',
  `changed_portion_id` int(10) unsigned NOT NULL COMMENT 'Este campo identifica el nombre de la porción reemplazada del plato',
  `changed_portion_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Este campo identifica el id de la porción reemplazada del plato, tienen una relación virtual con sgi.portions',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `order_changed_portions_order_id_index` (`order_id`),
  KEY `order_changed_portions_order_final_product_portion_id_index` (`order_final_product_portion_id`),
  KEY `order_changed_portions_original_product_id_index` (`original_product_id`),
  KEY `order_changed_portions_changed_product_id_index` (`changed_product_id`),
  KEY `order_changed_portions_original_portion_id_index` (`original_portion_id`),
  KEY `order_changed_portions_changed_portion_id_index` (`changed_portion_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
