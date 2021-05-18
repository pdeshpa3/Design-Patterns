package arrayvisitors.visitors;


import arrayvisitors.adt.MyArrayI;
import arrayvisitors.adt.MyArrayListI;
import arrayvisitors.util.FileProcessor;
import arrayvisitors.util.MyLogger;

import java.io.IOException;

import static java.lang.System.exit;

public class PopulateMyArrayVisitor implements VisitorI{

    private FileProcessor processor;

    public PopulateMyArrayVisitor(FileProcessor processor)
    {
        MyLogger.writeMessage("PopulateMyArrayVisitor Constructor", MyLogger.DebugLevel.CONSTRUCTOR);
        this.processor = processor;
    }

    /**
     * This method takes a MyArray and adds to it elements from input file
     * @param array : to store numbers
     */
    private void processFileForArray(MyArrayI array){
        MyLogger.writeMessage("Processing input file", MyLogger.DebugLevel.PROCESSING_FILE);
        try {
            while (true) {
                String input = processor.poll();
                if (input == null || input.equals("")) break;
                input = input.replaceAll("\\s+","");

                //System.out.println(input);
                array.addElement(getInt(input));
            }
            //array.print();
            processor.close();
        }catch(IOException e){
            e.printStackTrace();
        }catch(NumberFormatException e){
            e.printStackTrace();
            exit(0);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            exit(0);
        }
    }

    /**
     * Converts string to int
     * @param input : input from file
     * @return : number
     * @throws NumberFormatException : if number cannot be converted
     */
    private int getInt(String input)throws NumberFormatException, IllegalArgumentException
    {
        int number = Integer.parseInt(input);
        if(number>=100)throw new IllegalArgumentException("Invalid Input found");
        return number;
    }


    /**
     * Visits myArray for processing
     * @param myArray : array to store data into
     */
    @Override
    public void visit(MyArrayI myArray) {
        processFileForArray(myArray);
    }

    @Override
    public void visit(MyArrayListI list) {

    }
}
