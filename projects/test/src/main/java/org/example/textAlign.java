package org.example;

import java.util.ArrayList;
import java.util.List;

public class textAlign {
    public static void main(String[] args) {
        String linea1 = "| Id  |Propiedad|Valor Default|Descripción|";
        String linea2 = "|:----|-------------------|----------|-------------------|";
        String linea3 = "| 1 |   ```/config/{app-channel}/server.port```   |8585|Component access port (Devops " +
                "parameter group)|";
        String linea4 = "| 2 |   ```/config/{app-channel}/server.error.include-stacktrace```   |never|Determines " +
                "whether to include the stack trace in server error responses.|";
        String linea5 = "| 3 |   ```/config/{app-channel}/server.error.include-message```   |always|Determines " +
                "whether to include the error message in server error responses.|";

        String linea6 = "| 4 |   ```/config/{app-channel}/jwt-token-prefix```   |--Secret_National--|Token prefix " +
                "(Devops parameter group)|";
        String linea7 = "| 5 |   ```/config/{app-channel}/jwt-token-secret```   |--Secret_National--|Secret for the " +
                "token encrypted (Devops parameter group)|";
        String linea8 = "| 6 |   ```/config/{app-channel}/jwt-token-aud```   |internal|Token type|";
        String linea9 = "| 7 |   ```/config/{app-channel}/jwt-token-mod```   |order_or_localserver|Name and type of " +
                "component|";
        String linea10 = "| 8 |   ```/config/{app-channel}/spring.application.name```   " +
                "|order-or-localserver|Component name|";

        String linea11 = "| 9 |   ```/config/{app-channel}/springdoc.api-docs.path```   |/api-docs|Api docs path|";
        String linea12 = "| 10 |   ```/config/{app-channel}/springdoc.swagger-ui.path```   |/swagger-ui.html|Swagger " +
                "UI path|";
        String linea13 = "| 11 |   ```/config/{app-channel}/springdoc.swagger-ui.operationsSorter```   " +
                "|method|Swagger ordering type|";
        String linea14 = "| 12 |   ```/config/{app-channel}/springdoc.packagesToScan```   |com.robinfood.app" +
                ".controllers|Displayed package on Swagger|";

        String linea15 = "| 13 |   ```/config/{app-channel}/spring.redis.host```   |--Secret_National--|Redis host " +
                "for Spring. (DevOps parameter group)|";
        String linea16 = "| 14 |   ```/config/{app-channel}/spring.redis.port```   |--Secret_National--|Redis port " +
                "for Spring.|";

        String linea17 = "| 15 |   ```/config/{app-channel}/token-to-business-capability.auth-secret```   " +
                "|--Secret_National--|Secret of authentication for the business capacity token. (DevOps parameter " +
                "group)|";
        String linea18 = "| 16 |   ```/config/{app-channel}/token-to-business-capability.redis-id```   " +
                "|service-token|Redis ID for the business capacity token.|";
        String linea19 = "| 17 |   ```/config/{app-channel}/token-to-business-capability.issuer```   |backoffice" +
                ".v1|Business capacity token issuer.|";
        String linea20 = "| 18 |   ```/config/{app-channel}/token-to-business-capability.auth-key```   " +
                "|--Secret_National--|Authentication key for business capacity token. (DevOps parameter group)|";
        String linea21 = "| 19 |   ```/config/{app-channel}/logging.level.com.amazonaws.util.EC2MetadataUtils```   " +
                "|error|Sets the logging level for the EC2MetadataUtils class in the AWS SDK|";
        String linea22 = "| 20 |   ```/config/{app-channel}/logging.level.com.amazonaws.internal" +
                ".InstanceMetadataServiceResourceFetcher```   |error|Sets the logging level for the " +
                "InstanceMetadataServiceResourceFetcher class in the AWS SDK.|";

        String linea23 = "| 21 |   ```/config/{app-channel}/cloud.aws.region.use-default-aws-region-chain```   " +
                "|true|Determines if the default AWS region chain should be used.|";
        String linea24 = "| 22 |   ```/config/{app-channel}/cloud.aws.region.static```   |us-east-2|Sets the static " +
                "AWS region for cloud-based services.|";
        String linea25 = "| 23 |   ```/config/{app-channel}/cloud.aws.region.auto```   |false|Enables automatic AWS " +
                "region detection for cloud-based services.|";
        String linea26 = "| 24 |   ```/config/{app-channel}/cloud.aws.stack.auto```   |false|Enables automatic AWS " +
                "stack detection for cloud-based services.|";
        String linea27 = "| 25 |   ```/config/{app-channel}/cloud.aws.stack```   |false|Specifies the AWS stack name " +
                "for cloud-based services.|";
        String linea28 = "| 26 |   ```/config/{app-channel}/cloud.aws.credentials.access-key```   " +
                "|--Secret_National--|Sets the AWS access key for cloud-based services.|";
        String linea29 = "| 27 |   ```/config/{app-channel}/cloud.aws.credentials.secret-key```   " +
                "|--Secret_National--|Sets the AWS secret key for cloud-based services.|";
        String linea30 = "| 28 |   ```/config/{app-channel}/cloud.aws.end-point.url```   " +
                "|--Secret_National--|Specifies the AWS endpoint URL for cloud-based services.|";

        String linea31 = "| 29 |   ```/config/{app-channel}/elastic.apm.enabled```   |true|Elastic APM authorization " +
                "indicator.              |";
        String linea32 = "| 30 |   ```/config/{app-channel}/elastic.apm.server-url```   |--Secret_National--|Elastic " +
                "server url (Devops parameter group)       |";
        String linea33 = "| 31 |   ```/config/{app-channel}/elastic.apm.service-name```   |order-or-localserver|Name " +
                "of the Elastic Apm service.                  |";
        String linea34 = "| 32 |   ```/config/{app-channel}/elastic.apm.secret-token```   " +
                "|--Secret_National--|Elastic Secret Token (Devops parameter group)     |";
        String linea35 = "| 33 |   ```/config/{app-channel}/elastic.apm.environment```   |dev|Elastic APM environment" +
                ".                          |";
        String linea36 = "| 34 |   ```/config/{app-channel}/elastic.apm.application-packages```   |com.robinfood" +
                ".order-or-localserver|Application packages for elastic APM.             |";
        String linea37 = "| 35 |   ```/config/{app-channel}/elastic.apm.log-level```   |INFO|Elastic APM registration" +
                " level.                   |";

        String linea38 = "| 36 |   ```/config/{app-channel}/activemq-orders.broker-url```   |--Secret_National--|URL " +
                "of the ActiveMQ corridor for Spring.|";
        String linea39 = "| 37 |   ```/config/{app-channel}/activemq-orders.user```   |--Secret_National--|ActiveMQ " +
                "user for Spring.               |";
        String linea40 = "| 38 |   ```/config/{app-channel}/activemq-orders.password```   " +
                "|--Secret_National--|ActiveMQ password for Spring.           |";

        String linea41 = "| 39 |   ```/config/{app-channel}/activemq.orders-created.topic```   |Rf.All.Order" +
                ".OrderCreated.VirtualTopic|ActiveMQ theme for created orders          |";
        String linea42 = "| 40 |   ```/config/{app-channel}/spring.activemq.in-memory```   |false|ActiveMQ in spring " +
                "memory.  |";
        String linea43 = "| 41 |   ```/config/{app-channel}/spring.jms.pub-sub-domain```   |true|Sets the JMS pub-sub" +
                " domain for Spring applications.|";
        String linea44 = "| 42 |   ```/config/{app-channel}/activemq.paid-orders.client_id```   " +
                "|order-or-localserver_SERVER_PRUEBAS|Customer ID for orders paid in ActiveMQ.  |";

        String linea45 = "| 43 |   ```/config/{app-channel}/spring.cloud.compatibility-verifier.enabled```   " +
                "|false|Compatibility between the versions of the Spring Cloud dependencies|";
        String linea46 = "| 44 |   ```/config/{app-channel}/spring.mvc.pathmatch.matching-strategy```   " +
                "|ANT_PATH_MATCHER|Configuration related to the route coincidence strategy|";

        String linea47 = "| 45 |   ```/config/{app-channel}/feign.client.url.loyaltyBcUrl```   |https://dev.loyalty" +
                ".muydev.com/api/|Sets the URL for the loyaltyBcUrl Feign client.|";
        String linea48 = "| 46 |   ```/config/{app-channel}/feign.client.url.orderBcUrl```   |https://dev" +
                ".order-bc-query.muydev.com/api/|Sets the URL for the orderBcUrl Feign client.|";
        String linea49 = "| 47 |   ```/config/{app-channel}/feign.client.url.ssoUrl```   |https://sso.muydev" +
                ".com/|Sets the URL for the ssoUrl Feign client.|";

        String linea50 = "| 48 |   ```/config/{app-channel}/logging.level.com.robinfood.orderorlocalserver.network```" +
                "   |DEBUG|Sets the logging level for the com.robinfood.orderorlocalserver.network package.|";

        String linea51 = "| 49 |   ```/config/{app-channel}/logging.level.root```   |INFO|Sets the logging level for " +
                "the root package.|";

        String linea52 = "| 50 |   ```/config/{app-channel}/spring.main.allow-bean-definition-overriding```   " +
                "|true|Allows overriding of bean definitions in Spring applications.|";

        String linea53 = "| 51 |   ```/config/{app-channel}/feign.client.url.configurationsLocalserverBcUrl```   " +
                "|https://cosaprueba-configurations-localserver-bc.rf-dev.com/api/|Sets the URL for the " +
                "configurationsLocalserverBcUrl Feign client.|";


        List<String> lineString = new ArrayList<>();
        lineString.add(linea1);
        lineString.add(linea2);

        lineString.add(linea3);
        lineString.add(linea4);
        lineString.add(linea5);

        lineString.add(linea6);
        lineString.add(linea7);
        lineString.add(linea8);
        lineString.add(linea9);
        lineString.add(linea10);

        lineString.add(linea11);
        lineString.add(linea12);
        lineString.add(linea13);
        lineString.add(linea14);
        lineString.add(linea15);

        lineString.add(linea16);
        lineString.add(linea17);
        lineString.add(linea18);
        lineString.add(linea19);
        lineString.add(linea20);
        lineString.add(linea21);
        lineString.add(linea22);

        lineString.add(linea23);
        lineString.add(linea24);
        lineString.add(linea25);
        lineString.add(linea26);
        lineString.add(linea27);
        lineString.add(linea28);
        lineString.add(linea29);
        lineString.add(linea30);

        lineString.add(linea31);
        lineString.add(linea32);
        lineString.add(linea33);
        lineString.add(linea34);
        lineString.add(linea35);
        lineString.add(linea36);
        lineString.add(linea37);

        lineString.add(linea38);
        lineString.add(linea39);
        lineString.add(linea40);

        lineString.add(linea41);
        lineString.add(linea42);
        lineString.add(linea43);
        lineString.add(linea44);

        lineString.add(linea45);
        lineString.add(linea46);

        lineString.add(linea47);
        lineString.add(linea48);
        lineString.add(linea49);

        lineString.add(linea50);

        lineString.add(linea51);

        lineString.add(linea52);

        lineString.add(linea53);


        for (String data : lineString) {

            String[] elementosLinea1 = data.split("\\|");
            elementosLinea1 = limpiarElementos(elementosLinea1);
            String linea1Formateada = formatearLinea(elementosLinea1);
            System.out.println(linea1Formateada);
        }
        System.out.println("exit");
    }

