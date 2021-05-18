package arrayvisitors.adt;

import arrayvisitors.visitors.IVisitor;

import java.util.List;

public interface MyArrayI {
    public List<Integer> getArray();
    public void add(int number);
    void accept(IVisitor v);
}
