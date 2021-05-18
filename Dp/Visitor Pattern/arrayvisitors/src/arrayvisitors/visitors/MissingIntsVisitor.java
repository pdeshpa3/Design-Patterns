package arrayvisitors.visitors;

import arrayvisitors.adt.MyArrayI;
import arrayvisitors.adt.MyArrayListI;
import arrayvisitors.util.MyLogger;
import arrayvisitors.util.Results;

import java.util.Arrays;
import java.util.List;

public class MissingIntsVisitor implements VisitorI {


    private Results results;
    public MissingIntsVisitor(Results results){
        MyLogger.writeMessage("MissingIntsVisitor Constructor", MyLogger.DebugLevel.CONSTRUCTOR);
        this.results = results;
    }
    private  int count = 1;

    /**
     * This method checks if the array contains an element x
     * @param array : array to search into
     * @param x : element to find
     * @return : true/false
     */
    private boolean contains(int array[], int x){
        for(int num: array){
            if(num == x){
                return true;
            }
        }
        return false;
    }

    /**
     * This method takes a MyArray type and finds missing elements between(0-99(included)) and stores in result
     * @param myArray : array to process
     */
    @Override
    public void visit(MyArrayI myArray) {

        MyLogger.writeMessage("MissingInts processing visit", MyLogger.DebugLevel.MISSING_INTS);
        StringBuilder sb = results.getSb();
        sb.append("Printing Missing Ints for Array - "+this.count+"\n");

       // sb.setLength(0);
        int[] array = myArray.getElements();

        for(int i=0; i<100; i++){
            if(!contains(array, i)){
                if(i%10 == i){
                    sb.append("0"+i);
                    sb.append("\n");
                }else{
                    sb.append(i+"\n");
                }
            }
        }
        sb.append("\n");

        count++;
    }

    @Override
    public void visit(MyArrayListI list) {

    }
}
