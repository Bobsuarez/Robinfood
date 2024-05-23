CREATE TABLE `order_invoice_numbers` (
  `order_id` int(10) unsigned NOT NULL COMMENT 'Identificador del id de la orden TABLE (order_id)',
  `order_number` int(11) DEFAULT NULL COMMENT 'Consecutivo de la orden',
  `order_invoice_number` int(11) DEFAULT NULL COMMENT 'Consecutivo de la factura según la resolución asignado para el pos o la caja',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_id`),
  KEY `order_invoice_numbers_order_id_index` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;