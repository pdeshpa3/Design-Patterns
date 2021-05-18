package arrayvisitors.adt;

import arrayvisitors.util.MyLogger;
import arrayvisitors.visitors.ElementI;
import arrayvisitors.visitors.VisitorI;

public class MyArrayList implements MyArrayListI, ElementI {
    int Cap = 2;
    private MyArrayI array[] = new MyArrayI[2];
    private VisitorI visitor;
    private int count=0;

    public MyArrayList(){
        MyLogger.writeMessage("MyArrayList Constructor", MyLogger.DebugLevel.CONSTRUCTOR);
    }
    /**
     * This method accepts a visitor for extracting information from list
     * @param visitor : visitor to visit
     */
    @Override
    public void accept(VisitorI visitor) {
        this.visitor = visitor;
        visitor.visit(this);
    }


    /**
     * This method accepts a element which is an array in this case
     * @param array : element to add to list
     */
    @Override
    public void addArray(MyArrayI array) {
        this.array[this.count++] = array;
    }

    /**
     * This method returns array
     * @return : elements stored in array[]
     */
    @Override
    public MyArrayI[] getElements() {
        return this.array;
    }
}
