import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Balancer {
    private final Server[] servers;
    private final String policy;
    private int currentServerIndex;
    private final Random random;
    private final AtomicInteger requestIdGenerator;

    public Balancer(int numberOfServers, String policy) {
        this.servers = new Server[numberOfServers];
        for (int i = 0; i < numberOfServers; i++) {
            servers[i] = new Server(i + 1);
        }
        this.policy = policy.toLowerCase();
        this.currentServerIndex = 0;
        this.random = new Random();
        this.requestIdGenerator = new AtomicInteger(1);
    }

    public Server[] getServers() {
        return servers;
    }

    public void startServers() {
        for (Server server : servers) {
            new Thread(server).start();
        }
    }

    public void shutdownServers() {
        for (Server server : servers) {
            server.shutdown();
        }
    }

    public void distributeRequest(int cpuTime, int ioTime) {
        Request request = new Request(requestIdGenerator.getAndIncrement(), cpuTime, ioTime);
        Server selectedServer = selectServer();
        selectedServer.addRequest(request);
        System.out.println("Solicitud " + request.getRequestId() + " asignada al Servidor " + selectedServer.getServerId());
    }

    private Server selectServer() {
        switch (policy) {
            case "round robin":
                Server serverRR = servers[currentServerIndex];
                currentServerIndex = (currentServerIndex + 1) % servers.length;
                return serverRR;
            case "escolha aleatória":
                return servers[random.nextInt(servers.length)];
            case "fila mais curta":
                Server leastLoaded = servers[0];
                for (Server server : servers) {
                    if (server.getQueueSize() < leastLoaded.getQueueSize()) {
                        leastLoaded = server;
                    }
                }
                return leastLoaded;
            default:
                // Por defecto, Round Robin
                Server defaultServer = servers[currentServerIndex];
                currentServerIndex = (currentServerIndex + 1) % servers.length;
                return defaultServer;
        }
    }

    public void printServerStatus() {
        System.out.println("\nEstado actual de los servidores:");
        System.out.println("+------------+--------------+--------------+");
        System.out.println("| Servidor   | Estado       | En cola      |");
        System.out.println("+------------+--------------+--------------+");
        for (Server server : servers) {
            String estado = server.isBusy() ? "Ocupado" : "Inactivo";
            System.out.printf("| %-10d | %-12s | %-12d |\n",
                    server.getServerId(),
                    estado,
                    server.getQueueSize());
        }
        System.out.println("+------------+--------------+--------------+");
    }
}

