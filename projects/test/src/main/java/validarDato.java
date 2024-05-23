import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Data
class MyClass {
    private String document = "";
}

public class validarDato {

    public static void main(String[] args) {
        String dateTimeString = "2024-04-18T11:27:08.5472815-05:00";

        try {
            // Ajustamos el patrón para manejar la fracción de segundo completa y la zona horaria
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSXXX");
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);

            // Creamos otro formateador para el nuevo formato deseado
            DateTimeFormatter nuevoFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            String formatLocalDateTime = dateTime.format(nuevoFormatter);
            System.out.println(LocalDateTime.parse(formatLocalDateTime));
        } catch (DateTimeParseException e) {
            System.out.println("Error al parsear la fecha y hora: " + e.getMessage());
        }
    }
}


