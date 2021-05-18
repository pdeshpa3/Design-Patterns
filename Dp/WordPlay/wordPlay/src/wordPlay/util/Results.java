package wordPlay.util;

import wordPlay.metrics.Metrics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {

    private List<List<String>> resultList = new ArrayList<>(); //will store processed lines
    private Metrics metrics;

    public void addToResultList(List<String> list){
        resultList.add(list);
    }
    public List<List<String>> getResultList(){
        return resultList;
    }

    public void setMetrics(Metrics metrics){
        this.metrics = metrics;
    }

    /**
     * Writes to output or metrics file depending on whether isMetrics is set
     * @param filename : output/metrics file absolute paths
     * @param isMetrics : true if metrics file else false
     */
    @Override
    public void writeToFile(String filename, boolean isMetrics) {
        try {
            FileWriter myWriter = new FileWriter(filename);

            if(isMetrics){
                myWriter.write("AVG_NUM_WORDS_PER_SENTENCE - "+metrics.getAvgWordsPerSent()+" \n"+"AVG_WORD_LENGTH - "+metrics.getAvgWordLength()+" \n");
            }else{
                for(List list: resultList){
                    for(int i=0; i<list.size(); i++){
                        myWriter.write(list.get(i)+" ");
                    }
                    myWriter.write("\n");
                }
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred trying to write to the output file.");
            e.printStackTrace();
        }
    }

    /**
     * Writes thee store results from resultList  to stdout
     */
    @Override
    public void writeToStdout() {
        //writes to console
        for(List list: resultList){
          for(int i=0; i<list.size(); i++){
              System.out.print(list.get(i)+" ");
          }
          System.out.println();
        }
        System.out.println("AVG_NUM_WORDS_PER_SENTENCE - "+metrics.getAvgWordsPerSent());
        System.out.println("AVG_WORD_LENGTH - "+metrics.getAvgWordLength());
    }


}
