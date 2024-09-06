public class Solicitud {

    public int tamSolicitud;
    public int tempoIO;
    public int tempoCPU;

    public Solicitud(int tamSolicitud, int tempoCPU, int tempoIO){
        this.tamSolicitud=tamSolicitud;
        this.tempoCPU=tempoCPU;
        this.tempoIO=tempoIO;
    }
    
}
