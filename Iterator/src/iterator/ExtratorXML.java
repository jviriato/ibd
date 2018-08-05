package iterator;
import java.io.*;
import java.util.ArrayList;
import registro.Registro;


public class ExtratorXML extends Iterator {
    private String nome_tabela;
    
    @Override
    public void open(String p) throws FileNotFoundException, IOException{
        try { 
            this.raf = new RandomAccessFile(new File(p), "r");
            
            this.nome_tabela = this.raf.readLine();
            this.nome_tabela = this.nome_tabela.substring(this.nome_tabela.indexOf("<")+1, this.nome_tabela.indexOf(">"));
            
            this.init = this.raf.getFilePointer();
            this.reg_ant = this.raf.getFilePointer();
            
        } 
        catch (IOException ex) {}
    }
    
    @Override    
    public Registro next() throws IOException{
        if(this.hasNext()){
            this.reg_ant = this.raf.getFilePointer();
            String final_ = this.raf.readLine();
            final_ = "</" + final_.substring(final_.indexOf("<")+1, final_.indexOf(">")) + ">";
            Registro r = new Registro();
            
            String procura = raf.readLine(); 
            
            while(!procura.contains(final_)) {
                String campo = procura.substring(procura.indexOf("<")+1, procura.indexOf(">"));
                String dado  = procura.substring(procura.indexOf(">")+1, procura.indexOf("</"));
                r.addReg(campo, dado);
                procura = raf.readLine();
            }
            return r;
        }
        else{
            return null;
        }
    }
    
    @Override
    public boolean hasNext() throws IOException{
        long fp = 0;
        String linha = "";
        try {
            fp = this.raf.getFilePointer();
            linha = raf.readLine();
        } catch (IOException e) {
            e.getStackTrace();
        }
        
        if(!linha.contains(this.nome_tabela)) {
            try {
                this.raf.seek(fp);
                return true;
            } catch (IOException e) {
                e.getStackTrace();
            }
        }
        return false;
    }
