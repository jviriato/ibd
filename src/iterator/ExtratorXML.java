package iterator;
import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import registro.Registro;

/**
 *
 * @author viriato
 */
public class ExtratorXML extends Iterator {
    private String Nome_tabela;
    
    @Override
    public void open(String p) throws FileNotFoundException, IOException{
        try { 
            this.raf = new RandomAccessFile(new File(p), "r");
            
            this.Nome_tabela = this.raf.readLine();
            this.Nome_tabela = this.Nome_tabela.substring(this.Nome_tabela.indexOf("<")+1, this.Nome_tabela.indexOf(">"));
            
            this.init = this.raf.getFilePointer();
            this.reg_ant = this.raf.getFilePointer();
            
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }
    
    @Override    
    public Registro next() throws IOException{
        if(!this.hasNext()) return null;
        this.reg_ant = this.raf.getFilePointer();
        String final_ = this.raf.readLine();
        
        final_ = "</" + final_.substring(final_.indexOf("<")+1, final_.indexOf(">")) + ">";
        
        ArrayList<String> c = new ArrayList<>(); 
        ArrayList<String> v = new ArrayList<>();
        
        String procura = raf.readLine(); 
        while(!procura.contains(final_)) {
            c.add(procura.substring(procura.indexOf("<")+1, procura.indexOf(">"))); 
            v.add(procura.substring(procura.indexOf(">")+1, procura.indexOf("</")));                      
            
            procura = raf.readLine();
        }
        Registro ret = new Registro(c, v); 
         
        return ret;
    }
    public void trataRegistro(String campoInit, String dado, String campoFim, Registro reg){
        if(registroEhValido(campoInit, campoFim)){
            reg.addReg(campoInit.replaceAll("[<>]", ""), dado);
        }else{
            System.out.println("Campo inválido!");
        }
    }
    
    public boolean registroEhValido(String campoInit, String campoFim){
        return campoInit.equals(campoFim.replace("/", ""));
    }

    
    @Override
    public boolean hasNext() throws IOException{
        long pos = 0;
        String teste = null;
        try {
            pos = this.raf.getFilePointer();
            teste = raf.readLine();
        } catch (IOException ex) {}
        
        if(teste == null) return false;
        if(!teste.contains(this.Nome_tabela)) {
            try {
                this.raf.seek(pos);
                return true;
            } catch (IOException ex) {}
        }
        return false;
    }
