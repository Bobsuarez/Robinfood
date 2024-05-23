import org.example.dtos.ChangeStatusDTO;

import java.lang.reflect.Field;

public class VAlidarEntytyMain {

    public static boolean validateNullFields(Object entity) throws IllegalAccessException {
        for (Field field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.get(entity) == null) {
                System.out.println("Campo nulo encontrado: " + field.getName());
                return false; // O lanza una excepción o realiza alguna acción apropiada en tu caso
            }
        }
        return true; // Todos los campos son no nulos
    }

    // Ejemplo de uso
    public static void main(String[] args) {
        ChangeStatusDTO entity = ChangeStatusDTO.builder().channelId(1L).build(); // Instancia de tu entidad
        try {
            if (validateNullFields(entity)) {
                System.out.println("Todos los campos no son nulos, la entidad es válida.");
            } else {
                System.out.println("Se encontraron campos nulos en la entidad.");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace(); // Manejo de excepciones apropiado
        }
    }

}
