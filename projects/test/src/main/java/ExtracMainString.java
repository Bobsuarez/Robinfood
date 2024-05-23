public class ExtracMainString {

    private static int dataFinal = 0;

    public static void main(String[] args) {

        buildPronombres();
        buildCONECTORES();
        PREPOSICIONES();
        ADVERBIOS();

    }

    private static void buildPronombres() {
        String jav = "YO/ I/" +
                "TU/YOU/" +
                "ÉL/HE/" +
                "ELLA/SHE/" +
                "ELLO/IT/" +
                "NOSOTROS/WE/" +
                "VOSOTROS/YOU/" +
                "ELLOS/THEY/" +
                "MIO/MINE/" +
                "TUYO/YOUR/" +
                "SUYO/HIS(MASCULINO)/" +
                "SUYO/HER(FEMENINO)/" +
                "NUESTRO/OUR/" +
                "VUESTRO/YOUR/" +
                "ESTE/THIS/" +
                "ESTOS/THESE/" +
                "ESE/THAT/" +
                "ESOS/THOSE/" +
                "AQUEL/THAT/" +
                "AQUELLOS/THOSE";

        var nuevo1 = jav.split("/");
        System.out.println("puedes ayudarme realizando tres oraciones en ingles para principiantes cada PRONOMBRES");

        for (int i = 1; i <= nuevo1.length - 1; i += 2) {
            dataFinal += 1;
            System.out.println("-" + nuevo1[i].toString());
        }
//        System.out.println(dataFinal);

    }

    private static void buildCONECTORES() {
        String jav = "Y/AND/" +
                "QUE/WHAT/" +
                "AUNQUE/ALTHOUGH/" +
                "EN CAMBIO/INSTEAD/" +
                "PERO/BUT/" +
                "POR QUÉ/WHY/" +
                "PUES/WELL/" +
                "POR ESO/FOR THAT/" +
                "POR EJEMPLO/FOR EXAMPLE/" +
                "EN PRIMER LUGAR/FIRST OF ALL/" +
                "POR ÚLTIMO/FINALLY/" +
                "A CONTINUACIÓN/NEXT/" +
                "SIN EMBARGO/HOWEVER/" +
                "DEBIDO A /DUE TO/" +
                "INMEDIATAMENTE/IMMEDIATELY/" +
                "POR UN LADO/ON ONE SIDE/" +
                "POR EL OTRO LADO/ON THE OTHER SIDE/" +
                "EN EFECTO/INDEED/" +
                "EN MI OPINION/IN MY OPINION/" +
                "POR LO TANTO/THEREFORE";

        var nuevo1 = jav.split("/");

        System.out.println("puedes ayudarme realizando tres oraciones en ingles para principiantes cada CONECTORES");

        for (int i = 1; i <= nuevo1.length - 1; i += 2) {
            dataFinal += 1;
            System.out.println("-" + nuevo1[i].toString());
        }
//        System.out.println(dataFinal);
    }

    private static void PREPOSICIONES() {
        String jav = "A/TO/" +
                "CON/WITH/" +
                "CONTRA/AGAINST/" +
                "SEGÚN/ACCORDING TO/" +
                "ENTRE/BETWEEN/" +
                "PARA/FOR/" +
                "BAJO/UNDER/" +
                "SOBRE/ON/" +
                "HACIA/TOWARDS/" +
                "HASTA/UNTIL/" +
                "DESDE/FROM/" +
                "EN/IN/" +
                "SIN/WITHOUT/" +
                "POR/FOR/";

        var nuevo1 = jav.split("/");
        System.out.println("puedes ayudarme realizando tres oraciones en ingles para principiantes cada PREPOSICIONES");

        for (int i = 1; i <= nuevo1.length - 1; i += 2) {
            dataFinal += 1;
            System.out.println("-" + nuevo1[i].toString());
        }
//        System.out.println(dataFinal);
    }

    private static void ADVERBIOS() {
        String jav = "ACTUALMENTE/AT THE/" +
                "MOMENT AHORA/NOW/" +
                "ANOCHE/LAST NIGHT/" +
                "AYER/YESTERDAY/" +
                "DESPUÉS/AFTER/" +
                "HOY/TODAY/" +
                "JAMÁS/NEVER/" +
                "MAÑANA/TOMORROW/" +
                "RÁPIDAMENTE/QUICKLY/" +
                "LENTAMENTE/SLOWLY/" +
                "AQUÍ/HERE/" +
                "ALLÍ/THERE/" +
                "DELANTE/IN FRONT/" +
                "DETRÁS/BEHIND/" +
                "DEBAJO/UNDER/" +
                "ARRIBA/OVER/" +
                "AL LADO/NEXT/" +
                "FUERA/OUT/" +
                "DENTRO/IN/" +
                "MUCHO/A LOT/" +
                "POCO/A FEW/" +
                "DEMASIADO/TOO MUCH/" +
                "UN POCO/A BIT/" +
                "POR SUPUESTO/OFCOURSE/" +
                "EXACTO/EXACTLY/" +
                "SI/YES/" +
                "NO/NO/" +
                "JÁMAS/NEVER/" +
                "SIEMPRE/EVER/" +
                "A VECES/SOMETIMES";

        var nuevo1 = jav.split("/");
        System.out.println("puedes ayudarme realizando tres oraciones en ingles para principiantes cada ADVERBIOS");

        for (int i = 1; i <= nuevo1.length - 1; i += 2) {
            dataFinal += 1;
            System.out.println("-" + nuevo1[i].toString());
        }
//        System.out.println(dataFinal);
    }
}
