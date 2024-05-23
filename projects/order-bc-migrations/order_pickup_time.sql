CREATE TABLE `order_pickup_time` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Almacena los pickup_time de las marcas',
  `store_id` int(10) unsigned NOT NULL COMMENT 'Id de la tienda',
  `pickup_time` BIGINT NOT NULL COMMENT 'Tiempo de entrega de la tienda',
  `brand_id` int(10) unsigned NOT NULL COMMENT 'Id de la marca',
  `print_time` BIGINT NOT NULL COMMENT 'Tiempo de impresion de la marca',
  `transaction_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Id de la transaccion',
  PRIMARY KEY (`id`),
  KEY `order_pickup_time_store_id_index` (`store_id`),
  KEY `order_pickup_time_brand_id_index` (`brand_id`),
  KEY `order_pickup_transaction_id_index` (`transaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;