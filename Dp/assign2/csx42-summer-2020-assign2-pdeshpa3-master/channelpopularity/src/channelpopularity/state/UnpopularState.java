package channelpopularity.state;

import channelpopularity.context.ContextI;
import channelpopularity.util.Results;

public class UnpopularState extends AbstractState {
    private static final String STATE_NAME = StateName.UNPOPULAR.toString();
    private static final Integer adReqUpperLimit = 10;

    @Override
    public String getStateName()
    {
        return STATE_NAME;
    }

    @Override
    public Integer getAdRequestUpperLimit()
    {
        return adReqUpperLimit;
    }

    @Override
    public void addVideo(ContextI chanContext, Results results, String videoName) {
        super.addVideo(chanContext, results, videoName);
    }

    @Override
    public void removeVideo(ContextI chanContext, Results results, String videoName) {
        super.removeVideo(chanContext, results, videoName);
    }

    @Override
    public void processAdRequest(Results results, String videoName, Integer addLen) {
        super.processAdRequest(results, videoName, addLen);
    }

    @Override
    public void updateMetrics(ContextI chanContext, Results results, String videoName, Integer addV, Integer addL, Integer addD) {
        super.updateMetrics(chanContext, results, videoName, addV, addL, addD);
    }
}
