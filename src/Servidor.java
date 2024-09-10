import java.util.ArrayList;

public class Servidor extends Thread{
    
    public String id;
    public int tempoProcessamento;
    public ArrayList<Solicitud> solicitudes;
    public boolean corriendo;

    public Servidor(String id, int tempoProcessamento){
        this.id=id;
        this.tempoProcessamento=tempoProcessamento;
        this.solicitudes= new ArrayList<Solicitud>();
        this.corriendo=true;
    }

    public void addSolicitud(Solicitud solicitud){
        solicitudes.add(solicitud);
    }
    
    @Override
    public void run(){
        while (corriendo) {
            if (solicitudes.size()>0){
                
            }
        }
    }

}
