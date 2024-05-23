import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExceptionMain {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Runnable> tasks = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            final int taskNumber = i;
            Runnable runnable = () -> {
                try {
                    // Simular una tarea que lanza una excepción
                    Thread.sleep(2000);
                    if (taskNumber % 2 == 0) {
                        throw new RuntimeException("Excepción en el hilo " + taskNumber);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (RuntimeException e) {
                    throw new RuntimeException("Excepción en el hilo " + taskNumber);
//                    System.out.println("Excepción capturada en el hilo " + taskNumber + ": " + e.getMessage());
                }
            };
            tasks.add(runnable);
        }

        for (Runnable task : tasks) {
            executorService.execute(task);
        }

        // Apagar el ExecutorService
        executorService.shutdown();

        // Otras tareas en el hilo principal
        System.out.println("Tareas adicionales en el hilo principal.");
    }
}
