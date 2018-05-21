
package iterator;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import registro.Registro;

public abstract class Iterator {
    public List<Registro> regs = new ArrayList<>();
    public void open(String path) throws FileNotFoundException, IOException{
        try(RandomAccessFile raf = new RandomAccessFile(path, "r")){
            raf.seek(0);
            run(raf);
        }catch(IOException e){
            e.getStackTrace();
        }
        
    }

    public abstract Registro next(RandomAccessFile raf) throws IOException;
    public abstract boolean hasNext(RandomAccessFile raf) throws IOException;
    public void printaCampos()
    {
        for(Registro r : regs){
            for(int i = 0; i < r.getFieldCount(); i++){
                System.out.println(r.getFieldName(i) + ": " + r.getFieldValue(i));
            }
            System.out.println("");
        }
        
    }
    public void run(RandomAccessFile raf) throws IOException{
        Registro reg = next(raf);
        regs.add(reg);
        while(hasNext(raf)){
            reg = next(raf);
            regs.add(reg);
        }
        printaCampos();
    }


    public void close(RandomAccessFile raf) throws IOException{
        raf.close();
    }
}
