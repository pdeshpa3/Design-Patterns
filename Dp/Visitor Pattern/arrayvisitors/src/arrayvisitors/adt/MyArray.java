package arrayvisitors.adt;

import arrayvisitors.util.MyLogger;
import arrayvisitors.visitors.ElementI;
import arrayvisitors.visitors.VisitorI;

import java.util.Arrays;

public class MyArray implements MyArrayI, ElementI {

    private int arr[];
    private int currentCapacity = 0;
    private int CAPACITY = 10;


    public MyArray(){
        MyLogger.writeMessage("MyArray Constructor", MyLogger.DebugLevel.CONSTRUCTOR);
        arr = new int[this.CAPACITY];
        for(int i=0; i<CAPACITY; i++)arr[i] = -1;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    public int[] getElements(){
        return this.arr;
    }


    @Override
    public int getLength(){
        return this.currentCapacity;
    }


    /**
     * This method adds an element to the array; resizes array if needed
     * @param element : element to add
     */
    @Override
    public void addElement(int element) {
        if(currentCapacity<CAPACITY){
            arr[currentCapacity] = element;

        }else{
            resize();
            arr[currentCapacity] = element;
        }
        currentCapacity++;
    }

    /**
     * This method resizes array
     */
    @Override
    public void resize() {
        int newCap = (int)Math.ceil(currentCapacity*1.5);
        int newArr[] = new int[newCap]; //created a new array

        //copying over the elements
        for(int i=0; i<currentCapacity; i++){
            newArr[i] = arr[i];
        }

        this.arr = newArr;
        this.CAPACITY = newCap;

    }

    /**
     * DUMMY
     */
    public void print(){
        for(int x:arr)
            System.out.print(x+" ");

        System.out.println(CAPACITY+" -- "+currentCapacity);
    }

    /**
     * This method accepts a visitor to visit
     * @param visitor : visitor for processing
     */
    @Override
    public void accept(VisitorI visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return Arrays.toString(this.arr);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
