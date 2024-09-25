import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

// Universidad Estadual de Campinas
// Joel Antonio Lopez Cota - 290818
// Daniela Alejandra Camacho Molano - 290801


/**
 * Clase que representa un servidor en el sistema que procesa solicitudes.
 */
public class Server implements Runnable {
    private BlockingQueue<Request> requestQueue = new LinkedBlockingQueue<>();
    private int id;
    private boolean isBusy = false;
    private long totalProcessedRequests = 0;
    private long totalProcessingTime = 0;
    private long totalResponseTime = 0;
    private boolean isRunning = true;

    /**
     * Constructor que inicializa un servidor con un ID.
     * 
     * @param id Identificador del servidor.
     */
    public Server(int id) {
        this.id = id;
    }

    /**
     * Método para agregar una solicitud a la cola del servidor.
     * 
     * @param request La solicitud a agregar a la cola.
     */
    public void addRequest(Request request) {
        requestQueue.add(request);
    }

    /**
     * Método que devuelve el ID del servidor.
     * 
     * @return ID del servidor.
     */
    public int getId() {
        return id;
    }

    /**
     * Método que devuelve el tamaño actual de la cola de solicitudes.
     * 
     * @return Tamaño de la cola de solicitudes.
     */
    public int getQueueSize() {
        return requestQueue.size();
    }

    /**
     * Método que indica si el servidor está ocupado procesando solicitudes.
     * 
     * @return true si el servidor está ocupado, false en caso contrario.
     */
    public boolean isBusy() {
        return isBusy;
    }

    /**
     * Método que devuelve el total de solicitudes procesadas por el servidor.
     * 
     * @return Total de solicitudes procesadas.
     */
    public long getTotalProcessedRequests() {
        return totalProcessedRequests;
    }

    /**
     * Método que devuelve el tiempo total de procesamiento de las solicitudes.
     * 
     * @return Tiempo total de procesamiento.
     */
    public long getTotalProcessingTime() {
        return totalProcessingTime;
    }

    /**
     * Método que devuelve el tiempo total de respuesta de las solicitudes procesadas.
     * 
     * @return Tiempo total de respuesta.
     */
    public long getTotalResponseTime() {
        return totalResponseTime;
    }

    /**
     * Método que ejecuta el hilo del servidor para procesar las solicitudes en la cola.
     */
    @Override
    public void run() {
        while (isRunning) {
            try {
                Request request = requestQueue.take();
                long startTime = System.currentTimeMillis();

                isBusy = true;
                totalProcessedRequests++;
                long processingTime = request.process();
                totalProcessingTime += processingTime;

                Thread.sleep(processingTime);

                long endTime = System.currentTimeMillis();
                totalResponseTime += (endTime - startTime);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                isBusy = false;
            }
        }
    }

    /**
     * Método que calcula y devuelve el tiempo promedio de respuesta de las solicitudes.
     * 
     * @return Tiempo promedio de respuesta.
     */
    public double getAverageResponseTime() {
        return totalProcessedRequests == 0 ? 0 : (double) totalResponseTime / totalProcessedRequests;
    }

    /**
     * Método para detener el servidor.
     */
    public void shutdown() {
        isRunning = false;
    }
}
