package channelpopularity.state;

import channelpopularity.context.ContextI;
import channelpopularity.util.Results;

public interface StateI {
    void addVideo(ContextI channelContext, Results results, String videoName);
    void removeVideo(ContextI channelContext, Results results, String videoName);
    void updateMetrics(ContextI channelContext, Results results, String videoName, Integer addV, Integer addL, Integer addD);
    void processAdRequest(Results results, String videoName, Integer addLen);
}
