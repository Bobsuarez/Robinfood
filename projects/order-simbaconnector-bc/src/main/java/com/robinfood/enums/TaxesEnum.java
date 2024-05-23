package com.robinfood.enums;

import java.util.Objects;

public enum TaxesEnum {
    IMPO_CONSUMO(1L, "04", "INC"),
    IVA(2L, "01", "IVA"),
    ZZ(99999L, "ZZ", "No aplica");

    private static final TaxesEnum[] taxes;

    static {
        taxes = values();
    }

    private final Long taxId;

    private final String codeDian;

    private final String nameDian;

    TaxesEnum(Long taxId, String codeDian, String nameDian) {
        this.taxId = taxId;
        this.codeDian = codeDian;
        this.nameDian = nameDian;
    }

    public static TaxesEnum valueOfTaxesInDian(Long taxId) {
        for (TaxesEnum tax : taxes) {
            if (Objects.equals(tax.taxId, taxId)) {
                return tax;
            }
        }

        return ZZ;
    }

    public Long getTaxId() {
        return taxId;
    }

    public String getCodeDian() {
        return codeDian;
    }

    public String getNameDian() {
        return nameDian;
    }

}
