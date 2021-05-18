package textdecorators.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class InputDetails implements FileDisplayInterface{

    private List<String> list = new ArrayList<>();
    private String inp, misspelled, keys, op;
    private Set<String> missWds = new HashSet<>();
    private Set<String> keyWds = new HashSet<>();

    public InputDetails(String inp, String misspelled, String keys, String op){
        this.inp = inp;
        this.misspelled = misspelled;
        this.keys = keys;
        this.op = op;
    }

    /**
     * Methods to return data structures
     * @return : list, set, set
     */
    public List<String> getList(){return list; }
    public Set<String> getMissWds(){return missWds; }
    public Set<String> getKeyWds(){return keyWds; }

    /**
     * parses input file and stores data into list
     * @throws IOException : error occured while reading file
     */
    public void parseInputFile() throws IOException {
        StringBuilder sb = new StringBuilder();
        FileProcessor proc = new FileProcessor(inp);
        while(true){
            String data = proc.poll();
            if(data!=null && !data.equals("")){
                sb.append(data);
            }else{
                break;
            }
        }

        list = Arrays.asList(sb.toString().split("\\."));

        proc.close();
    }

    /**
     * Parses misspelled words file and stores it in a set
     * @throws IOException : error occured while reading file
     */
    public void parseMissWordsFile()throws IOException{
        FileProcessor proc = new FileProcessor(misspelled);
        while(true){
            String data = proc.poll();
            if(data!=null && !data.equals("")){
                missWds.add(data);
            }else{
                break;
            }
        }
        //System.out.println(missWds.toString());
        proc.close();
    }

    /**
     * Parses keywords file and stores it in keyWds set
     * @throws IOException : error occured while reading file
     */
    public void parseKeyWordsFile()throws IOException{
        FileProcessor proc = new FileProcessor(keys);
        while(true){
            String data = proc.poll();
            if(data!=null && !data.equals("")){
                keyWds.add(data);
            }else{
                break;
            }
        }
        //System.out.println(keyWds.toString());
        proc.close();
    }


    /**
     * Converts list to a string and write to the output file
     * @throws IOException
     */
    @Override
    public void writeToFile() throws IOException {
        StringBuilder res = new StringBuilder();
        for(String line: list){
            res.append(line);
            res.append(".");
        }
        FileWriter fw = new FileWriter(op);
        fw.write(res.toString());
        fw.close();
    }

    @Override
    public void close() throws IOException {

    }
}
