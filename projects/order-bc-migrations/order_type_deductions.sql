CREATE TABLE `order_type_deductions` (
`id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Deduccion aplicados a la orden',
`name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Esta columna indica el nombre de la deduccion',
`status` tinyint(2) unsigned NOT NULL DEFAULT '1' COMMENT 'Estado del tipo de deduccion (0:Inactivo, 1:Activo)',
`created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
