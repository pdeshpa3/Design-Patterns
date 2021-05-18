package arrayvisitors.util;

public class MyLogger {

    public static enum DebugLevel {
        CONSTRUCTOR, COMMON_INTS, MISSING_INTS, PROCESSING_FILE, WRITING_OUTPUT, NONE
    }

    private static DebugLevel debugLevel;

    public static void setDebugValue (int levelIn) {
        switch (levelIn) {
            case 1: debugLevel = DebugLevel.CONSTRUCTOR; break;
            case 2: debugLevel = DebugLevel.COMMON_INTS; break;
            case 3: debugLevel = DebugLevel.MISSING_INTS; break;
            case 4: debugLevel = DebugLevel.PROCESSING_FILE; break;
            case 5: debugLevel = DebugLevel.WRITING_OUTPUT; break;
            default: debugLevel = DebugLevel.NONE; break;
        }
    }

    public static void setDebugLevel (DebugLevel level) {
        debugLevel = level;
    }

    public static void writeMessage (String messg, DebugLevel level ) {
        if (level == debugLevel) System.out.println("DEBUG :" + messg);
    }

    public String toString() {
        return "The debug level has been set to -  " + debugLevel;
    }
}
