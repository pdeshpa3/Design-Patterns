package channelpopularity.state;

import channelpopularity.Exceptions.InvalidInputException;
import channelpopularity.context.ChannelContext;
import channelpopularity.entitiy.Metrics;
import channelpopularity.entitiy.Video;
import channelpopularity.util.Results;

public class HighlyPopular extends AbstractState{


    /**
     * Takes in channel context and results to delegate calls and update results
     * @param context : ChannelContext
     * @param results : Result instance to update the result
     */


    public HighlyPopular(ChannelContext context, Results results){ super.channelContext = context; super.results = results;}

    @Override
    public void addVideo(Video video) {
        super.addVideo(video);
    }

    @Override
    public void removeVideo(Video video) {
        super.removeVideo(video);
    }

    @Override
    public void calculateMetrics(Metrics metrics, Video video) throws InvalidInputException {
        super.calculateMetrics(metrics, video);
    }

    @Override
    public boolean addReq(Video video, int len) {
        return super.addReq(video, len);
    }

    @Override
    public String toString() {
        return StateName.HIGHLY_POPULAR.toString()+"_";
    }
}
