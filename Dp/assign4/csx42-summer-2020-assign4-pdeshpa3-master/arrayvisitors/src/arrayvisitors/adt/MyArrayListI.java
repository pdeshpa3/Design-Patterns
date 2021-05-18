package arrayvisitors.adt;

import arrayvisitors.visitors.IVisitor;

import java.util.List;

public interface MyArrayListI {
    public void addToList(MyArrayI arrayI);
    public List<MyArrayI> getList();
    void accept(IVisitor v);
}
