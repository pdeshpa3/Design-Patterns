package channelpopularity.video;

public class Video implements VideoInter{
    private final String videoName;
    
    private Integer views, likes, dislikes;

    private Integer videoPScore;
    
    public Video(String videoNameIn)
    {
        videoName = videoNameIn;
        views = 0; likes = 0; dislikes = 0;
        videoPScore = 0;
    }

    /**
     * Updates metrics information for video
     * @param addV : views
     * @param addL : likes
     * @param addD : dislikes
     */

    @Override
    public void updateVideoMetrics(Integer addV, Integer addL, Integer addD)
    {
        if(addV < 0)
        {
            throw new IllegalArgumentException("views cannot be negative");
        }
        views += addV;
        likes += addL;
        dislikes += addD;
        if(likes < 0) {
            throw new IllegalArgumentException("Total likes cannot be negative");
        }
        if(dislikes < 0) {
            throw new IllegalArgumentException("Total dislikes cannot be negative");
        }

        videoPScore = views + (2 * (likes - dislikes) );
    }


    @Override
    public Integer getVideoPScore()
    {
        return videoPScore > 0 ? videoPScore : 0;
    }

    @Override
    public String getVideoName()
    {
        return videoName;
    }
}
