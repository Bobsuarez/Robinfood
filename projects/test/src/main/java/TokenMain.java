import com.fasterxml.jackson.databind.JsonNode;
import org.example.ObjectMapperSingletonUtil;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;

public class TokenMain {

    public static void main(String[] args) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {

        long startTime = System.currentTimeMillis();


        String token = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoic3RvcmUtb3IiLCJhdWQiOiJzZXJ2aWNlIiwic3ViIjoic3RvcmUtb3IudjEiLCJwZXIiOltdLCJqdGkiOiI2YTA1OGM1Yy0zYzIwLTQwZGItYWY4MS02N2Q5NDI3ZDkzZmQiLCJpc3MiOiJzdG9yZS1vci52MSIsImlhdCI6MTY4OTExMTk4MiwiZXhwIjoxNjg5MTEyNTgyfQ.D-MwZBghGBIv1qvxiJuV8nWpxciDjXF7HOIjb8F0MT3eqXwnbOV55H3jtNU4tt8KCrUCbaxxBwDYEnx9TT_XXQ";

        // Dividir el token en sus secciones: header, payload y signature
        String[] jwtParts = token.split("\\.");
        if (jwtParts.length != 3) {
            System.out.println("Token JWT inválido");
            return;
        }

        // Decodificar cada sección Base64 y convertir a String
        String header = new String(Base64.getUrlDecoder().decode(jwtParts[0]), StandardCharsets.UTF_8);
        String payload = new String(Base64.getUrlDecoder().decode(jwtParts[1]), StandardCharsets.UTF_8);
        String signature = jwtParts[2]; // La firma no se decodifica

        JsonNode jsonNode = ObjectMapperSingletonUtil.stringToJsonNode(payload);
        JsonNode dataNode = jsonNode.get("exp");
        System.out.println(dataNode);

        // Imprimir los resultados
        System.out.println("Header: " + header);
        System.out.println("Payload: " + payload);
        System.out.println("Signature: " + signature);

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println(totalTime);
    }

    public static boolean validateToken(String token, PublicKey publicKey)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        String[] parts = token.split("\\.");
        String header = parts[0];
        String payload = parts[1];
        String signature = parts[2];

        // Verificar la firma manualmente
        Signature verifier = Signature.getInstance("SHA256withRSA");
        verifier.initVerify(publicKey);
        verifier.update((header + "." + payload).getBytes());
        boolean isSignatureValid = verifier.verify(Base64.getDecoder().decode(signature));

        // Aquí puedes realizar otras validaciones de acuerdo a tus requisitos, como comprobar la fecha de expiración, emisor, audiencia, etc.

        return isSignatureValid;
    }

    // Método para obtener la clave pública necesaria para la verificación de la firma
    private static PublicKey getPublicKey() {
        // Lógica para obtener la clave pública
        // Ejemplo: Key publicKey = obtainPublicKeyFromKeyStore();
        return null; // Retorna la clave pública apropiada
    }


}
