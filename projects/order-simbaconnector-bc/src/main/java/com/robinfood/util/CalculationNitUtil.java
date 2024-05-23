package com.robinfood.util;

public class CalculationNitUtil {

    private CalculationNitUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static int getVerificationDigit(String nit) {
        String auxNit = nit;
        auxNit = auxNit.replaceAll("\\s", "");
        auxNit = auxNit.replaceAll(",", "");
        auxNit = auxNit.replaceAll("\\.", "");
        auxNit = auxNit.replaceAll("-", "");

        if (!auxNit.matches("\\d+")) {
            System.out.println("El nit/cédula '" + auxNit + "' no es válido(a).");
            return -1;
        }

        int[] vpri = new int[16];
        int z = auxNit.length();

        vpri[1] = 3;
        vpri[2] = 7;
        vpri[3] = 13;
        vpri[4] = 17;
        vpri[5] = 19;
        vpri[6] = 23;
        vpri[7] = 29;
        vpri[8] = 37;
        vpri[9] = 41;
        vpri[10] = 43;
        vpri[11] = 47;
        vpri[12] = 53;
        vpri[13] = 59;
        vpri[14] = 67;
        vpri[15] = 71;

        int x = 0;
        int y;
        for (int i = 0; i < z; i++) {
            y = Character.getNumericValue(auxNit.charAt(i));
            x += (y * vpri[z - i]);
        }

        y = x % 11;

        return (y > 1) ? 11 - y : y;
    }
}
