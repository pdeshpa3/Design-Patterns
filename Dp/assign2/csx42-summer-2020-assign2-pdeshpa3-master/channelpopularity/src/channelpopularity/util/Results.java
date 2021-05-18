package channelpopularity.util;

import java.io.FileWriter;
import java.io.IOException;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {
    private final StringBuilder content;
    private final FileWriter fw;

    public Results(String outputFileNameIn) throws IOException
    {
        content = new StringBuilder();
        fw = new FileWriter(outputFileNameIn);
    }

    public void appendToContent(Object newContent)
    {
        content.append(newContent);
    }

    /**
     * writes to file
     * @throws IOException
     */
    @Override
    public void writeToFile() throws IOException
    { fw.write(content.toString());
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
