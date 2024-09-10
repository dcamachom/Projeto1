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

        for (int i=0;i<solicitudes.size();i++){
            int randomServer= (int) (Math.random() * 3) + 1;
            if(randomServer==1){
                
            }else if(randomServer==2){
                
            }else{
                
            }
        } 

    }

    public void roundRobin(){

        int cont=1;
        for (int i=0;i<solicitudes.size();i++){
            if(cont==1){
                
            }else if(cont==2){
                
            }else{
                
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
