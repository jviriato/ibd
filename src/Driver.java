
import iterator.Iterator;
import java.io.IOException;

public class Driver {
        public static void main(String[] args) throws IOException {
            if (args.length == 1){
                Iterator it = new Iterator();
                it.open(args[0]);
            }
    }

}
