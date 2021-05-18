package channelpopularity.context;

import channelpopularity.Exceptions.InvalidInputException;
import channelpopularity.entitiy.Metrics;
import channelpopularity.entitiy.Video;
import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.state.factory.SimpleStateFactoryI;
import channelpopularity.util.Results;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChannelContext implements ContextI, StateI {

    private StateI currentState;
    private List<Video> videoList = new ArrayList<>();
    private Map<StateName, StateI> map = new HashMap<>();//hashmap mapping statenames - instances
    private Results results = new Results();
    private DecimalFormat df = new DecimalFormat("#.##");


    /**
     *This constructor takes factory and stateNameList to get instances of State and map it with their state names
     * @param factory : Simple state factory
     * @param stateNameList : list containing all state names
     */
    public ChannelContext(SimpleStateFactoryI factory, List<StateName> stateNameList){
        for(StateName stateName: stateNameList){
            if(!map.containsKey(stateName)){
                map.put(stateName, factory.getState(stateName, this));
            }
        }
        currentState = map.get(StateName.UNPOPULAR); //initially channel is unpopular
    }

    /**
     * This is a getter method for getting the current state
     * @return currentState the Channel represents
     */
    public StateI getCurrentState() {
        return currentState;
    }

    /**
     * This method sets the current state with the state that maps to the passed statename
     * @param state : state that needs to be updated to
     */
    public void setCurrentState(StateName state){
        if(map.containsKey(state))
            currentState = map.get(state);
        else
            currentState = map.get(StateName.UNPOPULAR);
    }

    /**
     * This method takes the video name and returns the video from list
     * @param name: video name
     * @return : returns the corresponding video
     */
    public Video getVideo(String name){
        for(Video v: videoList){
            if(v.getVideoName().equalsIgnoreCase(name)){
                return v;
            }
        }
        return null;
    }

    /**
     * This method returns the index of video from the videoList
     * @param video : video to be returned from the list
     * @return : video index
     */
    public int listContains(Video video){
        int count = 0;

        for(Video v: videoList){

            if(v!=null && v.getVideoName().equals(video.getVideoName())){
                return count;
            }
            count++;
        }
        return -1;
    }

    /**
     *
     * @return : videos of the channel
     */
    public List<Video> getVideoList() {
        return videoList;
    }

    /**
     * This method updates the popularity score of Channel by averaging the score across all videos
     * @return : average popularity score of channel
     */
    public double updatePopularityScore(){
        //go through each video's popularity score and update this
        double score = 0;
        for(Video video: videoList){
            score += video.getScore();
        }
        return Double.parseDouble(df.format(score/videoList.size()));
    }

    /**
     * This delegates add video operation to currentState
     * @param video : video to add
     */
    @Override
    public void addVideo(Video video) {
        currentState.addVideo(video);
    }

    /**
     * This delegates remove video operation to currentState
     * @param video : video to remove
     */
    @Override
    public void removeVideo(Video video) {
        currentState.removeVideo(video);
    }

    /**
     * This method delegates add req operation to currentState
     * @param video : video to which add request should be processed on
     * @param len : length of add
     * @return : whether add req was accepted (true/false)
     */
    @Override
    public boolean addReq(Video video, int len) {

        return currentState.addReq(video, len);
    }

    /**
     * This method delegates metrics calculation to currentState
     * @param metrics : metrics holds views, likes, dislikes
     * @param video : video on which to calculate metrics
     * @throws InvalidInputException : metrics parameters may be invalid
     */
    @Override
    public void calculateMetrics(Metrics metrics, Video video) throws InvalidInputException {
        currentState.calculateMetrics(metrics, video);
    }

    public Results getResults() {
        return results;
    }
}
