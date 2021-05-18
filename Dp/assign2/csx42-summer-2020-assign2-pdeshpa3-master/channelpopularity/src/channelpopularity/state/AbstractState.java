package channelpopularity.state;

import channelpopularity.context.ContextI;
import channelpopularity.util.Results;
import channelpopularity.video.Video;
import channelpopularity.video.VideoInter;

import java.util.Map;

public abstract class AbstractState implements StateI {

    @Override
    public void addVideo(ContextI chanContext, Results results,
                         String videoName)
    {
        Map<String, VideoInter> videoMap = chanContext.getVideoMap();
        VideoInter newVideo = new Video(videoName);
        videoMap.put(videoName, newVideo);

        //setting the score
        chanContext.setPopularityScore();

        //appending to results
        results.appendToContent(getStateName());
        results.appendToContent("__VIDEO_ADDED::"+videoName+"\n");

        setNewState(chanContext);
    }

    @Override
    public void removeVideo(ContextI chanContext, Results results,
                            String videoName)
    {
        Map<String, VideoInter> videoMap = chanContext.getVideoMap();
        VideoInter videoR = videoMap.get(videoName);
        videoMap.remove(videoName);
        int sum = chanContext.getSum() -  videoR.getVideoPScore();

//results.appendToContent(getStateName());
        chanContext.setSum(sum);
        chanContext.setPopularityScore();

        //appending
        results.appendToContent(getStateName());
        results.appendToContent("__VIDEO_REMOVED::"+videoName+"\n");
        setNewState(chanContext);
    }

    @Override
    public void updateMetrics(ContextI chanContext, Results results, String videoName, Integer addV, Integer addL, Integer addD)
    {
        Map<String, VideoInter> videoMap = chanContext.getVideoMap();
        VideoInter video = videoMap.get(videoName);//video.updateVideoMetrics(addV, addL, addD);
        int iScore = video.getVideoPScore();
        video.updateVideoMetrics(addV, addL, addD);
        int aScore = video.getVideoPScore();

        //set new sum and update score
        chanContext.setSum(chanContext.getSum() - iScore + aScore);
        chanContext.setPopularityScore();

        results.appendToContent(getStateName());
        results.appendToContent("__POPULARITY_SCORE_UPDATE::"+chanContext.getPopularityScore()+"\n");
        setNewState(chanContext);
    }

    /**
     *
     * @param results
     * @param videoName
     * @param addLen
     */
    @Override
    public void processAdRequest(Results results, String videoName, Integer addLen)
    {
        if(addLen <= 0)
        { throw new IllegalArgumentException("Length invalid");
        }

       if(addLen<getAdRequestUpperLimit()){
           //add req approved
           results.appendToContent(getStateName()+"__AD_REQUEST::"+"APPROVED\n");
           return;
       }
        results.appendToContent(getStateName()+"__AD_REQUEST::"+"REJECTED\n");
    }

    /**
     * Changes state after checking popularity score
     * @param channelContext :  channelContext
     */
    private void setNewState(ContextI channelContext)
    {
        StateName newState = null;

        Float pScore = channelContext.getPopularityScore();
        if(pScore >= 0f && pScore <= 1000f) newState = StateName.UNPOPULAR;
        else if(pScore > 1000f && pScore <= 10000f) newState = StateName.MILDLY_POPULAR;
        else if(pScore > 10000f && pScore <= 100000f) newState = StateName.HIGHLY_POPULAR;
        else if(pScore > 100000f && pScore <= Integer.MAX_VALUE) newState = StateName.ULTRA_POPULAR;

        channelContext.setCurrentState(newState);
    }

    public abstract String getStateName();

    protected abstract Integer getAdRequestUpperLimit();
}
