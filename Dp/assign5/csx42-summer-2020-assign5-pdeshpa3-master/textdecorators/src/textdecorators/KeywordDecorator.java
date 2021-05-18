package textdecorators;

import textdecorators.util.InputDetails;

import java.util.List;
import java.util.Set;

public class KeywordDecorator implements AbstractTextDecorator {
    private AbstractTextDecorator atd;
    private InputDetails id;

    public KeywordDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
        atd = atdIn;
        id = idIn;
    }

    /**
     * finds the words from keyWds and replace them with KEYWORD_ & _KEYWORD
     */
    @Override
    public void processInputDetails() {
        // Decorate input details.

        List<String> input = id.getList();
        Set<String> keyWds = id.getKeyWds();

        for(int i=0; i<input.size(); i++){
            for(String w: keyWds){
                if(input.get(i).toLowerCase().contains(w.toLowerCase())){
                    int wdI = input.get(i).toLowerCase().indexOf(w.toLowerCase());
                    String wd = input.get(i).substring(wdI, wdI+w.length());
                    input.set(i, input.get(i).replaceAll(wd, PREFIXES.KEYWORD_+wd+SUFFIXES._KEYWORD));

                }
            }
        }

        System.out.println(input.toString());
        // Forward to the next decorator, if any.
        if (null != atd) {
            atd.processInputDetails();
        }
    }
}
