import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;


public class SqsMain {

    public static void main(String[] args) {

        Region region = Region.US_EAST_1; // Reemplaza con tu región

        SecretsManagerClient secretsManagerClient = SecretsManagerClient.builder()
                .region(region)
                .build();

        String secretName = "arn:aws:secretsmanager:us-east-1:853324821382:secret:dev/notification-manager-or/amq-orders-pos-vaBTtX"; // Reemplaza con el nombre de tu secreto

        GetSecretValueRequest secretValueRequest = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();

        GetSecretValueResponse secretValueResponse = secretsManagerClient.getSecretValue(secretValueRequest);

        // Aquí puedes manejar la respuesta del secreto
        String secretString = secretValueResponse.secretString();
        // Utiliza el secreto en tu lógica de aplicación

        // Asegúrate de manejar cualquier excepción que pueda ocurrir durante la obtención del secreto
    }
}
