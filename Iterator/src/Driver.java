import iterator.ExtratorTXT;
import iterator.ExtratorXML;
import iterator.NestedLoopJoin;
import java.io.IOException;

public class Driver {
        public static void main(String[] args) throws IOException {
            ExtratorTXT txt = new ExtratorTXT();
            ExtratorXML xml = new ExtratorXML();
            xml.open("tabela3.xml");
            txt.open("tabela4.txt");

            NestedLoopJoin nlj = new NestedLoopJoin();
            nlj.setLeftIterator(txt);
            nlj.setRightIterator(xml);
            nlj.setLeftIteratorJoinField("idFunc");
            nlj.setRightIteratorJoinField("idFunc");
            nlj.run();
    }

}
