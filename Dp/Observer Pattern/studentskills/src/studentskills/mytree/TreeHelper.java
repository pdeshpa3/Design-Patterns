package studentskills.mytree;

import studentskills.util.MyLogger;
import studentskills.util.Results;
import studentskills.util.UtilRef;

import java.util.ArrayList;
import java.util.List;

public class TreeHelper {


    private BSTi ReplicaTree0, ReplicaTree1, ReplicaTree2;
    private List<Results> resultsList = new ArrayList<>();

    public TreeHelper() {
         this.ReplicaTree0 = UtilRef.getReference(0);
         this.ReplicaTree1 = UtilRef.getReference(1);
         this.ReplicaTree2 = UtilRef.getReference(2);
         resultsList.add(new Results());
         resultsList.add(new Results());
         resultsList.add(new Results());
    }

    /**
     * This method creates a node and its replicas and sets up obsrvers and listeners
     * @param studentRecord : student record to insert into BST
     */
    public void insert(StudentRecord studentRecord){

        Node node = ReplicaTree0.search(studentRecord.getbNumber()); //search for the node
        if(node!=null){
            //node exists
            MyLogger.writeMessage("Update type insert ", MyLogger.DebugLevel.UPDATE_INSERT);
            node.valuesChanged(UpdateCallTypes.INSERT, "", "", studentRecord);

        }else{
            MyLogger.writeMessage("Inserting new node ", MyLogger.DebugLevel.INSERT);
            Node node0 = new Node(studentRecord.getbNumber());
            //System.out.println(node0);
            node0.setStudentRecord(studentRecord);

            ReplicaTree0.insert(node0);
            try {
                //setting up observers and listeners

                Node clone1 = (Node) node0.clone();
                clone1.setStudentRecord( (StudentRecord) studentRecord.clone());

                Node clone2 = (Node) node0.clone();
                clone2.setStudentRecord( (StudentRecord) studentRecord.clone());

                node0.registerObserver(clone1);
                node0.registerObserver(clone2);

                clone1.registerObserver(node0);
                clone1.registerObserver(clone2);

                clone2.registerObserver(node0);
                clone2.registerObserver(clone1);

                ReplicaTree1.insert(clone1);
                ReplicaTree2.insert(clone2);

            }catch(Exception e){
                System.err.println("Clone not supported exception");
            }
        }//end of else

    }

    /**
     * This method gets the ReplicaTree on the basis of replicaNum and calls update on node
     * @param replicaNum : replicaNum for ReplicaTree
     * @param bNum : bNumber to search the required node
     * @param oldValue : old value
     * @param newValue : new value
     */
    public void modify(int replicaNum, int bNum, String oldValue, String newValue){
        MyLogger.writeMessage("update type modify", MyLogger.DebugLevel.UPDATE_MODIFY);
        BSTi ReplicaTree = (BST) UtilRef.getReference(replicaNum);
        Node node = ReplicaTree.search(bNum);

        node.valuesChanged(UpdateCallTypes.MODIFY, oldValue, newValue, null);

    }

    public Node search(int BNumber) {
        Node node = ReplicaTree0.search(BNumber);
        return node;
    }


    /**
     * This method calls printNodes to store result into the stringBuilder
     * @return : List of results instances
     */
    public List<Results> inorder() {


        ReplicaTree0.printNodes(resultsList.get(0));

        ReplicaTree1.printNodes(resultsList.get(1));

        ReplicaTree2.printNodes(resultsList.get(2));

        return resultsList;

    }
}
