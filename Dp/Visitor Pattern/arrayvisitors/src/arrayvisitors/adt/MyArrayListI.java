package arrayvisitors.adt;

import arrayvisitors.visitors.VisitorI;

public interface MyArrayListI  {

     void addArray(MyArrayI array);

     MyArrayI[] getElements();

    void accept(VisitorI visitor);
}
