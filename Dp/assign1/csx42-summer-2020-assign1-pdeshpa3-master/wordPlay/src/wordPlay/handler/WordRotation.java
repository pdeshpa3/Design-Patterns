package wordPlay.handler;

import wordPlay.util.Results;

public class WordRotation {

    StringBuilder sb = new StringBuilder();
    Results results;
    boolean contained = false;

    public void setResults(Results r){
        results = r;
    }

    /**
     * Reference : Geeksforgeeks.com
     * Takes a word and rotates by d position
     * @param word : word to rotate
     * @param d : d positions
     */
    public void rotate(String word, int d){

        if(word.contains(".")){
            word = word.replace(".", "");
            contained = true;
        }
        d  = d % word.length();
        d = word.length()-d;

        String ans = word.substring(d) + word.substring(0, d);

        if(contained){
            ans = ans+".";
            contained = false;
        }

        results.getSb().append(ans+" ");

    }

}
