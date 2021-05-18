package arrayvisitors.visitors;

import arrayvisitors.adt.MyArrayI;
import arrayvisitors.adt.MyArrayListI;

public interface IVisitor {
    void visit(MyArrayI myArrayI);
    void visit(MyArrayListI listI);
}
