import java.util.UUID;

public class ValidateUUI {

    public static void main(String[] args) {

        String valor = "5d93fe21-230c-4343-be3b-a3701c2118bd";
        boolean isTemplate = valor.contains("-template");
        if (isTemplate) {
            valor = valor.replace("-template", "");
        }
        UUID uuid = UUID.fromString(valor);

        System.out.println(uuid);


    }
}
