package iterator;
import java.io.*;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import registro.Registro;

public class NestedLoopJoin extends Iterator{
    Iterator it_left;
    Iterator it_right;
    String   field_left;
    String   field_right;
    boolean fim_loop = true;
    boolean pode_guardar = true;
    Registro reg_temp;
    
    public void setLeftIterator(Iterator it){
        it_left = it;
    }
    public void setRightIterator(Iterator it){
        it_right = it;
    }
    public void setLeftIteratorJoinField(String field){
        field_left = field;
    }
    public void setRightIteratorJoinField(String field){
        field_right = field;
    }
    
    @Override
    public Registro next() throws IOException{
        Registro r_left;
        Registro r_right;
        boolean reg_modificado = false;
        r_left = this.it_left.next();
        while(this.it_right.hasNext()){
            r_right = this.it_right.next();         
            if(deu_match(r_left, r_right)){
                reg_modificado = true;
                r_left = join(r_left, r_right);
            }
        }
        
        this.it_right.reset();
        if(reg_modificado){
            reg_modificado = false;
            return r_left;
        }
        else{
            return null;
        }
    }
    public boolean deu_match(Registro r_left, Registro r_right){
        int i_left, i_right;
        i_left = getIndex(r_left);
        i_right = getIndex(r_right); 
        String field_value_l = r_left.getFieldValue(i_left);
        String field_value_r = r_right.getFieldValue(i_right);
        int value_l = Integer.valueOf(field_value_l);
        int value_r = Integer.valueOf(field_value_r);
        
        return (value_l == value_r);
    }
    public Registro join(Registro r_left, Registro r_right){
        int i_left, i_right;
        i_left = getIndex(r_left);
        i_right = getIndex(r_right);
        String s = "";
        Registro r = new Registro();
        s = r_left.retornaCampos();
        s += r_right.retornaCampos();
        Set<String> list = trataString(s, this.field_right);
        for (String set : list) {
            String dados[];
            dados = set.split(":");
            r.addReg(dados[0], dados[1].trim());
        }
        return r;
    }
    
    private Set trataString(String s, String campo)
    {
        //para remover duplicatas
        StringBuilder textResult = new StringBuilder();
        Set list = new LinkedHashSet<>();
        try (Scanner scanner = new Scanner(s)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(!list.contains(line)){
                    list.add(line);
                    textResult.append(line).append(System.getProperty("line.separator"));
                }
            }
        }
        return list;
    }
    
    @Override
    public boolean hasNext() throws IOException{
        return this.it_left.hasNext();
    }
    
    private int getIndex(Registro reg){
        int index = 0;
        for(int i = 0; i < reg.getFieldCount(); i++){ //se reg.nome == field_left
            if(reg.getFieldName(i).equals(field_left)){
               index = i;
            }
        }
        return index;
    }
        
    @Override
    public void run() throws IOException{
        try{
            while(this.it_left.hasNext()){
                Registro r = this.next();
                if (r != null){
                    System.out.println(r.retornaCampos());                
                }
            }
        } catch(NullPointerException npe){}
    }
    @Override
    public void fp_back() throws IOException{
        this.it_left.fp_back();
        this.it_right.fp_back();
    }
    
}
