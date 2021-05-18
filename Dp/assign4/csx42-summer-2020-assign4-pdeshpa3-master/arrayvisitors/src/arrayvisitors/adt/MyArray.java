package arrayvisitors.adt;

import arrayvisitors.visitors.IVisitor;

import java.util.Arrays;
import java.util.List;

public class MyArray implements MyArrayI{

    private Integer[] myarr = new Integer[10];
    private int index = 0;
    int size = 10;

    public List<Integer> getArray(){
        return Arrays.asList(myarr);
    }

    /**
     * Adds a number to array
     * @param number : number to array
     */
    public void add(int number){

        if(index<size)
        myarr[index++] = number;
        else{
            int newSize = (int)((index+1)*0.5+(index+1));
            Integer[] newarr = new Integer[newSize];
            System.arraycopy(myarr, 0, newarr, 0, index);
            myarr = newarr;
            myarr[index++] = number;
            size = newSize;
           // System.out.println(newSize);
        }
    }

    /**
     * This accepts a visitor
     * @param v : missing ints visitor
     */
    @Override
    public void accept(IVisitor v) {
        v.visit(this);
    }

    public void display(){
        for(int x: myarr){
            System.out.print(x+" ");
        }
        System.out.println(myarr.length);
    }


}
