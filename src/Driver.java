
import iterator.Iterator;
import iterator.ExtratorTXT;
import iterator.ExtratorXML;
import java.io.IOException;

public class Driver {
        public static void main(String[] args) throws IOException {
            if (args.length == 2){
                ExtratorTXT txt = new ExtratorTXT();
                ExtratorXML xml = new ExtratorXML();
                xml.open(args[0]);
                txt.open(args[1]);

            }
    }

}
