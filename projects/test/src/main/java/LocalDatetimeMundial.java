import java.util.Calendar;
import java.util.TimeZone;

public class LocalDatetimeMundial {


    public static void main(String[] args) {
        System.out.println("\tHoras del Mundo");
        System.out.println("*******************************");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("America/Bogota"));
        System.out.println("Hora en Bogota: " + getHoraFormato(calendar));

    }

    static String getHoraFormato(Calendar cal) {

        System.out.println(cal.get(Calendar.DAY_OF_MONTH));
        System.out.println(cal.get(Calendar.MONTH));
        System.out.println(cal.get(Calendar.YEAR));



        String hora =  cal.get(Calendar.HOUR_OF_DAY)+ ":" +
                cal.get(Calendar.MINUTE) + ":" +
                cal.get(Calendar.MILLISECOND);
        return hora;
    }

}
