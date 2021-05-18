package channelpopularity.operation;

import java.util.HashMap;
import java.util.Map;

public enum Operation {
    ADD_VIDEO, REMOVE_VIDEO, METRICS, AD_REQUEST;
    private static final Map<String, Operation> oprMap = new HashMap<>();
    static
    {
        createMap();
    }

    private static void createMap()
    {
        for(Operation oper : Operation.values())
            oprMap.put(oper.toString(), oper);

    }

    public static Operation getOpr(String opName)
    {
        return oprMap.get(opName);
    }
}
