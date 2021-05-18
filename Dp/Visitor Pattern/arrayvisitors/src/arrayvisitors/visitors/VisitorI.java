package arrayvisitors.visitors;

import arrayvisitors.adt.MyArrayI;
import arrayvisitors.adt.MyArrayListI;

public interface VisitorI {
        //for each eleemnt it should have 1 visit visit(MyArrayI arr),   visit(MyArrayListI list)
    void visit(MyArrayI myArray);
    void visit(MyArrayListI list);
}
