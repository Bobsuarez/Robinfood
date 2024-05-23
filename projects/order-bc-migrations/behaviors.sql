CREATE TABLE `behaviors` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Identificador del comportamiento',
    `name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Nombre del comportamiento',
    `description` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Breve descripción del comportamiento',
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
