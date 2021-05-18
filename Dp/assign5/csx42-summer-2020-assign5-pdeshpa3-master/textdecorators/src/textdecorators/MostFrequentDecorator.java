package textdecorators;

import textdecorators.util.InputDetails;

import java.util.*;

public class MostFrequentDecorator implements AbstractTextDecorator {
    private AbstractTextDecorator atd;
    private InputDetails id;
    private Map<String, Integer> map = new HashMap<>();
    private int maxO = 1;
    private String outWd = "";

    public MostFrequentDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
        atd = atdIn;
        id = idIn;
    }

    /**
     * Finds word which occurs maximum times
     * @param list : input
     */
    private void findMax(List<String> list){
        for(String st: list){
            for(String wd: st.split(" ")){

                if(map.containsKey(wd.toLowerCase())){
                    map.put(wd.toLowerCase(), map.get(wd.toLowerCase())+1);
                }else{
                    map.put(wd.toLowerCase(), 1);
                }
            }
        }


        for(Map.Entry<String, Integer> entry: map.entrySet()){
            if(entry.getValue()>=maxO){
                maxO = entry.getValue();
                outWd = entry.getKey();
            }
        }
    }

    /**
     * Replaces the word that occurs most time with MOST_FREQUENT_ & _MOST_FREQUENT
     */
    @Override
    public void processInputDetails() {
        // Decorate input details.

        List<String> input = id.getList();
        Set<String> keyWds = id.getKeyWds();
        findMax(input);

        for(int i=0; i<input.size(); i++){

                if(input.get(i).toLowerCase().contains(outWd.toLowerCase())){
                    int wdI = input.get(i).toLowerCase().indexOf(outWd.toLowerCase());
                    String wd = input.get(i).substring(wdI, wdI+outWd.length());
                    input.set(i, input.get(i).replaceAll(wd, PREFIXES.MOST_FREQUENT_+wd+SUFFIXES._MOST_FREQUENT));

                }

        }

        // Forward to the next decorator, if any.
        if (null != atd) {
            atd.processInputDetails();
        }
    }
}
