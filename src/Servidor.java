import java.util.ArrayList;

public class Servidor {
    
    public String id;
    public int tempoProcessamento;
    public ArrayList<Solicitud> solicitudes;

    public Servidor(String id, int tempoProcessamento){
        this.id=id;
        this.tempoProcessamento=tempoProcessamento;
        this.solicitudes= new ArrayList<Solicitud>();
    }

    //función para procesar la solicitud
    public void procesarSolicitud(Solicitud solicitud){

    }

}
