
import iterator.Iterator;
import iterator.ExtratorTXT;
import java.io.IOException;

public class Driver {
        public static void main(String[] args) throws IOException {
            if (args.length == 2){
                Iterator it = new Iterator();
                ExtratorTXT txt = new ExtratorTXT();
//                it.open(args[0]);
                txt.open(args[1]);

            }
    }

}
