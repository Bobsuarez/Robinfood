CREATE TABLE `payment_method_behaviors` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Identificador del comportamiento del método de pago',
    `payment_method_id` int(10) unsigned NOT NULL COMMENT 'Id del método de pago (Relación virtual tabla -payment_methods- BD ORDERS)',
    `behavior_id` int(10) unsigned NOT NULL COMMENT 'Id del comportamiento (Relación virtual tabla -behaviors- BD ORDERS)',
    PRIMARY KEY (`id`),
    KEY `payment_method_behaviors_paymenth_method_id_index` (`payment_method_id`),
    KEY `payment_method_behaviors_behavior_id_index` (`behavior_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
