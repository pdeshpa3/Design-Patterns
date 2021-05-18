package channelpopularity.video;

public interface VideoInter {

        void updateVideoMetrics(Integer addV, Integer addL, Integer addD);

        Integer getVideoPScore();

        String getVideoName();

}
