CREATE TABLE `order_final_product_portions` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Almacena los productos finales asociados a la orden, Ej: MUY Paisa',
  `order_id` int(10) unsigned NOT NULL COMMENT 'Id de la orden (Relación virtual con tabla -orders- BD ORDERS)',
  `company_id` int(10) unsigned NOT NULL COMMENT 'Id de la compañía (Relación virtual con companies de BD SGI)',
  `store_id` int(10) unsigned NOT NULL COMMENT 'Id de la tienda (Relación virtual con stores de BD SGI)',
  `group_id` int(10) unsigned NOT NULL COMMENT 'Id del grupo al cual pertenece el producto (Relación virtual con group de BD SGI)',
  `group_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Nombre del grupo',
  `order_final_product_id` int(10) unsigned NOT NULL COMMENT 'Id del producto final al que está asociado (Relación virtual con -order_final_products- DB ORDERS)',
  `group_sku` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'identificador sku del grupo',
  `portion_id` int(10) unsigned NOT NULL DEFAULT '1' COMMENT 'Corresponde al ID de la porción (Relación virtual con tabla -portions- BD SGI)',
  `portion_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Este campo me permite identificar el nombre de la porción agregada',
  `portion_sku` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'identificador sku de la porción',
  `product_id` int(10) unsigned NOT NULL COMMENT 'Id del producto Ej:frijol (Relación virtual con tabla products de SGI)',
  `product_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Nombre del producto procesado Ej: frijol',
  `dic_unit_id` int(10) unsigned NOT NULL COMMENT 'Unidad de medida del producto Ej:Gramos (Relación virtual con tabla dic_units de SGI)',
  `portion_status` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT 'Estado de la porción',
  `effective_sale` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT 'Indica si la venta es efectiva o NO (1 y 0 respectivamente)',
  `units_number` double(8,2) unsigned NOT NULL COMMENT 'Número de unidades del producto Ej: 300g, 500g, 50ml, 3.5g',
  `addition` tinyint(3) unsigned DEFAULT '0' COMMENT 'Indica si el producto es adición o NO (1 y 0 respectivamente)',
  `changed_product` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT 'Indica si el producto es el original asociado al plato o se cambió por otro (0 y 1 respectivamente)',
  `quantity` int(10) unsigned NOT NULL COMMENT 'Cantidad de productos en cuestión',
  `quantity_free` int(10) unsigned NOT NULL DEFAULT 0 COMMENT 'Cantidad de porciones gratis',
  `base_price` decimal(13,4) unsigned NOT NULL COMMENT 'Precio base de los productos',
  `discount` decimal(13,4) NOT NULL,
  `operation_date` date NOT NULL COMMENT 'Fecha real de la compra, no es necesariamente la fecha en la que se inserta en BD',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `order_final_product_portions_order_id_index` (`order_id`),
  KEY `order_final_product_portions_company_id_index` (`company_id`),
  KEY `order_final_product_portions_store_id_index` (`store_id`),
  KEY `order_final_product_portions_order_final_product_id_index` (`order_final_product_id`),
  KEY `order_final_product_portions_product_id_index` (`product_id`),
  KEY `order_final_product_portions_dic_unit_id_index` (`dic_unit_id`),
  KEY `order_final_product_portions_portion_id_index` (`portion_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
