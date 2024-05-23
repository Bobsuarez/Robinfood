CREATE TABLE `sequence_order_numbers` (
  `pos_id` int(10) unsigned NOT NULL COMMENT 'Identificador del pos o la caja',
  `order_number` int(11) DEFAULT NULL COMMENT 'Consecutivo de la orden',
  `order_invoice_number` int(11) DEFAULT NULL COMMENT 'Consecutivo de la factura según la resolución asignado para el pos o la caja',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`pos_id`),
  KEY `sequence_order_numbers_pos_id_index` (`pos_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
