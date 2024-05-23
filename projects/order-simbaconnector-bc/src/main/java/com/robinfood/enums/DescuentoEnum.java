package com.robinfood.enums;

public enum DescuentoEnum {
    GENERAL("09", "Descuento General"),
    OTROS("CS01", "Otro Cargo");

    private static final DescuentoEnum[] descuentos;

    static {
        descuentos = values();
    }

    private final String codeDian;

    private final String nameDian;

    DescuentoEnum(String codeDian, String nameDian) {
        this.codeDian = codeDian;
        this.nameDian = nameDian;
    }

    public static DescuentoEnum valueOfDescuento(String codeDian) {
        for (DescuentoEnum descuento : descuentos) {
            if (descuento.codeDian.equals(codeDian)) {
                return descuento;
            }
        }
        throw new IllegalArgumentException("No matching descuento for [" + codeDian + "]");
    }

    public String getCodeDian() {
        return codeDian;
    }

    public String getNameDian() {
        return nameDian;
    }

}
