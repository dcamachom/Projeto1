import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

// Universidade Estadual de Campinas
// Joel Antonio Lopez Cota 290818
// Daniela Alejandra Camacho Molano 290801
// 11/09/2002

// Clase principal para simular el tráfico de solicitudes
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
        loadBalancer =new Balancer(3,policy);
        loadBalancer.startServers();
        
        Random random = new Random();

        // Generación de solicitudes
        for (int i = 1; i <= 20; i++) {
            // Asigna tiempos de procesamiento basados en el tipo
            int cpuTime = 0;
            int ioTime = 0;
            
            cpuTime = random.nextInt(500) + 200; // 200ms a 700ms
            ioTime = random.nextInt(2000) + 1000; // 1000ms a 3000ms

            loadBalancer.distributeRequest(cpuTime, ioTime);
            loadBalancer.printServerStatus();

            // Intervalo aleatorio entre solicitudes: 100ms a 1000ms
            int arrivalInterval = random.nextInt(900) + 100;
            try {
                TimeUnit.MILLISECONDS.sleep(arrivalInterval);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Espera a que todas las solicitudes sean procesadas
        loadBalancer.shutdownServers();

        // Permite tiempo para que los servidores terminen
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Imprime las métricas
        Metrics.printMetrics();

        scanner.close();
    }
}
