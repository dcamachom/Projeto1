import java.time.Instant;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Server implements Runnable{
    private final int serverId;
    private final BlockingQueue<Request> requestQueue;
    private volatile boolean isRunning;
    private volatile boolean isBusy;

    public Server(int serverId) {
        this.serverId = serverId;
        this.requestQueue = new LinkedBlockingQueue<>();
        this.isRunning = true;
        this.isBusy = false;
    }

    public int getServerId() {
        return serverId;
    }

    public int getQueueSize() {
        return requestQueue.size();
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void addRequest(Request request) {
        try {
            requestQueue.put(request);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void shutdown() {
        isRunning = false;
    }

    @Override
    public void run() {
        while (isRunning || !requestQueue.isEmpty()) {
            try {
                Request request = requestQueue.poll();
                if (request != null) {
                    isBusy = true;
                    request.setStartTime(Instant.now());
                    // Simula el tiempo de CPU
                    Thread.sleep(request.getCpuTime());
                    // Simula el tiempo de I/O
                    Thread.sleep(request.getIoTime());
                    request.setEndTime(Instant.now());
                    Metrics.recordRequest(request);
                    System.out.println("Servidor " + serverId + " procesó la solicitud " + request.getRequestId() +
                            ", CPU: " + request.getCpuTime() +"ms, I/O: " + request.getIoTime() + "ms).");
                    isBusy = false;
                } else {
                    // Si no hay solicitudes, el servidor está inactivo
                    isBusy = false;
                    Thread.sleep(100); // Evita un ciclo muy rápido
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}