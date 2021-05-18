package studentskills.driver;

import studentskills.mytree.BSTi;
import studentskills.mytree.Node;
import studentskills.mytree.StudentRecord;
import studentskills.mytree.TreeHelper;
import studentskills.util.FileProcessor;
import studentskills.util.MyLogger;
import studentskills.util.Results;
import studentskills.util.UtilRef;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class InputParser {

    private String inputFile;
    private String modifyFile;
    private String outputFile0, outputFile1, outputFile2;
    private String errFile;
    private FileProcessor fileProcessor;
    private TreeHelper treeHelper;
    private Results err;

    /**
     * This constructor takes parameters and creates treeHelper instance
     * @param inputFile : input file
     * @param modifyFile : modify file
     * @param outputFile0 : outputfile for replica tree 0
     * @param outputFile1 : output file for replicatree 1
     * @param outputFile2 : output file for replica tree 2
     * @param errFile : error file
     */
     InputParser(String inputFile, String modifyFile, String outputFile0, String outputFile1, String outputFile2, String errFile){
        MyLogger.writeMessage("InputParser constructor", MyLogger.DebugLevel.CONSTRUCTOR);
        this.inputFile = inputFile;
        this.modifyFile = modifyFile;
        this.outputFile0 = outputFile0;
        this.outputFile1 = outputFile1;
        this.outputFile2 = outputFile2;
        this.errFile = errFile;
        treeHelper = new TreeHelper();
        err = new Results();

    }


    /**
     * This method invokes method on result instance to write to file
     * @throws IOException : can occur while writing to file
     */
     void printToOutputFiles()throws IOException{

         MyLogger.writeMessage("Writing to output file", MyLogger.DebugLevel.WRITE_OUTPUT);

        List<Results> resultList = treeHelper.inorder();

        Results results0 = resultList.get(0);
        results0.writeToFile(outputFile0);
        results0.closeFileWrite();
        System.out.println("Replica Tree 0");
        results0.writeToStdout();

        Results results1 = resultList.get(1);
        results1.writeToFile(outputFile1);
        results1.closeFileWrite();
         System.out.println("Replica Tree 1");
        results1.writeToStdout();

        Results results2 = resultList.get(2);
        results2.writeToFile(outputFile2);
        results2.closeFileWrite();
         System.out.println("Replica Tree 2");
        results2.writeToStdout();
    }

    /**
     * This method parses input from inputFile
     * @throws IOException
     */
     void parseInputFile()throws IOException {

            fileProcessor = new FileProcessor(inputFile);

            while(true){
                String input = fileProcessor.poll();
                if(input == null)break;
                StudentRecord record = createRecord(input);
                treeHelper.insert(record);

            }

    }//end of method

    /**
     * This method parses Modify File
     * @throws IOException
     */
    void parseModifyFile()throws IOException{
        fileProcessor = new FileProcessor(modifyFile);

        while(true){
            String input = fileProcessor.poll();
            if(input == null)break;
            parseModifiedData(input);

        }

    }

    /**
     * This method parses input and calls modify on treeHelper to modify nodes
     * @param input : input from input file
     * @throws IOException
     */
    private void parseModifiedData(String input)throws IOException, NumberFormatException{

         if(input.indexOf(":") == input.length()-1){
             err.writeToError("New Value missing from modify file ", errFile);
             err.closeFileWrite();
             return;
         }

        String part1[] = (input.split(":")[0]).split(",");
        String newValue = input.split(":")[1];

        int replicaNum = getInt(part1[0]);
        int bNum = getInt(part1[1]);
        String oldValue = part1[2];


        treeHelper.modify(replicaNum, bNum, oldValue, newValue);

    }

    /**
     * This method takes input and returns a student record
     * @param line : input
     * @return : student record created by the method
     * @throws IOException
     */
    private StudentRecord createRecord(String line)throws IOException, NumberFormatException{
         int BNum = getInt(line.split(":")[0]);
         String parameters[] = line.split(":")[1].split(",");

         String fName = parameters[0];
         String lastName = parameters[1];
         double gpa = Double.parseDouble(parameters[2]);
         String major = parameters[3];
         Set<String> skills = new HashSet<>();

         for(int i=4; i<parameters.length; i++)skills.add(parameters[i]);

         if(skills.size()>10){
             err.writeToError("Skills more than 10, incorrect input ",this.errFile);
             err.closeFileWrite();
         }

         StudentRecord record = new StudentRecord();
         record.setbNumber(BNum);
         record.setFirstName(fName);
         record.setLastName(lastName);
         record.setMajor(major);
         record.setGpa(gpa);
         record.setSkills(skills);

         return record;
    }


    /**
     * This method converts string to int
     * @param input : input string
     * @return : integer
     */
    private int getInt(String input)throws NumberFormatException{ //will throw an exception
         return Integer.parseInt(input);
    }

}
