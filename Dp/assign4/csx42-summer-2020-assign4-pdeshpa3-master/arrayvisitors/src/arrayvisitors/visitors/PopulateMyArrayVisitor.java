package arrayvisitors.visitors;

import arrayvisitors.adt.MyArray;
import arrayvisitors.adt.MyArrayI;
import arrayvisitors.adt.MyArrayListI;
import arrayvisitors.util.FileProcessor;
import arrayvisitors.util.MyLogger;

import java.io.IOException;

public class PopulateMyArrayVisitor implements IVisitor{

    public FileProcessor proc;
    public PopulateMyArrayVisitor(FileProcessor fp){
        MyLogger.writeMessage("PopulateArrayVisitor constructor called", MyLogger.DebugLevel.CONSTRUCTOR);
        proc = fp;
    }

    /**
     * Removes spaces, tabs, newlines
     * @param inp: input
     * @return : input
     */
    public String strip(String inp){
        return inp.replaceAll("[\\n\\t ]","");
    }

    /**
     * This method populates array
     * @param myArrayI : array to populate with numbers
     */
    @Override
    public void visit(MyArrayI myArrayI) {
        MyLogger.writeMessage("input process", MyLogger.DebugLevel.FILE_PROCESSOR);
        try {
            while (true) {
                String inp = proc.poll();
                if (inp == null) break;
                if (inp.equals("")) break;
                inp = strip(inp);
                Integer n = new Integer(inp);
                myArrayI.add(n);
            }
        }catch(Exception e){}
    }

    @Override
    public void visit(MyArrayListI listI) {
        //empty
    }
}
