package wordPlay.util;

import java.io.FileWriter;
import java.io.IOException;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {

    StringBuilder sb = new StringBuilder();
    double avgWds = 0;
    double avgLen = 0;
    String oF, mF;

    /**
     * stores output file and metrics file's absolute path
     * @param op : output file path
     * @param m : metrics file path
     */
    public Results(String op, String m){
        this.oF = op;
        this.mF = m;
    }

    public StringBuilder getSb(){
        return sb;
    }

    /**
     * stores parameters, avg words and avg length
     * @param w : avg words
     * @param l : avg length
     */
    public void setMetrics(double w, double l){
        this.avgWds = w;
        this.avgLen = l;
    }

    /**
     * write to output file and then to metrics file
     */
    @Override
    public void writeToFile() {
        //write to output file and then to metrics file
        try {
            FileWriter fw = new FileWriter(oF);
            String arr[] = sb.toString().split(" ");
            for(String s: arr){
                fw.write(s+" ");
                if(s.contains(".")){
                    fw.write("\n");
                }
            }
            fw.close();
            FileWriter fm = new FileWriter(mF);
            fm.write("AVG_NUM_WORDS_PER_SENTENCE - "+avgWds+"\n");
            fm.write("AVG_WORD_LENGTH - "+avgLen);
            fm.close();
        } catch (IOException e) {
            System.out.println("Couldn't write to file");
            e.printStackTrace();
        }

    }

    /**
     * Writes output to Stdout
     */
    @Override
    public void writeToStdout() {
        String arr[] = sb.toString().split(" ");
        for(String s: arr){
            System.out.print(s+" ");
            if(s.contains(".")){
                System.out.println();
            }
        }

        System.out.println("AVG_NUM_WORDS_PER_SENTENCE - "+avgWds);
        System.out.println("AVG_WORD_LENGTH - "+avgLen);
    }
}
