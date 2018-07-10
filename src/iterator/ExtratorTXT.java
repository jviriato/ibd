package iterator;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import registro.Registro;

/**
 *
 * @author viriato
 */
public class ExtratorTXT extends Iterator {
    @Override
    public Registro next() throws IOException{
        String linha = "";
        String regex = "([\\w]*:)([\\w]*)";
        Registro reg = new Registro();
        try {
            while ((linha = this.raf.readLine()) != null) {
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(linha);
                while(matcher.find()){
                    trataRegistro(matcher.group(1), matcher.group(2), reg);
                }
                break;

            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        return reg;
    }
    @Override
    public boolean hasNext() throws IOException{
        long fp = this.raf.getFilePointer();
        String linha = "";
        try {
            if ((linha = this.raf.readLine()) != null) {
                this.raf.seek(fp);
                return true;
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        this.raf.seek(fp);
        return false;

    }
    public void trataRegistro(String campo, String dado, Registro reg){
        reg.addReg(campo.replace(":", ""), dado);
    }

}
