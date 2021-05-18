package studentskills.util;

public class MyLogger {

    public static enum DebugLevel {
        INSERT, WRITE_OUTPUT, UPDATE_INSERT, CONSTRUCTOR, UPDATE_MODIFY, NONE
    };

    private static DebugLevel debugLevel;

    public static void setDebugValue (int levelIn) {
        switch (levelIn) {
            case 5: debugLevel = DebugLevel.INSERT; break;
            case 4: debugLevel = DebugLevel.WRITE_OUTPUT; break;
            case 2: debugLevel = DebugLevel.UPDATE_INSERT; break;
            case 1: debugLevel = DebugLevel.CONSTRUCTOR; break;
            case 3: debugLevel = DebugLevel.UPDATE_MODIFY; break;
            default: debugLevel = DebugLevel.NONE; break;
        }
    }

    public static void setDebugValue (DebugLevel level) {
        debugLevel = level;
    }

    public static void writeMessage (String messg, DebugLevel level ) {
        if (level == debugLevel) System.out.println("DEBUG :" + messg);
    }

    public String toString() {
        return "The debug level has been set to -  " + debugLevel;
    }
}
