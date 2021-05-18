package channelpopularity.state;

import channelpopularity.Exceptions.InvalidInputException;
import channelpopularity.context.ChannelContext;
import channelpopularity.entitiy.Metrics;
import channelpopularity.entitiy.Video;
import channelpopularity.operation.Operation;
import channelpopularity.util.Results;

import static java.lang.System.exit;

public abstract class AbstractState implements StateI {

    ChannelContext channelContext; //context is needed to change state
    private double popScore = 0;
    Results results;

    /**
     * This method checks if the add_request length is appropriate for currentState of Channel and approves/rejects add
     * @param video : video the add needs to be part of
     * @param len : length of add_req
     * @return : approve/reject (true/false)
     */
    @Override
    public boolean addReq(Video video, int len) {

        int found = channelContext.listContains(video);

        if(found == -1){
            System.out.println("video not found");
            exit(0);
        }else{
            //video exists so
            String st = channelContext.getCurrentState().toString();
            st = st.substring(0,st.length()-1);

            for(StateName stateName: StateName.values()){

                if(stateName.toString().equalsIgnoreCase(st)){
                    if(len>1 && len <= stateName.getAddUpperLimit()){//UNPOPULAR__AD_REQUEST::APPROVED
                        results.getSb().append(channelContext.getCurrentState()+"__AD_REQUEST::APPROVED\n");
                        return true;
                    }
                }
            }//end of for

        }
        results.getSb().append(channelContext.getCurrentState()+"__AD_REQUEST::REJECTED\n");
        return false;
    }

    /**
     * This method returns the current state the channel should be in
     * @return : statename the channels should be updated to
     */
    private StateName checkStateChange(){
        /*
        if(popScore>0 && popScore<1000)return StateName.UNPOPULAR;
        if(popScore>1000 && popScore<=10000)return StateName.MILDLY_POPULAR;
        if(popScore>10000 && popScore<=100000)return StateName.HIGHLY_POPULAR;
        if(popScore>100000) return StateName.ULTRA_POPULAR;
        */

        for(StateName st: StateName.values()){

            if(popScore>st.getLow() && popScore<=st.getHi() ){

                return st;
            }
        }

        return StateName.UNPOPULAR;
    }

    /**
     * This method takes in a metrics to update the video metrics and chnages state based on popularity score, updates results
     * @param metrics : metrics the video needs to be updated to
     * @param video : video
     * @throws InvalidInputException : if any metric falls below acceptable range
     */
    public void calculateMetrics(Metrics metrics, Video video)throws InvalidInputException {

        video.updateVideoMetrics(metrics);

        this.popScore = channelContext.updatePopularityScore();

        StateName st = checkStateChange();

        results.getSb().append(channelContext.getCurrentState()+"__"+"POPULARITY_SCORE_UPDATE::"+popScore+"\n");
        channelContext.setCurrentState(st);
    }

    /**
     * This method adds video to channel, updates its score and changes channel state if needed, updates results
     * @param video : video to be added to channel
     */
    @Override
    public void addVideo(Video video) {

        channelContext.getVideoList().add(video);
        this.popScore = channelContext.updatePopularityScore();
        StateName st = checkStateChange();

        results.getSb().append(channelContext.getCurrentState()+"__"+"VIDEO_ADDED::"+video.getVideoName()+"\n");
        channelContext.setCurrentState(st);
    }

    /**
     * This method removes the video from channel, updates the channel score and changes state if needed, updates results
     * @param video : video to be removed
     */
    @Override
    public void removeVideo(Video video) {
        int found = channelContext.listContains(video);

        if(found == -1){
            System.out.println("video not found");
        }else{
            channelContext.getVideoList().remove(found); //remove the video

            popScore = channelContext.updatePopularityScore(); //update the score
            StateName st = checkStateChange();

            results.getSb().append(channelContext.getCurrentState()+"__"+"VIDEO_REMOVED::"+video.getVideoName()+"\n");
            channelContext.setCurrentState(st);

        }
    }


}
