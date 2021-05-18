package textdecorators;

import textdecorators.util.InputDetails;

import java.util.List;
import java.util.Set;

public class SpellCheckDecorator implements AbstractTextDecorator {
    private AbstractTextDecorator atd;
    private InputDetails id;

    public SpellCheckDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
        atd = atdIn;
        id = idIn;
    }

    /**
     * Replaces all words from input lines which match the misspelled words and appends them with SPELLCHECK
     */
    @Override
    public void processInputDetails() {
        // Decorate input details.

        List<String> input = id.getList();
        Set<String> missWds = id.getMissWds();

        for(int i=0; i<input.size(); i++){
            for(String w: missWds){
                if(input.get(i).contains(w)){
                    input.set(i, input.get(i).replaceAll(w, PREFIXES.SPELLCHECK_+w+SUFFIXES._SPELLCHECK));
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
