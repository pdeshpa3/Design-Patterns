package channelpopularity.util;

import java.io.IOException;

public interface FileDisplayInterface {
    void writeToFile() throws IOException;
    void close() throws IOException;
}
