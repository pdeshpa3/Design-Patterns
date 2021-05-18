package wordPlay.matrics;

import wordPlay.util.Results;

public class MetricsCalculator {

    Results results;

    public void setResults(Results r){
        results = r;
    }

    /**
     * Calculate average number of words per sentence and average word length
     */
    public void calculate(){
        StringBuilder sb = results.getSb();
        double avgWds = 0;
        double words = 0;
        double len = 0;
        double sent = 0;
        double avgLen = 0;
        String arr[] = sb.toString().split(" ");
        for(String s: arr){
            words++;
            len = len + s.length();
            if(s.contains(".")){
                sent++;
            }
        }

        avgWds = words / sent;
        avgLen = (len-2) / words;
        results.setMetrics(avgWds, avgLen);
    }
}
