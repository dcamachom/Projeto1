import java.time.Instant;

public class Request {
    private int idRequest;
    private int cpuTime; // en ms
    private int ioTime; // en ms
    private Instant arrivalTime;
    private Instant startTime;
    private Instant endTime;

    public Request(int idRequest, int cpuTime, int ioTime) {
        this.idRequest = idRequest;
        this.cpuTime = cpuTime;
        this.ioTime = ioTime;
        this.arrivalTime = Instant.now();
    }

    public int getRequestId() {
        return idRequest;
    }

    public int getCpuTime() {
        return cpuTime;
    }

    public int getIoTime() {
        return ioTime;
    }

    public Instant getArrivalTime() {
        return arrivalTime;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public long getResponseTime() {
        return endTime.toEpochMilli() - arrivalTime.toEpochMilli();
    }
}

