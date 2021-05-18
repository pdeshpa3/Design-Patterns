package studentskills.mytree;

import studentskills.util.MyLogger;
import studentskills.util.Results;

import java.io.IOException;
import java.util.Arrays;

public class TreeHelper {

    BinarySearchTreeI Rep0, Rep1, Rep2;
    String out1, out2, out3;

    public TreeHelper(String o1, String o2, String o3){
        MyLogger.writeMessage("TreeHelper Constructor", MyLogger.DebugLevel.CONSTRUCTOR);
        Rep0 = new BinarySearchTree();
        Rep1 = new BinarySearchTree();
        Rep2 = new BinarySearchTree();
        out1 = o1;
        out2 = o2;
        out3 = o3;
    }

    /**
     * Inserts all records
     * @param sr0 : student record object
     * @throws CloneNotSupportedException : if cloning fails
     */
    public void insert(StudentRecord sr0)throws CloneNotSupportedException{

        StudentRecord sr1 = (StudentRecord) sr0.clone();
        StudentRecord sr2 = (StudentRecord) sr0.clone();
        setup(sr0, sr1, sr2);

        Rep0.insert(sr0);
        Rep1.insert(sr1);
        Rep2.insert(sr2);

    }

    /**
     * Setsup Observers and Listeners
     * @param sr0 : record 0
     * @param sr1 : cloned record 1
     * @param sr2 : cloned record 2
     */
    void setup(StudentRecord sr0, StudentRecord sr1, StudentRecord sr2){
        sr0.registerO(sr1);
        sr0.registerO(sr2);

        sr1.registerO(sr0);
        sr1.registerO(sr2);

        sr2.registerO(sr0);
        sr2.registerO(sr1);

    }

    /**
     * Searches for the node and updates it and observers
     * @param inp : input
     */
    public void modify(String inp){
        MyLogger.writeMessage("Updating record", MyLogger.DebugLevel.UPDATE_RECORD);
        String inp1[] = (inp.split(":")[0]).split(",");
        String inp2 = inp.split(":")[1];


        StudentRecord sr = null;
        if(new Integer(inp1[0]) == 0)
         sr = Rep0.search(new Integer(inp1[1]));
        else if(new Integer(inp1[0]) == 1) sr = Rep1.search(new Integer(inp1[1]));
        else if(new Integer(inp1[0]) == 2) sr = Rep2.search(new Integer(inp1[1]));


        if(sr!=null){
            sr.update(inp);
            sr.notifyO(inp);
        }

    }

    /**
     * Passes results instances to inorder and writes to file using appropriate methods
     * @throws IOException : Writing to file may cause exception
     */
    public void printNodes()throws IOException {
        Results r0 = new Results(out1);
        Results r1 = new Results(out2);
        Results r2 = new Results(out3);
        Rep0.inorder(r0);
        Rep1.inorder(r1);
        Rep2.inorder(r2);

        r0.writeToFile();
        r0.close();

        r1.writeToFile();
        r1.close();

        r2.writeToFile();
        r2.close();
    }

}
