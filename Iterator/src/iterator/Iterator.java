
package iterator;
import java.io.*;
import registro.Registro;

public abstract class Iterator {
    public RandomAccessFile raf;
    public String path;
    public long init;
    public long reg_ant;
    public void open(String p) throws FileNotFoundException, IOException{
        this.raf = new RandomAccessFile(p, "r");
        this.raf.seek(0);
        this.path = p;
        this.init = this.raf.getFilePointer();
        this.reg_ant = this.raf.getFilePointer();
    }
    public abstract Registro next() throws IOException;
    public abstract boolean hasNext() throws IOException;

    public void run() throws IOException{
        while(hasNext()){
            Registro reg = next();
            String r = reg.retornaCampos();
            System.out.println(r);
        }
    }


    public void close() throws IOException{
        try{
            this.raf.close();
        }catch(IOException e){
            e.getStackTrace();
        }
    }
    
    public void reset() throws IOException{
        this.raf.seek(this.init);
    }
    
    public void fp_back() throws IOException{
        this.raf.seek(this.reg_ant);
    }
}
