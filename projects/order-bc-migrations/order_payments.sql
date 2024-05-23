CREATE TABLE `order_payments` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Métodos de pago asociados a la orde (Para poder manejar pago mixto)',
  `order_id` int(10) unsigned NOT NULL COMMENT 'Id de la orden (Relación virtual tabla -orders- BD ORDERS)',
  `origin_id` int(10) unsigned DEFAULT 0 COMMENT 'Relación virtual con tabla -origins- BD ORDERS',
  `payment_method_id` int(10) unsigned NOT NULL COMMENT 'Método de pago asociado (Relación virtual tabla -payment_methods- BD ORDERS)',
  `subtotal` decimal(13,4) unsigned NOT NULL DEFAULT '0.0000' COMMENT 'Subtotal asociado al método de pago',
  `tax` decimal(13,4) unsigned NOT NULL DEFAULT '0.0000' COMMENT 'Impuestos asociados al método de pago',
  `discount` decimal(13,4) unsigned NOT NULL DEFAULT '0.0000' COMMENT 'Descuentos asociados al método de pago',
  `value` decimal(13,4) unsigned NOT NULL COMMENT 'Valor pagado con el método asociado',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `order_payments_order_id_index` (`order_id`),
  KEY `order_payments_payment_method_id_index` (`payment_method_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
