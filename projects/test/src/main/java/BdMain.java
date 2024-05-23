import org.example.ObjectMapperSingletonUtil;
import org.example.entities.FlowEventLogsEntity;
import org.example.repository.floweventlogs.FlowEventLogsRepository;

import java.util.Objects;
import java.util.Scanner;

public class BdMain {

//    public static void main(String[] args) {
//        FlowEventLogsRepository  flowEventLogsRepository = new FlowEventLogsRepository();
//
//        FlowEventLogsEntity flowEventLogsEntity = flowEventLogsRepository.searchByEventIdAndFlowId("eventi" , 1L);
//
//        System.out.println(ObjectMapperSingletonUtil.objectToJson(flowEventLogsEntity));
//
//    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese los artículos (escriba 'fin' para finalizar):");

        // Usar un bucle for para leer múltiples líneas de entrada
        for (int i = 1; ; i++) {
            System.out.print("Artículo " + i + ": ");
            String articleEntered = scanner.nextLine();

            if (articleEntered.equalsIgnoreCase("fin")) {
                break; // Salir del bucle si se ingresa 'fin'
            }

            // Realizar alguna acción con el artículo ingresado
            System.out.println("Artículo ingresado: " + articleEntered);
        }

        // Cerrar el Scanner
        scanner.close();
    }

}
