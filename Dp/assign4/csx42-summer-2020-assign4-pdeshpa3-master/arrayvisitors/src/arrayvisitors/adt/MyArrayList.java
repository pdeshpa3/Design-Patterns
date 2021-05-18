package arrayvisitors.adt;

import arrayvisitors.visitors.IVisitor;

import java.util.ArrayList;
import java.util.List;

public class MyArrayList implements MyArrayListI {

    List<MyArrayI> list = new ArrayList<>();

    /**
     * Adds array to list
     * @param arrayI
     */
    public void addToList(MyArrayI arrayI){
        list.add(arrayI);
    }

    public List<MyArrayI> getList(){
        return list;
    }

    @Override
    public void accept(IVisitor v) {
        v.visit(this);
    }
}
