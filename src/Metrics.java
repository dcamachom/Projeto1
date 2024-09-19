import java.util.ArrayList;
import java.util.List;

public class Metrics {
    private static final List<Request> processedRequests = new ArrayList<>();

    public static synchronized void recordRequest(Request request) {
        processedRequests.add(request);
    }

    public static synchronized void printMetrics() {
        int totalRequests = processedRequests.size();
        if (totalRequests == 0) {
            System.out.println("No se procesaron solicitudes.");
            return;
        }

        long totalResponseTime = 0;
        for (Request req : processedRequests) {
            totalResponseTime += req.getResponseTime();
        }

        double averageResponseTime = (double) totalResponseTime / totalRequests;
        double throughput = totalRequests / ((double) (processedRequests.get(processedRequests.size() - 1).getEndTime().toEpochMilli() - 
                                                       processedRequests.get(0).getArrivalTime().toEpochMilli()) / 1000);

        System.out.println("\n--- Métricas del Sistema ---");
        System.out.println("Total de solicitudes procesadas: " + totalRequests);
        System.out.printf("Tiempo medio de respuesta: %.2f ms\n", averageResponseTime);
        System.out.printf("Vazão del sistema (throughput): %.2f solicitudes/segundo\n", throughput);
        System.out.println("-----------------------------");
    }
}

