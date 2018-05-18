
package iterator;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import registro.Registro;

public class Iterator {
    List<Registro> regs = new ArrayList<>();
    
    public void open(String path) throws FileNotFoundException, IOException{
        try(RandomAccessFile raf = new RandomAccessFile(path, "r")){
            raf.seek(0);
            run(raf);
        }catch(IOException e){
            e.getStackTrace();
        }
        
    }

    public Registro next(RandomAccessFile raf) throws IOException{
        String linha = "";
        String regex_registro_inicio = "^([' ']*(<registro>))";
        String regex_registro_fim = "^([' ']*(</registro>))";
        String regex_campos = "^([' ']*<[\\w]*>)([\\w]*)(</[\\w]*>)";
        Registro reg = new Registro();
        boolean dentro_do_registro = false;
        try {
            while ((linha = raf.readLine()) != null) {
                if(linha.matches(regex_registro_inicio)){
                    dentro_do_registro = true;
                }
                else if(linha.matches(regex_registro_fim)){
                    dentro_do_registro = false;
                    break;
                }
                else if(dentro_do_registro){
                    Pattern pattern = Pattern.compile(regex_campos);
                    Matcher matcher = pattern.matcher(linha.trim());
                    while(matcher.find()){
                        trataRegistro(matcher.group(1), matcher.group(2), matcher.group(3), reg);
                    }
                }
            }
            } catch (IOException e) {
                e.printStackTrace();
            }
        return reg;
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
    public void run(RandomAccessFile raf) throws IOException{
        Registro reg = next(raf);
        reg.printaCampos();

        while(hasNext(raf)){
            reg = next(raf);
            reg.printaCampos();
        }
    }

    public boolean hasNext(RandomAccessFile raf) throws IOException{
        long fp = raf.getFilePointer();
        String linha = "";
        String regex_registro_inicio = "^([' ']*(<registro>))";
        
        try {
            while ((linha = raf.readLine()) != null) {
                if(linha.matches(regex_registro_inicio)){
                    raf.seek(fp);
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        raf.seek(fp);
        return false;
    }
    public void close(RandomAccessFile raf) throws IOException{
        raf.close();
    }
}
