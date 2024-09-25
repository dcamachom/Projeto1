// Universidad Estadual de Campinas
// Joel Antonio Lopez Cota - 290818
// Daniela Alejandra Camacho Molano - 290801

/**
 * Clase que representa una solicitud en el sistema.
 */
public class Request {
    private int id;
    private int cpuTime;
    private int ioTime;

    /**
     * Constructor de la clase Request.
     *
     * @param id       El identificador de la solicitud.
     * @param cpuTime  El tiempo que la solicitud requiere de CPU.
     * @param ioTime   El tiempo que la solicitud requiere de I/O.
     */
    public Request(int id, int cpuTime, int ioTime) {
        this.cpuTime = cpuTime;
        this.ioTime = ioTime;
        this.id = id;
    }

    /**
     * Procesa la solicitud y determina el tiempo que toma, ya sea basado en 
     * el tiempo de CPU o el tiempo de I/O, dependiendo de cuál sea mayor.
     *
     * @return El tiempo que se tarda en procesar la solicitud.
     */
    public long process() {
        return cpuTime > ioTime ? cpuTime : ioTime; 
    }

    @Override
    public String toString() {
        return "Request{id= " + id + ", cpuTime=" + cpuTime + ", ioTime=" + ioTime + "}";
    }
}
