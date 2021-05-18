package channelpopularity.entitiy;

public class Metrics {
    private int views;
    private int likes;
    private int dislikes;

    /**
     * This constructor assigns views, likes and dislikes to chass
     * @param views : number of views for a video
     * @param likes : number of likes for a video
     * @param dislikes : number of dislikes for a video
     */
    public Metrics(int views, int likes, int dislikes){
        this.views = views;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }
}
