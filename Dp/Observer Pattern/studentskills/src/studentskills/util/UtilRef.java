package studentskills.util;

import studentskills.mytree.BST;
import studentskills.mytree.BSTi;

/**
 * This is a singleton class for creating and returning ReplicaTree instances
 */
public class UtilRef {

    private static BSTi ReplicaTree0 = new BST(0);
    private static BSTi ReplicaTree1 = new BST(1);
    private static BSTi ReplicaTree2 = new BST(2);

    private UtilRef(){}

    /**
     * This method returns the instance based on tree id
     * @param id : Replica Tree id
     * @return : returns the replica tree instance
     */
    public static BSTi getReference(int id){

        switch(id){
            case 0: return ReplicaTree0;
            case 1: return ReplicaTree1;
            case 2: return ReplicaTree2;
            default: return ReplicaTree0;
        }

    }
}
