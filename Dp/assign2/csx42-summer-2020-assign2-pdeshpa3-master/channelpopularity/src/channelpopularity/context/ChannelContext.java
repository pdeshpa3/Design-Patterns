package channelpopularity.context;

import channelpopularity.operation.Operation;
import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.state.factory.SimpleStateFactoryI;
import channelpopularity.util.FileProcessor;
import channelpopularity.util.Results;
import channelpopularity.video.VideoInter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChannelContext implements ContextI{
    private static final String METRICS_NAMES = "VIEWS|LIKES|DISLIKES";

    private StateI curState;
    private final Map<StateName, StateI> stateMap;
    private final Map<String, VideoInter> videoMap;

    private Float popularityScore = 0f;
    private Integer sumOfAllVideoScores = 0;

    private final Results results;

    /**
     * This constructor initializes stateMap
     * @param stateFactoryIn : object creator
     * @param stateNames : contains state names
     * @param resultsIn : Result object
     */
    public ChannelContext(SimpleStateFactoryI stateFactoryIn,
                          List<StateName> stateNames, Results resultsIn)
    {
        stateMap = new HashMap<>();
        videoMap = new HashMap<>();
        
        for(StateName stateName : stateNames)
        {
            stateMap.put(stateName, stateFactoryIn.create(stateName));
        }
        
        curState = stateMap.get(StateName.UNPOPULAR);

        results = resultsIn;

    }



    @Override
    public void processInput(String ip)throws Exception
    {
        FileProcessor fp = new FileProcessor(ip);

        String line = fp.poll();

        if(line == null)
        {
            throw new IllegalArgumentException("Input file empty");
        }

        while(line != null)
        {
            processLine(line);
            line = fp.poll();
        }
    }

    @Override
    public void setCurrentState(StateName nextState)
    {
        curState = stateMap.get(nextState);
    }

    @Override
    public Map<String, VideoInter> getVideoMap()
    {
        return videoMap;
    }

    @Override
    public Integer getSum()
    {
        return sumOfAllVideoScores;
    }

    @Override
    public void setSum(Integer newVideoSumScore)
    {
        sumOfAllVideoScores = newVideoSumScore;
    }

    @Override
    public Float getPopularityScore()
    {
        return popularityScore;
    }

    /**
     * Calculates popularity score
     */
    @Override
    public void setPopularityScore()
    {
        if(videoMap.size() == 0)
        {
            popularityScore = 0f;
        }
        else
        {
            popularityScore = ((float)sumOfAllVideoScores) / videoMap.size();
        }
    }

    /**
     * This method processes line and invokes methods on current state
     * @param line : line input
     * @throws IllegalArgumentException : Invalid input
     */

    private void processLine(String line)throws IllegalArgumentException
    {
        String[] splitLine = line.split("::");

        if(splitLine.length != 2) {
            throw new IllegalArgumentException("Incorrect line format ");
        }
        String[] oprLine = splitLine[0].split("__");
        String opr = oprLine[0];
        Operation oper = Operation.getOpr(opr);

        if(oper == null)
        {
            throw new IllegalArgumentException("Incorrect line format ");
        }

        String videoName = null;
        if(oprLine.length == 1)
        {
            videoName = splitLine[1];
        }
        else if(oprLine.length == 2)
        {
            videoName = oprLine[1];
        }
        else
        {
            throw new IllegalArgumentException("Incorrect line format ");
        }

        if(Operation.ADD_VIDEO.equals(oper))
        {
            if(videoMap.containsKey(videoName))
            {
                throw new IllegalArgumentException("video already part of channel");
            }
            curState.addVideo(this, results, videoName);
        }
         else if(Operation.REMOVE_VIDEO.equals(oper))
        {
            if(!videoMap.containsKey(videoName))
            {
                throw new IllegalArgumentException("video not present");
            }
            curState.removeVideo(this, results, videoName);
        }
         else if(Operation.METRICS.equals(oper))
        {
            if(!videoMap.containsKey(videoName))
            {
                throw new IllegalArgumentException("video not in channel");
            }
            try {
                final Map<String, Integer> metParams = getMet(
                        line, splitLine[1]);
                Integer addV = metParams.get("VIEWS");
                Integer addL = metParams.get("LIKES");
                Integer addD = metParams.get("DISLIKES");



            curState.updateMetrics(this, results, videoName, addV, addL, addD);
            }catch (Exception e){e.printStackTrace();}
        }
        else if(Operation.AD_REQUEST.equals(oper))
        {
            if(!videoMap.containsKey(videoName))
            {
                throw new IllegalArgumentException("format error");
            }
            int adLen=0;
            try
            {
                adLen = Integer.parseInt(splitLine[1].split("=")[1]);
            }
            catch(Exception e)
            {
                e.printStackTrace();
                throw new IllegalArgumentException("Incorrect line format ");
            }
            curState.processAdRequest(results, videoName, adLen);
        }
        else
        {
        }
    }

    /**
     * processes metrics information from input
     * @param line : line input
     * @param splitLine : [metrics]
     * @return : map containing metrics info
     * @throws Exception : Incorrect format
     */
    private Map<String, Integer> getMet(String line,
                                        String splitLine)throws Exception
    {
        splitLine = splitLine.substring(1, splitLine.length() - 1); //[metrics info]
        String[] metrArr = splitLine.split(",");

        Map<String, Integer> metPair = new HashMap<String, Integer>();
        
        for(String info : metrArr)
        {
            String[] metMap = info.split("=");

            if(metMap.length != 2)
            {throw new IllegalArgumentException("Incorrect metrics format");
            }
            String metKey = metMap[0];
            if(!metKey.matches(METRICS_NAMES))
            { throw new IllegalArgumentException("Incorrect metrics format ");
            }

            int val = 0;
            try
            {
                val = Integer.parseInt(metMap[1]);
            }
            catch(NumberFormatException e)
            {   e.printStackTrace();
                throw new IllegalArgumentException("Incorrect metrics format ");
            }
            metPair.put(metKey, val);
        }

        return metPair;
    }


}
