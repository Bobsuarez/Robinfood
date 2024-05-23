CREATE TABLE `order_coupons`
(
    `id`             int(11)      NOT NULL AUTO_INCREMENT,
    `code`           varchar(50)      NOT NULL COMMENT 'Nombre del cupon',
    `coupon_type`    bigint(20)           NULL COMMENT 'corresponde al tipo del cupon',
    `value`          decimal(13,4)    NOT NULL COMMENT 'Corresponde al valor del descuento del cupon',
    `redeemed_id`    varchar(20)          NULL COMMENT 'Corresponde al codigo con que fue redimido el cupon (Relación virtual tabla coupon_codes_redeemed DB COUPONS)',
    `transaction_id` int(11) unsigned NOT NULL COMMENT 'Corresponde a la transacción a la que se le aplica el cupon (Relación virtual tabla transactions DB ORDERS)',
    `created_at`     timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`     timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `order_coupons_transaction_id_index` (`transaction_id`),
    KEY `order_coupons_code_index` (`code`),
    KEY `order_coupons_redeemed_id_index` (`redeemed_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;