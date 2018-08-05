package registro;


import java.util.ArrayList;
import java.util.List;

public class Registro {
    List<String> campo;
    List<String> dado;

    public Registro() {
        campo = new ArrayList<>();
        dado  = new ArrayList<>();
    }
    public Registro(List<String> Nome_Campos, List<String> Valores_Campos) {
        this.campo = Nome_Campos;
        this.dado = Valores_Campos;
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

    public String retornaCampos(){
        String reg = "";
        for(int i = 0; i < campo.size(); i++){
            reg += campo.get(i) + ": " + dado.get(i);
            reg += "\n";
        }
        return reg;
    }
}
