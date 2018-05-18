package registro;


import java.util.ArrayList;
import java.util.List;

public class Registro {
    List<String> campo = new ArrayList<>();
    List<String> dado  = new ArrayList<>();

    public Registro() {
        campo = new ArrayList<>();
        dado  = new ArrayList<>();
    }

    
    public Registro(String c, String d) {
        this.campo.add(c);
        this.dado.add(d);
    }

    public void addReg(String c, String d){
        this.campo.add(c);
        this.dado.add(d);
    }
    
    public void printaCampos(){
        for(int i = 0; i < campo.size(); i++){
            System.out.println(campo.get(i) + ": " + dado.get(i));
        }
        System.out.println("");
    }
    public int getFieldCount(){
        return this.campo.size();
    }
}
