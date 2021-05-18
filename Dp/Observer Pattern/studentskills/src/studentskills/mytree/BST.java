package studentskills.mytree;

import studentskills.util.MyLogger;
import studentskills.util.Results;


/**
 *
 * Reference: https://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/
 */
public class BST implements BSTi{
    private Node root;
    private final int id;

    public BST(int id){
        MyLogger.writeMessage("BST constructor called", MyLogger.DebugLevel.CONSTRUCTOR);
        this.root = null;
        this.id = id;
    }

    /**
     * This method returns id for the current Replica of BST
     * @return
     */
    public int getId(){
        return id;
    }

    /**
     * This method invokes insertRec to insert a node
     * @param node : newly created node
     */
    @Override
    public void insert(Node node) {
        this.root = insertRec(this.root, node);
    }

    /**
     * This method inserts a new node in BST
     * @param root : root of BST
     * @param node : node to be inserted
     * @return : returns root
     */
    private Node insertRec(Node root, Node node){
        if (root == null) {
            root = node;
            return root;
        }

        if(node.getKey() <= root.getKey()){
            root.setLeft(insertRec(root.getLeft(), node));
        } else if (node.getKey() > root.getKey())
            root.setRight(insertRec(root.getRight(), node));

        return root;
    }



    @Override
    public Node search(int BNumber) {
        return search(this.root, BNumber);
    }

    /**
     * This method searches for a record using bNumber as key
     * @param root : BST root
     * @param key : BNumber
     * @return : returns node to be searched, if found
     */
    private Node search(Node root, int key)
    {
        // Base Cases: root is null or key is present at root
        if (root==null || root.getKey()==key)
            return root;

        // val is greater than root's key
        if (root.getKey() >= key)
            return search(root.getLeft(), key);

        // val is less than root's key
        return search(root.getRight(), key);
    }

    @Override
    public void inorder() {
        inorderRec(this.root);
    }

    /**
     * This method prints inorder of BST
     * @param root : root of BST
     */
    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.getLeft());
            System.out.println(root.getStudentRecord());
            inorderRec(root.getRight());
        }
    }

    /**
     * This method stores results in stringbuilder in inorder
     * @param root : root of BST
     * @param sb : StringBuilder
     */
    private void store(Node root, StringBuilder sb) {
        if (root != null) {
            store(root.getLeft(),sb);
            sb.append(root.getStudentRecord().getbNumber()+": "+root.getStudentRecord().getFirstName()+","+root.getStudentRecord().getLastName()+","+root.getStudentRecord().getGpa()+","+ root.getStudentRecord().getMajor()+","+String.join(", ", root.getStudentRecord().getSkills())+"\n");
            store(root.getRight(),sb);
        }
    }

    /**
     * This method invokes store to store results in StringBuilder taken from Result instance passed to it
     * @param results : instance to store result
     */
    public void printNodes(Results results){
        StringBuilder sb = results.getSb();
        store(this.root, sb);

    }
}
