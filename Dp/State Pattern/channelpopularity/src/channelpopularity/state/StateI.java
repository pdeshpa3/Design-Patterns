package channelpopularity.state;

import channelpopularity.Exceptions.InvalidInputException;
import channelpopularity.entitiy.Metrics;
import channelpopularity.entitiy.Video;

public interface StateI {

    void addVideo(Video video);
    void removeVideo(Video video);
    boolean addReq(Video video, int len);
    void calculateMetrics(Metrics metrics, Video video) throws InvalidInputException;

}