    private static String[] limpiarElementos(String[] elementos) {
        String[] elementosLimpios = new String[elementos.length];
        for (int i = 0; i < elementos.length; i++) {
            elementosLimpios[i] = elementos[i].trim();
        }
        return elementosLimpios;
    }

    private static String formatearLinea(String[] elementos) {
        StringBuilder lineaFormateada = new StringBuilder();
        try {
            for (int i = 0; i < elementos.length; i++) {
                if (i == 0) {
                    continue;
                }
                if (i == 1) {
                    String nuevo;
                    if (Integer.parseInt(elementos[i]) <= 9) {
                        nuevo = "| " + elementos[i] + "  ";
                        lineaFormateada.append(nuevo);
                        continue;
                    }
                    nuevo = "| " + elementos[i] + " ";
                    lineaFormateada.append(nuevo);
                    continue;
                }
                if (i == 2) {
                    lineaFormateada.append(String.format("|%-80s", "  " + elementos[i]));
                    continue;
                }
                if (i == 3) {
                    lineaFormateada.append(String.format("|%-81s", " " + elementos[i]));
                    continue;
                }
                lineaFormateada.append(String.format("|%-94s|", " " + elementos[i]));
            }
        } catch (Exception e) {
            lineaFormateada.append("| " + elementos[1] + " ");

            String guion = "-";
            if (elementos[2].equals("-------------------")) {
                for (int i = 0; i < 76; i++) {
                    guion = guion + "-";
                }
                elementos[2] = guion + " ";
            }
            guion = "-";
            if (elementos[3].equals("----------")) {
                for (int i = 0; i < 77; i++) {
                    guion = guion + "-";
                }
                elementos[3] = guion + " ";
            }
            guion = "-";
            if (elementos[4].equals("-------------------")) {
                for (int i = 0; i < 90; i++) {
                    guion = guion + "-";
                }
                elementos[4] = guion + " ";
            }


            lineaFormateada.append(String.format("|%-80s", "  " + elementos[2]));
            lineaFormateada.append(String.format("|%-81s", "  " + elementos[3]));
            lineaFormateada.append(String.format("|%-94s|", "  " + elementos[4]));
        }

        return lineaFormateada.toString();
    }
}
