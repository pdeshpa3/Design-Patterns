package arrayvisitors.driver;


import arrayvisitors.adt.MyArray;
import arrayvisitors.adt.MyArrayI;
import arrayvisitors.adt.MyArrayList;
import arrayvisitors.adt.MyArrayListI;
import arrayvisitors.util.*;
import arrayvisitors.visitors.CommonIntsVisitor;
import arrayvisitors.visitors.MissingIntsVisitor;
import arrayvisitors.visitors.PopulateMyArrayVisitor;
import arrayvisitors.visitors.VisitorI;

/**
 * @author John Doe
 *
 */
public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 2;

	public static void main(String[] args) throws Exception {

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if ((args.length != 5) || (args[0].equals("${input1}")) || (args[1].equals("${input2}")) || (args[2].equals("${commonintsout}")) || (args[3].equals("${missingintsout}")) || (args[4].equals("${debug}"))) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);
		}
		System.out.println("Hello World! Lets get started with the assignment");

		try {
			MyLogger.setDebugValue(Integer.parseInt(args[4]));
		}catch (ArithmeticException e){
			e.printStackTrace();
			System.out.println("Illegal argument, debug level has to be a number 1-5");
		}
		//RESULTS instances
        Results resultsCommonInts = new Results();
        Results resultsMissingInts = new Results();

        //VISITORS
        VisitorI commonVisitor = new CommonIntsVisitor(resultsCommonInts);
        VisitorI missingVisitor = new MissingIntsVisitor(resultsMissingInts);

        //ELEMENTS Populating
		FileProcessor i1fileProcessor = new FileProcessor(args[0]);
		VisitorI populateMyArrayVisitor = new PopulateMyArrayVisitor(i1fileProcessor);
		MyArrayI myArray1 = new MyArray();
		populateMyArrayVisitor.visit(myArray1);

        FileProcessor i2fileProcessor = new FileProcessor(args[1]);
		populateMyArrayVisitor = new PopulateMyArrayVisitor(i2fileProcessor);
		MyArrayI myArray2 = new MyArray();
		populateMyArrayVisitor.visit(myArray2);

        MyArrayListI list = new MyArrayList();
        list.addArray(myArray1);
        list.addArray(myArray2);

        myArray1.accept(missingVisitor);
        myArray2.accept(missingVisitor);
        list.accept(commonVisitor);

        //WRITING results
        resultsCommonInts.writeToFile(args[2]);
        resultsCommonInts.writeToStdout();

        resultsMissingInts.writeToFile(args[3]);
        resultsMissingInts.writeToStdout();



	}
}
