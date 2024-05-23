CREATE TABLE `payment_methods` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Métodos de pago específicos. Ej: Tarjeta Visa, Tarjeta Mastercard, etc',
  `type_payment_method_id` int(10) unsigned NOT NULL COMMENT 'Tipo de métdo de pago asociado (Relación viertual tabla -type_payment_methods- BD ORDERS)',
  `order_flow_print_id` int(11) NOT NULL COMMENT 'Establece si el metodo de pago dependiendo el valor (1 => "Impresion de comanda directa en cocina  para las ordenes de autogestión procesadas por dicho metodo de pago"; 2 => "Que imprima la orden a cocina y caja"; 3 => "Cualquier otro flujo")',
  `status_id` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT 'Estado del método (0:Inactivo, 1:Activo, 2:standby)',
  `name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Nombre del método de pago',
  `name_short` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Nombre corto del método de pago',
  `icon` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `payment_methods_order_flow_print_id_index` (`order_flow_print_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
