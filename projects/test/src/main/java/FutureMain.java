import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
public class FutureMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 0);

        for (int i = 1; i <= 5; i++) {
            int index = i;
            System.out.println("Hilo " + index);
            future = future.thenApplyAsync(previousResult -> {
                // Simulación de un proceso de larga duración
                try {
                    Thread.sleep(2000);
                    System.out.println("terminado");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return previousResult + index;
            });
        }

        // Esperar y obtener el resultado final
        int result = future.join();
        System.out.println("Resultado final: " + result);


    }

    static class ThreadTest implements Runnable {
        private final String name;

        public ThreadTest(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println("Ejecutando " + name + ", iteración " + i);
                try {
                    Thread.sleep(1000); // Simula una tarea que toma tiempo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
