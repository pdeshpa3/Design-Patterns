package wordPlay.driver;

import wordPlay.handler.WordRotation;
import wordPlay.metrics.MetricsCalculator;
import wordPlay.util.FileProcessor;
import wordPlay.util.Results;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;

import static java.lang.System.exit;

/**
 * @author Shreya Vinay Desai
 *
 */
public class Driver {

    private Results sharedResults = new Results();

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


        Driver driver = new Driver();

        driver.getStarted(args[0], args[1], args[2]);

        if( (new File(args[0])).length()<=0){
            System.out.println("Invalid Input- Empty file found");
            exit(0);
        }

	}

    /**
     * This method starts the processing and invokes Calculation methods
     * @param arg : inputfile
     * @param outputFile : outputfile
     * @param metricsFile : metricsfile
     */
	private void getStarted(String arg, String outputFile, String metricsFile){
	    try {
            processWords(arg);

            MetricsCalculator calculator = new MetricsCalculator(sharedResults);
            calculator.calcMetrics();

            sharedResults.writeToStdout();
            sharedResults.writeToFile(outputFile, false);
            sharedResults.writeToFile(metricsFile, true);

        }catch(InvalidPathException e){
            e.printStackTrace();
            System.out.println("Invalid Path for file occurred");
            exit(0);
        }catch(SecurityException e){
            e.printStackTrace();
            System.out.println("Security Exception occurred");
            exit(0);
        }
    }

    /**
     *  This method checks if it contains characters or digits only.
     * @param word : Takes a word for validating
     * @return : true/false
     */
    private boolean isValid(String word){

        if(word.isEmpty() || word.matches("^[a-zA-Z0-9]+$") || word.substring(0,word.length()-1).matches("^[a-zA-Z0-9]+$")){
	       return true;
        }
	    System.out.println("Invalid input Exception occurred");
	    return false;
    }


    /**
     * This method gets word from FileProcessor and invokes method to rotate words and handles exceptions
     * @param inputFileName : input file provided by user
     */
	private void processWords(String inputFileName){
	    boolean newLine = false;
        try{
            FileProcessor fileProcessor = new FileProcessor(inputFileName);

            WordRotation rotator = new WordRotation(sharedResults);
            rotator.setNewLine(true);

            String st=fileProcessor.poll();
            if(!isValid(st)){
                exit(0);
            }
            int pos = 1;
            while(st.length()>0 ){

                if(newLine){
                    rotator.setNewLine(true);
                    newLine = false;
                }
                String result = rotator.rotateWord(st, pos);

                //System.out.print(result);
                pos++;
                if(st.contains(".")){
                    //System.out.print(".");
                    //System.out.println(" ");
                    newLine = true;
                    pos=1;
                }
                //System.out.print(" ");
                st=fileProcessor.poll();

                if(!isValid(st)){
                    exit(0);
                }

            }//end of  while

        }catch(FileNotFoundException e){
            e.printStackTrace();
            System.out.println("File not found Exception occurred");
            exit(0);
        }catch(InvalidPathException e){
            e.printStackTrace();
            System.out.println("Invalid Path for file Exception occurred");
            exit(0);
        }catch(SecurityException e){
            e.printStackTrace();
            System.out.println("Security Exception occurred");
            exit(0);
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("IO Exception occurred");
            exit(0);
        }catch (NullPointerException e){
            System.out.println("NULLPointer Exception occurred.");
            exit(0);
        }
    }
}
