import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceExample {

    public static void main(String[] args) {
        // Crear un ScheduledExecutorService con 5 hilos en el pool
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

        // Crear una tarea para imprimir la hora actual
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName);
            for(int i=0; i < 10; i++ ){
                System.out.println("Task executed at " + System.currentTimeMillis());
            }
        };

        // Programar la tarea para que se ejecute después de un retraso de 5 segundos
        executorService.schedule(task, 5, TimeUnit.SECONDS);

        // Programar la tarea para que se ejecute a intervalos fijos de 10 segundos
        executorService.scheduleAtFixedRate(task, 0, 10, TimeUnit.SECONDS);
//
//        // Mantener el programa en ejecución durante un tiempo para observar las ejecuciones
//        try {
//            Thread.sleep(60000); // 60 segundos
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        // Apagar el ScheduledExecutorService
//        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
}
