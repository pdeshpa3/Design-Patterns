package channelpopularity.util;


import java.io.FileWriter;
import java.io.IOException;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {
	private StringBuilder sb = new StringBuilder();

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
        FileWriter myWriter = new FileWriter(output);
        try {
            myWriter.write(sb.toString());
        }finally {
            myWriter.close();
        }


    }

    /**
     * This method writes to stdout
     */
    @Override
    public void writeToStdout() {
        System.out.println(sb.toString());
    }
}
