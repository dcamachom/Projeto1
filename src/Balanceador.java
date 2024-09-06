import java.util.ArrayList;

public class Balanceador {

    public String politica;
    public ArrayList<Solicitud> solicitudes;
    public Servidor servidor1;
    public Servidor servidor2;
    public Servidor servidor3;
    
    public Balanceador(String politica, Servidor servidor1, Servidor servidor2, Servidor servidor3){
        this.politica=politica;
        this.servidor1=servidor1;
        this.servidor2=servidor2;
        this.servidor3=servidor3;
        solicitudes= new ArrayList<Solicitud>(); //TODO:hacer funcion para cargar las solcitudes y que se llene la lista
    }

    //funciones para manejar las politicas
    
    public void escolhaAletoria(){

        Solicitud solicitudActual= null;
        for (int i=0;i<solicitudes.size();i++){
            int randomServer= (int) (Math.random() * 3) + 1;
            solicitudActual=solicitudes.get(i);
            if(randomServer==1){
                servidor1.procesarSolicitud(solicitudActual);
            }else if(randomServer==2){
                servidor2.procesarSolicitud(solicitudActual);
            }else{
                servidor3.procesarSolicitud(solicitudActual);
            }
        } 

    }

    public void roundRobin(){

        int cont=1;
        Solicitud solicitudActual= null;
        for (int i=0;i<solicitudes.size();i++){
            solicitudActual=solicitudes.get(i);
            if(cont==1){
                servidor1.procesarSolicitud(solicitudActual);
            }else if(cont==2){
                servidor2.procesarSolicitud(solicitudActual);
            }else{
                servidor3.procesarSolicitud(solicitudActual);
            }

            if (cont==3){
                cont=1;
            }else{
                cont++;
            }
        }

    }

    public void filaMaisCurta(){

    }

    


}
