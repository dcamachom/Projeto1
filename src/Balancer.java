import java.util.Random;
import java.util.List;

// Universidad Estadual de Campinas
// Joel Antonio Lopez Cota - 290818
// Daniela Alejandra Camacho Molano - 290801


/**
 * La clase Balancer es responsable de la asignación de solicitudes a servidores
 * basándose en diferentes políticas de balanceo de carga.
 */
public class Balancer {
    private List<Server> servers; 
    private String policy; 
    private Random random = new Random(); 
    private int roundRobinIndex = 0;
    private final Object lock = new Object(); 
    
    /**
     * Constructor de la clase Balancer.
     * 
     * @param servers La lista de servidores a los que se les asignarán las solicitudes.
     * @param policy La política de balanceo de carga que se utilizará.
     */
    public Balancer(List<Server> servers, String policy) {
        this.servers = servers;
        this.policy = policy.toLowerCase(); // Convertir la política a minúsculas para una comparación uniforme.
    }

    /**
     * Asigna una solicitud a un servidor según la política de balanceo de carga.
     * 
     * @param request La solicitud a ser asignada.
     */
    public void assignRequest(Request request) {
        switch (policy) {
            case "escolha aleatória":
                assignRandom(request); 
                break;
            case "round robin":
                assignRoundRobin(request); 
                break;
            case "fila mais curta":
                assignShortestQueue(request);
                break;
            default:
                assignRoundRobin(request); 
                break;
        }
    }

    /**
     * Asigna una solicitud a un servidor de manera aleatoria.
     * 
     * @param request La solicitud a ser asignada.
     */
    private void assignRandom(Request request) {
        int serverIndex = random.nextInt(servers.size()); 
        servers.get(serverIndex).addRequest(request);
        logRequestAssignment(serverIndex, request); 
    }

    /**
     * Asigna una solicitud a un servidor utilizando la política de Round Robin.
     * 
     * @param request La solicitud a ser asignada.
     */
    private void assignRoundRobin(Request request) {
        synchronized (lock) { 
            servers.get(roundRobinIndex).addRequest(request); 
            logRequestAssignment(roundRobinIndex, request); 
            roundRobinIndex = (roundRobinIndex + 1) % servers.size();
        }
    }

    /**
     * Asigna una solicitud al servidor con la cola más corta.
     * 
     * @param request La solicitud a ser asignada.
     */
    private void assignShortestQueue(Request request) {
        Server shortestQueueServer = servers.stream()
                .min((s1, s2) -> Integer.compare(s1.getQueueSize(), s2.getQueueSize()))
                .orElse(servers.get(0)); 
        shortestQueueServer.addRequest(request);
        logRequestAssignment(shortestQueueServer.getId(), request); 
    }
    /**
     * Registra la asignación de una solicitud a un servidor.
     * 
     * @param serverIndex El índice del servidor al que se le asignó la solicitud.
     * @param request La solicitud asignada.
     */
    private void logRequestAssignment(int serverIndex, Request request) {
        System.out.println("Assigned " + request + " to Server " + serverIndex);
    }

    /**
     * Imprime el estado actual de todos los servidores.
     */
    public void printServerStatus() {
        System.out.println("\nEstado actual de los servidores:");
        for (Server server : servers) {
            String estado = server.isBusy() ? "Ocupado" : "Inactivo"; 
            System.out.printf("Servidor %d: Estado: %s, En cola: %d\n", server.getId(), 
                                estado, server.getQueueSize());
        }
    }

    /**
     * Imprime las métricas finales de procesamiento de solicitudes de todos los servidores.
     */
    public void printMetrics() {
        long totalRequestsProcessed = 0; 
        long totalProcessingTime = 0; 

        System.out.println("\nMétricas finales:");
        for (Server server : servers) {
            totalRequestsProcessed += server.getTotalProcessedRequests(); 
            totalProcessingTime += server.getTotalProcessingTime();
            System.out.printf("Servidor %d procesó %d solicitudes\n", server.getId(), server.getTotalProcessedRequests());
            System.out.printf("Tiempo promedio de respuesta: %.2f ms\n", server.getAverageResponseTime());
        }

        double averageProcessingTime = totalProcessingTime / (double) totalRequestsProcessed;
        System.out.printf("Throughput del sistema: %.2f solicitudes/segundo\n", (double) totalRequestsProcessed / (totalProcessingTime / 1000.0));
        System.out.printf("Tiempo promedio de procesamiento: %.2f ms\n", averageProcessingTime);
    
        }

    /**
     * Espera hasta que todas las colas de los servidores estén vacías.
     */
    public void waitForCompletion() {
        boolean allQueuesEmpty;
        do {
            allQueuesEmpty = servers.stream().allMatch(server -> server.getQueueSize() == 0); // Verifica si todas las colas están vacías.
            printServerStatus(); 
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); 
            }
        } while (!allQueuesEmpty);
    
    }
}
