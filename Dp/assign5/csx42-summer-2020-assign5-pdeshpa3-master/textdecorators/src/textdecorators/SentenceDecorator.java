package textdecorators;

import textdecorators.util.InputDetails;

import java.util.List;

public class SentenceDecorator implements AbstractTextDecorator {

    private AbstractTextDecorator atd;
    private InputDetails id;

    public SentenceDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
        atd = atdIn;
        id = idIn;
    }


    /**
     * Appends beginning and ending of each line with BEGIN_SENTENCE_ & _END_SENTENCE
     */
    @Override
    public void processInputDetails() {
        // Decorate input details.

        List<String> input = id.getList();

        for(int i=0; i<input.size(); i++){
            String s = input.get(i);
            input.set(i, PREFIXES.BEGIN_SENTENCE__+s+SUFFIXES.__END_SENTENCE);
        }

        //System.out.println(input.toString());
        // Forward to the next decorator, if any.
        if (null != atd) {
            atd.processInputDetails();
        }
    }
}
