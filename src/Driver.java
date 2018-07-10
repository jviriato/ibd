import iterator.ExtratorTXT;
import iterator.ExtratorXML;
import iterator.NestedLoopJoin;
import java.io.IOException;

public class Driver {
        public static void main(String[] args) throws IOException {
            if (args.length == 2){
                ExtratorTXT txt = new ExtratorTXT();
                ExtratorXML xml = new ExtratorXML();
                xml.open(args[0]);
//                xml.run();
                txt.open(args[1]);
//                txt.run();
                
                NestedLoopJoin nlj = new NestedLoopJoin();
                nlj.setLeftIterator(xml);
                nlj.setRightIterator(txt);
                nlj.setLeftIteratorJoinField("idFunc");
                nlj.setRightIteratorJoinField("idFunc");
                
                ExtratorTXT arq3 = new ExtratorTXT();
                arq3.open("arquivo3.txt");
                
                NestedLoopJoin n = new NestedLoopJoin();
                n.setLeftIterator(arq3);
                n.setRightIterator(nlj);
                n.setLeftIteratorJoinField("idFunc");
                n.setRightIteratorJoinField("idFunc");
                n.run();
            }
    }

}
