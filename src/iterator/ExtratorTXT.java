/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iterator;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import registro.Registro;

/**
 *
 * @author viriato
 */
public class ExtratorTXT extends Iterator {
    @Override
    public Registro next(RandomAccessFile raf) throws IOException{
        String linha = "";
        String regex = "([\\w]*:)([\\w]*)";
        Registro reg = new Registro();
        try {
            while ((linha = raf.readLine()) != null) {
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(linha);
                while(matcher.find()){
                    trataRegistro(matcher.group(1), matcher.group(2), reg);
                }
                break;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reg;
    }
    @Override
    public boolean hasNext(RandomAccessFile raf) throws IOException{
        long fp = raf.getFilePointer();
        String linha = "";
        try {
            if ((linha = raf.readLine()) != null) {
                raf.seek(fp);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        raf.seek(fp);
        return false;

    }
    public void trataRegistro(String campo, String dado, Registro reg){
        reg.addReg(campo.replace(":", ""), dado);
    }

}
