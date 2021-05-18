package wordPlay.handler;

import wordPlay.util.Results;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

public class WordRotation {

    //We therefore need to rotate "Welcome" by 1 position, "to" by 2 positions,
    // "design" by 3 positons and "course" by 4 positions to the right.
    //eWelcom to igndes ernspatt ummers 2020.
    private Results results;
    private boolean isNewLine = false;
    private boolean removedPeriod = false;

    public WordRotation(Results sharedResultInstance){
        results = sharedResultInstance;
    }

    public void setNewLine(boolean value){
        isNewLine = value;
    }

    /**
     * This method takes a word from Driver and rotates it by specified index
     * @param word : word to process
     * @param index : word to process by index times
     * @return : returns the processed word
     */
    public String rotateWord(String word, int index){


        if(word==null ){
            System.out.println("Null pointer exception for word..");
            exit(0);
        }

        if(word.length() == 0)return ""; //empty string found

        if(word.contains(".")){
            word = word.substring(0,word.length()-1); //removing period
            removedPeriod = true;
        }

        index--;

        if(index >= word.length()){
            index = index % word.length(); //Exception case 2020 (index 6)
        }

        String result = "";
        try {
            result = word.substring(word.length() - index - 1) + word.substring(0, word.length() - index - 1);

        }catch (StringIndexOutOfBoundsException e){
            System.out.println(index);
            System.out.println("Exception for "+word);

        }
        //System.out.print(index+1);
        if(removedPeriod){
            result = result+".";
            removedPeriod = false;
        }
        storeWord(result);
        return result;
    }

    /**
     * This method stores the rotated word into the Results class
     * @param processedWord : rotated word
     */
    private void storeWord(String processedWord){
       if(isNewLine){
           //create a new list and add the processed words in it
           List<String> list = new ArrayList<>();
           results.addToResultList(list);

       }else{
           //use already created list; redundant
       }
       List<List<String>> resultList = results.getResultList();
       List<String> currentList = resultList.get(resultList.size()-1);
       currentList.add(processedWord);

       isNewLine = false;
    }

}
