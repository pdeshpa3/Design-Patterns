package studentskills.driver;

import studentskills.util.FileProcessor;
import studentskills.util.MyLogger;

import java.io.IOException;

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
		//-Dinput -Dmodify -Dout1 -Dout2 -Dout3 -Derror -Ddebug
		if ((args.length != 7) || (args[0].equals("${input}")) || (args[1].equals("${modify}")) || (args[1].equals("${output1}")) || (args[1].equals("${output2}")) || (args[1].equals("${output3}")) || (args[1].equals("${error}")) || (args[1].equals("${debug}"))) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);
		}
		System.out.println("Hello World! Lets get started with the assignment");


		try {

			int arg = Integer.parseInt(args[6]);
			arg = arg>0 ? arg: 0;
			MyLogger.setDebugValue(arg);
			InputParser parser = new InputParser(args[0], args[1], args[2], args[3], args[4], args[5]);
			parser.parseInputFile();
			parser.parseModifyFile();
			parser.printToOutputFiles();


		}catch(IOException e){e.printStackTrace();}
		catch (NumberFormatException e){e.printStackTrace();}
		catch(Exception e){e.printStackTrace();}


	}
}
