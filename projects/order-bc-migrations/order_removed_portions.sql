CREATE TABLE `order_removed_portions` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Almacena las órdenes realizadas',
  `order_id` int(10) unsigned NOT NULL COMMENT 'Identificador de la orden (Relación viertual tabla -orders- BD ORDERS)',
  `order_final_product_id` int(10) unsigned NOT NULL COMMENT 'Identificador del producto (Relación virtual tabla -order_final_products- BD ORDERS)',
  `final_product_id` int(10) unsigned NOT NULL COMMENT 'Id del plato (Ej: Muy fresco)(Relación virtual tabla -final_products- BD SGI)',
  `portion_id` int(10) unsigned NOT NULL COMMENT 'Este campo identifica el id de la porción original del plato, tienen una relación virtual con sgi.portions',
  `group_id` int(10) unsigned NOT NULL COMMENT 'Este campo identifica el id del grupo asociado al plato, tienen una relación virtual con sgi.groups',
  `portion_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Este campo identifica el nombre de la porción removida del plato',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `order_removed_portions_order_id_index` (`order_id`),
  KEY `order_removed_portions_order_final_product_id_index` (`order_final_product_id`),
  KEY `order_removed_portions_final_product_id_index` (`final_product_id`),
  KEY `order_removed_portions_portion_id_index` (`portion_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

