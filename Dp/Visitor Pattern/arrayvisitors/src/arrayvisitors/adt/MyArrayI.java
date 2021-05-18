package arrayvisitors.adt;

import arrayvisitors.visitors.VisitorI;

public interface MyArrayI {

    void addElement(int element);
    void resize();
    void print();
    int getLength();
    int[] getElements();
    void accept(VisitorI visitor);


}
