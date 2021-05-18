package channelpopularity.context;

import channelpopularity.state.StateName;
import channelpopularity.video.VideoInter;

import java.util.Map;

public interface ContextI {

void setCurrentState(StateName nextState);

    Map<String, VideoInter> getVideoMap();

    Integer getSum();

    void setSum(Integer newVideoSumScore);

    Float getPopularityScore();

    void setPopularityScore();

    void processInput(String inpFileName) throws Exception;
}
