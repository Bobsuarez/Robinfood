import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class threadMain {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 3; i++) {
            Runnable worker = new ThreadTest("Hilo " + (i + 1));
            executorService.execute(worker);
        }

        executorService.shutdown();
        while (!executorService.isTerminated()) {
            // Esperar a que todos los hilos finalicen
        }

        System.out.println("Todos los hilos han terminado.");
    }

    static class ThreadTest implements Runnable {
        private final String name;

        public ThreadTest(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println("Ejecutando " + name + ", iteración " + i );
                try {
                    Thread.sleep(1000); // Simula una tarea que toma tiempo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
