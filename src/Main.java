import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

// Universidad Estadual de Campinas
// Joel Antonio Lopez Cota - 290818
// Daniela Alejandra Camacho Molano - 290801

public class Main {
    public static void main(String[] args) {
        Balancer loadBalancer;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione la política de balanceo de carga: ");
        System.out.println("1. Escolha Aleatória");
        System.out.println("2. Round Robin");
        System.out.println("3. Fila Mais Curta");
        String policyOption = scanner.nextLine();
        String policy = "";

        switch (policyOption) {
            case "1":
                policy = "escolha aleatória";
                break;
            case "2":
                policy = "round robin";
                break;
            case "3":
                policy = "fila mais curta";
                break;
            default:
                System.out.println("Selección no válida. Se utilizará 'Round Robin' por defecto.");
                policy = "round robin";
                break;
        }

        List<Server> servers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Server server = new Server(i);
            servers.add(server);
            new Thread(server).start();
        }

        loadBalancer = new Balancer(servers, policy);

        Random random = new Random();
        ExecutorService requestExecutor = Executors.newCachedThreadPool(); // Pool de hilos para concurrencia
        int id= 1;
       // Generar lotes de 2 a 3 solicitudes
        for (int i = 1; i <= 5; i ++) {
            int numberOfRequests = random.nextInt(2)+ 2; // Generar 2 o 3 solicitudes por lote
            for (int j = 0; j < numberOfRequests; j++) {
                int finalId=id++;
                requestExecutor.submit(() -> {
                    int cpuTime = random.nextInt(500) + 200;  // 200ms a 700ms
                    int ioTime = random.nextInt(2000) + 1000; // 1000ms a 3000ms
                    loadBalancer.assignRequest(new Request(finalId,cpuTime, ioTime));
                });
            }
            loadBalancer.printServerStatus();


            // Simular intervalo aleatorio entre lotes
            int arrivalInterval = random.nextInt(900) + 100; // Intervalo aleatorio: 100ms a 1000ms
            try {
                TimeUnit.MILLISECONDS.sleep(arrivalInterval);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        // Esperar hasta que todas las solicitudes sean procesadas
        loadBalancer.waitForCompletion();
        
        // Mostrar métricas finales
        loadBalancer.printMetrics();
        scanner.close();
        System.exit(0); // Termina el programa
    }
}
