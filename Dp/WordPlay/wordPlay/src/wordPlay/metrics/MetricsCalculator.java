package wordPlay.metrics;

import wordPlay.util.Results;

import java.text.DecimalFormat;
import java.util.List;

import static java.lang.System.exit;

public class MetricsCalculator {

    /**
     * Stores result instance and uses it to set metrics information
     */
    private Results results;
    private Metrics metrics;
    private DecimalFormat df = new DecimalFormat("#.##"); //precision upto 2 digits

    public MetricsCalculator(Results sharedResultInstance){
        results = sharedResultInstance;
        metrics = new Metrics();
        results.setMetrics(metrics);
    }

    /**
     * simply makes calls for metrics calculation to appropriate methods
     */
    public void calcMetrics(){
        try {
            calcAvgWordsPerSentence();
            calcAvgWordLength();
        }catch (Exception e){
            System.out.println("Empty File found Invalid Input Exception");
            exit(0);
        }
    }

    //avg num of words / sent  = total words / num of sent
    //avg word length = total word len / no of words

    /**
     * Calculates total words and number of sentences to compute avg words / sent
     */
    private void calcAvgWordsPerSentence(){
        List<List<String>> resultList = results.getResultList();
        int totalWords = 0;
        double avgWordsPerSent = 0;
        for(List list: resultList){
            totalWords+=list.size();
        }
        avgWordsPerSent = (double) totalWords / resultList.size();
        avgWordsPerSent = Double.valueOf(df.format(avgWordsPerSent)); //casting to double
        metrics.setAvgWordsPerSent(avgWordsPerSent);
        //System.out.println(avgWordsPerSent);
    }

    /**
     * Computes total word length and number of words to calculate avg word length
     */
    private void calcAvgWordLength(){
        List<List<String>> resultList = results.getResultList();
        int totalWordLength = 0;
        int totalWords = 0;
        double avgWordLength = 0;
        for(List list: resultList){

            for(int i=0; i<list.size(); i++){
                totalWordLength += ((list.get(i)+"").length());
            }
            totalWords+=list.size();
        }
        totalWordLength -= 2;
        avgWordLength = (double) (totalWordLength) / totalWords;
        avgWordLength = Double.valueOf(df.format(avgWordLength)); //casting to double
        metrics.setAvgWordLength(avgWordLength);
        //System.out.println(avgWordLength);
    }


}
