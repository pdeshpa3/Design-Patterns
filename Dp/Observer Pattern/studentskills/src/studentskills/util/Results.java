package studentskills.util;


import java.io.FileWriter;
import java.io.IOException;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {
	private StringBuilder sb = new StringBuilder();
	private  FileWriter myWriter;

    public StringBuilder getSb() {
        return sb;
    }


    public void print()
    {
        System.out.println(sb.toString());
    }

    /**
     * This method writes output to outputFile
     * @param output : output file
     * @throws IOException : If writing to file not possible
     */
    @Override
    public void writeToFile(String output) throws IOException {
        myWriter = new FileWriter(output);
        myWriter.write(sb.toString());

    }

    /**
     * This method writes to error file
     * @param data : data to write
     * @param errFile : error File to write into
     * @throws IOException : i write not possible
     */
    public void writeToError(String data, String errFile) throws IOException {
        myWriter = new FileWriter(errFile);
        myWriter.write(data);
    }

    /**
     * This method closes the write instance
     * @throws IOException : if close not possible
     */
    public void closeFileWrite()throws IOException{
        if(myWriter!=null)
        myWriter.close();
    }

    /**
     * This method writes to stdout
     */
    @Override
    public void writeToStdout() {
        System.out.println(sb.toString());
    }
}
