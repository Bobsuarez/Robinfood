import java.math.BigDecimal;

public class ejemplolVariables2 {

    public static final int DEFAULT_INTEGER_VALUE = 0;

    public static final BigDecimal INITIAL_MARGIN_ERROR = BigDecimal.valueOf(0.0001);
    public static final BigDecimal END_MARGIN_ERROR = BigDecimal.valueOf(0.0005);

    public static void main(String[] args) {

        var errorMarge = new BigDecimal("-100.5800");

        var result = differenceValueWithinRange(errorMarge);

        System.out.println(result);
    }

    private static Boolean differenceValueWithinRange(BigDecimal errorMarge) {
        return (errorMarge.abs().compareTo(INITIAL_MARGIN_ERROR) >= DEFAULT_INTEGER_VALUE
                && errorMarge.abs().compareTo(END_MARGIN_ERROR) <= DEFAULT_INTEGER_VALUE);
    }
}

//        System.out.println(data.abs().compareTo(INITIAL_MARGIN_ERROR));
//        System.out.println(data.abs().compareTo(INITIAL_MARGIN_ERROR) >= DEFAULT_INTEGER_VALUE);
//
//        System.out.println(data.abs().compareTo(END_MARGIN_ERROR));
//        System.out.println(data.abs().compareTo(END_MARGIN_ERROR) <= DEFAULT_INTEGER_VALUE);
//
//        System.out.println((data.abs().compareTo(INITIAL_MARGIN_ERROR) >= DEFAULT_INTEGER_VALUE
//                && data.abs().compareTo(END_MARGIN_ERROR) <= DEFAULT_INTEGER_VALUE));

//Nombre completo: eduardo suarez
//Documento de identidad: 1030643665
//Teléfonos de contacto: 3057304840
//Correo electrónico: eduar.suarez001@gmail.com