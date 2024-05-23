import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.example.connection.HttpClientConnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
public class httpMain {

    static HttpClientConnection httpClientConnection = new HttpClientConnection();

    private static final String URL = "https://5sd1yo5b51.execute-api.us-east-1.amazonaws.com/api/v1/channel/2/flow/CHANGE_STATUS/route?uuid=76ad7121-6348-43f8-bab1-3d6e8d6300dd";

    private static final String token = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoib3JkZXItY3JlYXRpb24iLCJhdWQiOiJzZXJ2aWNlIiwic3ViIjoib3JkZXItY3JlYXRpb24udjEiLCJwZXIiOltdLCJqdGkiOiIyYzhjNDYzNS0yM2I3LTQ1NDktYjBjMC1mZjMwNzQ2NDM0MmQiLCJpc3MiOiJiYWNrb2ZmaWNlLnYxIiwiaWF0IjoxNjg3NDYyOTkwLCJleHAiOjE5ODc0NjM1OTB9.eoynYjIgNp5AdMrU-7_agSD-j5W5EddTlAjrpk0ymFuy8Dib5vMrC0VVeVvjhgOL5DTgUfnSpLDPlcuv3YT08Q";

    public static void main(String[] args) {

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        for (int j = 0; j < 5; j++) {

            System.out.println("-----> hilo principal # " + j);

            List<CompletableFuture<Void>> futures = new ArrayList<>();

            for (int i = 0; i < 4; i++) {

                CompletableFuture<Void> future;

                int finalI = i;
                future = CompletableFuture.runAsync(() -> {
                            System.out.println(" # number thread [" + finalI + "]--------------------");
                            if (finalI == 3) {
                                try {
                                    Thread.sleep(25000);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            initOkHttp();
                        }
                ).exceptionally((Throwable ex) -> {
                    log.error("Error initializing linea 45 {}", ex.getMessage());
                    return null;
                });
                futures.add(future);
            }
//            scheduler.schedule(() -> cancelarHilos(futures), 25, TimeUnit.SECONDS);
            CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));

            try {
                allOf.get(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("ERROR_InterruptedException_THREAD_TIMEOUT", e);
            } catch (ExecutionException  e) {
                log.error("ERROR_ExecutionException_THREAD_TIMEOUT", e);
            }catch (TimeoutException  e) {
                log.error("ERROR_TimeoutException_THREAD_TIMEOUT", e);
            }
        }
        getThread();


//        scheduler.shutdown();

//        initHttpsAws(); //1240
//        initUrl(); //1094

    }

    private static void getThread() {
        for (Thread t : Thread.getAllStackTraces().keySet()) {
            if (t.isAlive()) {
                System.out.println(" hilo vivo -------------------------------- " + t.getName());
            }
            t.interrupt();
        }
    }

    private static void cancelarHilos(List<CompletableFuture<Void>> futures) {
        getThread();
        Thread.currentThread().interrupt();
//        for (CompletableFuture<Void> future : futures) {
//            if (!future.isDone() && !future.isCancelled()) {
//                future.cancel(true);
//            }
//        }
        System.out.println("............... :::::::: ................");
        getThread();
        System.out.println("Hilos cancelados después de cierto tiempo");
    }

    private static void initHttpsAws() {

        long startTime = System.currentTimeMillis();
        long endTime = 0L;

        try {

            CloseableHttpClient httpClient = HttpClients.createDefault();

            HttpGet request = new HttpGet(URL);

            CloseableHttpResponse response = httpClient.execute(request);

            // Capturar el cuerpo de la respuesta
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String responseBody = EntityUtils.toString(entity);
                log.info("Response Body: {} ", responseBody);
                // Aquí puedes procesar el cuerpo de la respuesta según sea necesario
            }
            response.close();
            endTime = System.currentTimeMillis();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            long totalTime = endTime - startTime;
            log.info("--> Time HTTP {}", totalTime);
        }
    }

    @SneakyThrows
    private static void initOkHttp() {

        httpClientConnection.connectionProcess("data", null, token, URL);
    }

    private static void initUrl() {
        long startTime = System.currentTimeMillis();

        try {
            URL url = new URL(URL);

            // Establecer conexión persistente
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Connection", "keep-alive");
            con.setRequestMethod("GET");


            // Realizar la solicitud
            int status = con.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                System.out.println("Response content: " + content.toString());
            } else {
                System.out.println("Error response code: " + status);
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                System.out.println("Response content: " + content.toString());
                log.error("data  {}", con);
                // Aquí puedes manejar los diferentes códigos de respuesta de error según sea necesario
            }

            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;
            System.out.println("Tiempo total de la solicitud: " + elapsedTime + " milisegundos");

            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
