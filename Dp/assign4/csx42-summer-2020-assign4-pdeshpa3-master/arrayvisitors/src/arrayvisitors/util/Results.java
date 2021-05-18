package arrayvisitors.util;

import java.io.FileWriter;
import java.io.IOException;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {
    private final StringBuilder content;
    private final FileWriter fw;

    public Results(String outputFileNameIn) throws IOException
    {
        MyLogger.writeMessage("Results constructor", MyLogger.DebugLevel.CONSTRUCTOR);
        content = new StringBuilder();
        fw = new FileWriter(outputFileNameIn);
    }


    /**
     * Writes content to Stringbuilder
     * @param newContent : content to append
     */
    public void appendToContent(Object newContent)
    {
        content.append(newContent);
    }

    /**
     * writes to file
     * @throws IOException : If writing to file fails
     */
    @Override
    public void writeToFile() throws IOException
    {   MyLogger.writeMessage("Print Results ", MyLogger.DebugLevel.RESULTS);
        fw.write(content.toString());
    }

    @Override
    public void writeToStdOut()
    {
        System.out.println(content.toString());
    }

    @Override
    public void close() throws IOException
    { fw.close();
    }
}
