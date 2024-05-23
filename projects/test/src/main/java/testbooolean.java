public class testbooolean {

    public static void main(String[] args) {
        validated("https://orderbillnumbergenerator-bc.rf-dev.com/", "jdbc:mysql://ou-db-rw.muydev.com:3306/orders");
        validated("https://orderbillnumbergenerator-bc.rf-dev.com/", "jdbc:mysql://ou-db-ro.muy.com.co:3306/orders");
        validated("https://orderbillnumbergenerator-bc.muy.com.co/", "jdbc:mysql://ou-db-rw.muydev.com:3306/orders");




        validated("https://orderbillnumbergenerator-bc.muy.com.co/", "jdbc:mysql://ou-db-ro.muy.com.co:3306/orders");
    }

    private static void validated(String urString, String mysql) {
        boolean isPROD = urString.contains("muy.com");
        boolean isBDProd = mysql.contains("muy.com");

        if (!(isPROD && isBDProd)) {
            System.out.println("entro");
            return;
        }
        System.out.println("Afuera");
    }
}
