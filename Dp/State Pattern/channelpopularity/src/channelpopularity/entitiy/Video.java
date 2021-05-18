package channelpopularity.entitiy;

import channelpopularity.Exceptions.InvalidInputException;

public class Video {
    private String videoName;
    private Metrics metrics;
    private int score = 0; // #Views + 2 * (#Likes - #Dislikes); pop score of video

    public Video(String name){
        this.videoName = name;
        metrics = new Metrics(0, 0 , 0);
    }

    public Metrics getMetrics() {
        return metrics;
    }

    /**
     * This method updates metrics for a video
     * @param metrics : metrics object that holds views, likes, dislikes
     * @throws InvalidInputException : checks if any metric is negative
     */
    public void updateVideoMetrics(Metrics metrics)throws InvalidInputException {
        int views = this.metrics.getViews();
        int likes = this.metrics.getLikes();
        int dislikes = this.metrics.getDislikes();

        int newViews = views+metrics.getViews();
        int newLikes = likes+metrics.getLikes();
        int newDislikes = dislikes+metrics.getDislikes();

        if(views<0 || newLikes<0 || newDislikes<0 )throw new InvalidInputException("Metrics falls below 0");

        this.metrics.setViews(newViews);
        this.metrics.setLikes(newLikes);
        this.metrics.setDislikes(newDislikes);

        calculateScore();
    }

    public int getScore(){
        return score;
    }

    /**
     * This method calculates popularity score for a video
     */
    private void calculateScore(){
        int sc = metrics.getViews() + 2*(metrics.getLikes() - metrics.getDislikes());
        this.score = Math.max(0, sc); //pop score cannot be negative

    }

    public String getVideoName() {
        return videoName;
    }



}
