package com.robinfood.enums;

public enum IdentificationTypeEnum {

    CEDULA_CIUDADANIA(1, 13, "Cédula de ciudadanía"),
    CEDULA_DE_EXTRANJERIA(2, 22, "Cédula de extranjería"),
    NIT(3, 31, "NIT"),
    PASAPORTE(4, 41, "Pasaporte");

    private static final IdentificationTypeEnum[] identificqtions;

    static {
        identificqtions = values();
    }

    private final int identificationId;

    private final int codeDian;

    private final String nameDian;

    IdentificationTypeEnum(int identificationId, int codeDian, String nameDian) {
        this.identificationId = identificationId;
        this.codeDian = codeDian;
        this.nameDian = nameDian;
    }

    public static IdentificationTypeEnum valueOfIdentificationInDian(int identificationId) {
        for (IdentificationTypeEnum identification : identificqtions) {
            if (identification.identificationId == identificationId) {
                return identification;
            }
        }
        throw new IllegalArgumentException("No matching identification for [" + identificationId + "]");
    }

    public int getIdentificationId() {
        return identificationId;
    }

    public int getCodeDian() {
        return codeDian;
    }

    public String getNameDian() {
        return nameDian;
    }
}
