package arrayvisitors.visitors;

import arrayvisitors.adt.MyArrayI;
import arrayvisitors.adt.MyArrayListI;
import arrayvisitors.util.MyLogger;
import arrayvisitors.util.Results;

import java.util.List;

public class CommonIntsVisitor implements IVisitor{

    Results res;
    public CommonIntsVisitor(Results results){
        MyLogger.writeMessage("CommonIntsVisitor constructor called", MyLogger.DebugLevel.CONSTRUCTOR);
        res = results;
    }

    @Override
    public void visit(MyArrayI myArrayI) {
        //empty
    }

    /**
     * accepts a list and finds elements in common
     * @param listI : list containing 2 arrays
     */
    @Override
    public void visit(MyArrayListI listI) {
        //common ints in both arrays
        List<MyArrayI> list = listI.getList();
        MyArrayI list1 = list.get(0);
        MyArrayI list2 = list.get(1);
        List<Integer> l1 = list1.getArray();
        List<Integer>  l2 = list2.getArray();

        for(int i=0; i<l1.size(); i++){
            for(int j=0; j<l2.size(); j++){
                if(l1.get(i) == l2.get(j)){
                    res.appendToContent(l1.get(i)+" ");
                }
            }
        }

        res.appendToContent("\n");
    }
}
