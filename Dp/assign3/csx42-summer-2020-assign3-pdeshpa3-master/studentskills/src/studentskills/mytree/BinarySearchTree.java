package studentskills.mytree;

import studentskills.util.MyLogger;
import studentskills.util.Results;

import java.util.Collections;
import java.util.Set;

/**
 * Ref : geeksforgeeks.com
 */
public class BinarySearchTree implements BinarySearchTreeI{

    // Root of BST
    StudentRecord root;
    Results r;

    // Constructor
    BinarySearchTree() {
        root = null;
        MyLogger.writeMessage("BinarySearchTree constructor", MyLogger.DebugLevel.CONSTRUCTOR);
    }


    // This method mainly calls insertRec()
    public void insert(StudentRecord sr) {
        root = insertRec(root, sr);
    }

    /**
     * Function to insert a record recursively
     * @param root : root of tree
     * @param sr : StudentRecord
     * @return : StudentRecord Object
     */
    StudentRecord insertRec(StudentRecord root, StudentRecord sr) {

        /* If the tree is empty, return a new node */
        if (root == null) {
            root = sr;
            return root;
        }

        /* Otherwise, recur down the tree */
        if (sr.getbNum() < root.getbNum())
            root.left = insertRec(root.left, sr);
        else if (sr.getbNum() > root.getbNum())
            root.right = insertRec(root.right, sr);
        else if (sr.getbNum() == root.getbNum()){
            root.setSkillSet(sr.getSkillSet());
            root.setMajor(sr.getMajor());
            root.setlName(sr.getlName());
            root.setfName(sr.getfName());
        }

        /* return the (unchanged) node pointer */
        return root;
    }

    // This method mainly calls InorderRec()
    public void inorder(Results r)  {
        this.r = r;
        inorderRec(root);
    }

    /**
     * Function to traverse the tree in inorder
     * @param root : root of BST
     */
    void inorderRec(StudentRecord root) {
        if (root != null) {
            inorderRec(root.left);
            String x = (root.getbNum()+" "+ root.getSkillSet()+"\n");
            System.out.println(x);
            r.appendToContent(x);
            inorderRec(root.right);
        }
    }


    public StudentRecord search(int bNum){
        return search(root, bNum);
    }


    /**
     * A function to search for a record given key
     * @param root : tree root
     * @param key : bNumber
     * @return : StudentRecord object
     */
    public StudentRecord search(StudentRecord root, int key)
    {
        // Base Cases: root is null or key is present at root
        if (root==null || root.getbNum()==key)
            return root;

        // val is greater than root's key
        if (root.getbNum() > key)
            return search(root.left, key);

        // val is less than root's key
        return search(root.right, key);
    }



}
