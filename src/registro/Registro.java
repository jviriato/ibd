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

    public int getFieldCount(){
        return campo.size();
    }
    public String getFieldName(int index){
        return campo.get(index);
    }
    public String getFieldValue(int index){
        return dado.get(index);
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
}
