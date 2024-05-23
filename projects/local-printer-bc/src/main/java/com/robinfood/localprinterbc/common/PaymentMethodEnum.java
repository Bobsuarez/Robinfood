package com.robinfood.localprinterbc.common;

public enum PaymentMethodEnum {

    CASH   (1L, "Efectivo", "Dinheiro"),
    POS_DEBIT_CARD   (2L, "Tarjeta débito", "Cartão de débito"),
    POS_CREDIT_CARD   (3L, "Tarjeta crédito", "Cartão de crédito"),
    SELF_DEBIT_CARD   (4L, "Tarjeta débito", "Cartão de débito"),
    SELF_CREDIT_CARD   (5L, "Tarjeta crédito", "Cartão de crédito"),
    PAYU_TRANSACTION   (6L, "PayU", "PayU"),
    FOODCOINS(7L, "Efectivo", "Dinheiro"),
    INTEGRATIONS   (8L, "Integraciones", "Integrações"),
    MERCADO_PAGO   (9L, "Mercadopago", "Mercadopago"),
    NEQUI_PUSH   (10L, "Nequi", "Nequi"),
    DAVIPLATA   (11L, "DaviPlata", "DaviPlata"),
    TPAGA   (12L, "Tpaga","Tpaga"),
    UBER_EATS   (13L, "Uber Eats", "Uber Eats"),
    DOMICILIOS_COM   (14L, "Domicilios.com", "Domicilios.com"),
    RAPPI   (15L, "Rappi", "Rappi"),
    IFOOD   (16L, "Ifood", "Ifood"),
    SIN_DELANTAL   (17L, "Sin delantal", "Sin delantal"),
    DIDI   (18L, "DiDi", "DiDi"),
    JUSTO   (19L, "Justo", "Justo"),
    DIDI_EFECTIVO   (20L, "DiDi Efectivo", "DiDi Dinheiro"),
    UBER_EATS_EFECTIVO   (21L, "Uber Eats Efectivo", "Uber Eats Dinheiro"),
    RAPPI_EFECTIVO   (22L, "Rappi Efectivo", "Rappi Dinheiro"),
    ADYEN_TRANSACTION   (23L, "Adyen Transaction", "Adyen Transaction"),
    PIX   (24L, "Pix", "Pix"),
    VOUCHER   (25L, "Voucher", "Voucher"),
    GIFT_CARD (26L, "Tarjeta Muy", "Cartão MUY");



    private Long id;
    private String name;
    private String nameBR;

    PaymentMethodEnum(long id, String name, String nameBR) {
        this.id = id;
        this.name = name;
        this.nameBR = nameBR;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getNameBR() { return nameBR;}

    public static PaymentMethodEnum findById(Long id) {
        for (PaymentMethodEnum iterable : PaymentMethodEnum.values()) {
            if (iterable.getId().equals(id)) {
                return iterable;
            }
        }
        //By Default
        return PaymentMethodEnum.CASH;
    }
}
