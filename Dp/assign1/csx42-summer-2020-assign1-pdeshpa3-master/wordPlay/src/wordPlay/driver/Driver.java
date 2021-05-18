package wordPlay.driver;

import wordPlay.handler.WordRotation;
import wordPlay.matrics.MetricsCalculator;
import wordPlay.util.FileProcessor;
import wordPlay.util.Results;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;

import static java.lang.System.exit;

/**
 * @author Pranav Deshpande
 *
 */
public class Driver {
	public static void main(String[] args) {

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if ((args.length != 3) || (args[0].equals("${input}")) || (args[1].equals("${output}")) || (args[2].equals("${metrics}"))) {
			System.err.println("Error: Incorrect number of arguments. Program accepts 3 arguments.");
			exit(0);
		}
		System.out.println("Hello World! Lets get started with the assignment");
		new Driver().testFileProcessor(args[0], args[1], args[2]);

	}

	/**
	 * Creating instances
	 * Processing the words through word rotation instance
	 * @param inp : input file
	 * @param op : output file
	 * @param met : metrix file
	 */
	public void testFileProcessor(String inp, String op, String met){
		try{

			FileProcessor fp = new FileProcessor(inp);
			WordRotation wd = new WordRotation();
			MetricsCalculator mc = new MetricsCalculator();
			Results results = new Results(op, met);

			wd.setResults(results);
			mc.setResults(results);

			int index = 1;
			while(true)
			{
				try{
					String word = fp.poll();
					if(word.isEmpty() || word.matches("^[a-zA-Z0-9]+$") ||  word.matches("^[a-zA-Z0-9]+[.]$"))
					  wd.rotate(word, index);
					else{
					  System.out.println("Invalid Input detected");
					  exit(0);
					}
					if(word==null) break;

					index++;
					if(word.contains(".")){
						index = 1; //end of the line
					}
				}catch(Exception e) {
					break;
				}

			}

			mc.calculate();
			results.writeToStdout();
			results.writeToFile();
		}catch(IOException | InvalidPathException | SecurityException excp){
			System.out.println("Exception Occured");
			excp.printStackTrace();
		}


	}
}
