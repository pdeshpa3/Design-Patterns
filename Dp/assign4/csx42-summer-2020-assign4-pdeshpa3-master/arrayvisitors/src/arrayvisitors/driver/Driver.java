package arrayvisitors.driver;



import arrayvisitors.adt.MyArray;
import arrayvisitors.adt.MyArrayI;
import arrayvisitors.adt.MyArrayList;
import arrayvisitors.adt.MyArrayListI;
import arrayvisitors.util.FileProcessor;
import arrayvisitors.util.MyLogger;
import arrayvisitors.util.Results;
import arrayvisitors.visitors.CommonIntsVisitor;
import arrayvisitors.visitors.IVisitor;
import arrayvisitors.visitors.MissingIntsVisitor;
import arrayvisitors.visitors.PopulateMyArrayVisitor;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author Pranav
 *
 */
public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 5;

	public static void main(String[] args) {

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if ((args.length != 5) || (args[0].equals("${input1}")) || (args[1].equals("${input2}") ) || (args[2].equals("${output1}") ) || (args[3].equals("${output2}") )  || (args[4].equals("${debug}") )) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);
		}
		System.out.println("Hello World! Lets get started with the assignment");

		try {

			MyLogger.setDebug(new Integer(args[4]));
			FileProcessor proc = new FileProcessor(args[0]);
			IVisitor popVisitor = new PopulateMyArrayVisitor(proc);

			MyArrayI myArray1 = new MyArray();
			popVisitor.visit(myArray1);

			FileProcessor proc2 = new FileProcessor(args[1]);
			IVisitor popVisitor2 = new PopulateMyArrayVisitor(proc2);

			MyArrayI myArray2 = new MyArray();
			popVisitor2.visit(myArray2);

			MyArrayListI list = new MyArrayList();
			list.addToList(myArray1);
			list.addToList(myArray2);
			Results resCommon = new Results(args[2]);
			Results resMiss = new Results(args[3]);
			IVisitor commonInts = new CommonIntsVisitor(resCommon);
			IVisitor missingInts = new MissingIntsVisitor(resMiss);

			myArray1.accept(missingInts);
			myArray2.accept(missingInts);
			list.accept(commonInts);

			resCommon.writeToStdOut();
			resMiss.writeToStdOut();
			resCommon.writeToFile();
			resCommon.close();

			resMiss.writeToFile();
			resMiss.close();


		}catch(Exception e){e.printStackTrace();}

	}


}
