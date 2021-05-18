package wordPlay.metrics;

public class Metrics {
    /**
     * Stores metrics information for easy access
     */
    private double avgWordsPerSent = 0;
    private double avgWordLength = 0;

    //getters and setters

    public double getAvgWordsPerSent() {
        return avgWordsPerSent;
    }

    void setAvgWordsPerSent(double avgWordsPerSent) {
        this.avgWordsPerSent = avgWordsPerSent;
    }

    public double getAvgWordLength() {
        return avgWordLength;
    }

    void setAvgWordLength(double avgWordLength) {
        this.avgWordLength = avgWordLength;
    }
}
