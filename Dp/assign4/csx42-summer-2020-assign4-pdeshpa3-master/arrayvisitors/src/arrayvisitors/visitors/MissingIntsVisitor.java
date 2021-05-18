package arrayvisitors.visitors;

import arrayvisitors.adt.MyArrayI;
import arrayvisitors.adt.MyArrayListI;
import arrayvisitors.util.MyLogger;
import arrayvisitors.util.Results;

import java.util.List;

public class MissingIntsVisitor implements IVisitor{

    private Results res;

    public MissingIntsVisitor(Results results){
        MyLogger.writeMessage("MissingIntsVisitor constructor called", MyLogger.DebugLevel.CONSTRUCTOR);
        res =results;
    }

    /**
     * accepts an array and finds missing number between 0 and 99 and stores in results
     * @param myArrayI
     */
    @Override
    public void visit(MyArrayI myArrayI) {

        List<Integer> myarr = myArrayI.getArray();
        for(int i=0; i<=99; i++){
            if(!myarr.contains(i)){
                res.appendToContent(i+" ");
            }
        }
        res.appendToContent("\n");
    }

    @Override
    public void visit(MyArrayListI listI) {
        //empty
    }
}
