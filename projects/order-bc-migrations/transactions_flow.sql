CREATE TABLE `transactions_flow`
(
    `id`             int(11)               NOT NULL AUTO_INCREMENT,
	`transaction_id` int(11)      unsigned NOT NULL COMMENT 'Corresponde a la transacción asociada a la orden(Relación virtual tabla transactions DB ORDERS)',
    `flow_id`        bigint(20)                NULL COMMENT 'corresponde al flujo de la transaccion',
    `created_at`     timestamp             NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`     timestamp             NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `transactions_flow_transaction_id_index` (`transaction_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;  