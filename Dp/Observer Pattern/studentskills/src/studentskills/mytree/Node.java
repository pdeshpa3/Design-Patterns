package studentskills.mytree;

import studentskills.observerpattern.ObserverI;
import studentskills.observerpattern.SubjectI;

import java.util.ArrayList;
import java.util.List;


public class Node implements Cloneable, SubjectI, ObserverI {

    private StudentRecord studentRecord;
    private int key;
    private Node left, right;
    private List<ObserverI> observers;

     Node(int key){
        this.key = key;
        this.left = null;
        this.right = null;
        this.observers = new ArrayList<>();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

     StudentRecord getStudentRecord() {
        return studentRecord;
    }

     void setStudentRecord(StudentRecord studentRecord) {
        this.studentRecord = studentRecord;
    }

     int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

     Node getLeft() {
        return left;
    }

     void setLeft(Node left) {
        this.left = left;
    }

     Node getRight() {
        return right;
    }

     void setRight(Node right) {
        this.right = right;
    }

    @Override
    public void registerObserver(ObserverI o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(ObserverI o) {
        //do something
    }

    /**
     * This method calls appropriate methods to update values
     * @param type : type of Update - INSERT/MODIFY
     * @param oldValue : old value to be updated
     * @param newValue : new value to update into
     * @param studentRecord : student record to use for update
     */
     void valuesChanged(UpdateCallTypes type, String oldValue, String newValue, StudentRecord studentRecord){
        update(type, oldValue, newValue, studentRecord); //update the node itself
        notifyObservers(type, oldValue, newValue, studentRecord); //update on corresponding replica nodes

    }

    /**
     * This method updates all observers the node has
     * @param type : INSERT/MODIFY
     * @param oldValue : old value to be updated
     * @param newValue : new value to update into
     * @param studentRecord : student record to use for update
     */
    @Override
    public void notifyObservers(UpdateCallTypes type, String oldValue, String newValue, StudentRecord studentRecord) {
        for(ObserverI obs: observers){
           obs.update(type, oldValue, newValue, studentRecord);
        }
    }

    /**
     * This method takes a student record and assigns its latest values to current student record
     * @param studentRecord : contains latest values
     */
    private void insertBasedCall(StudentRecord studentRecord){

        if(studentRecord!=null){
            this.studentRecord.setFirstName(studentRecord.getFirstName());
            this.studentRecord.setLastName(studentRecord.getLastName());
            this.studentRecord.setMajor(studentRecord.getMajor());
            this.studentRecord.setGpa(studentRecord.getGpa());
            this.studentRecord.getSkills().addAll(studentRecord.getSkills());
        }

    }

    /**
     * This method checks if INSERT/MODIFY type call and makes appropriate updates to the current record
     * @param type : INSERT/MODIFY
     * @param oldValue : old value to be updated
     * @param newValue : new value to update into
     * @param studentRecord : student record to use for update
     */
    @Override
    public void update(UpdateCallTypes type, String oldValue, String newValue, StudentRecord studentRecord) {

        if(type.toString().equals("INSERT")){
            insertBasedCall(studentRecord);

        }else {

           // System.out.println(oldValue+" "+newValue+" "+this.studentRecord.getFirstName());
            if (this.studentRecord.getFirstName().equals(oldValue) || this.studentRecord.getFirstName().equals(newValue)) {
                this.studentRecord.setFirstName(newValue);
            } else if (this.studentRecord.getLastName().equals(oldValue) || this.studentRecord.getLastName().equals(newValue)) {
                this.studentRecord.setLastName(newValue);
            } else if (this.studentRecord.getMajor().equals(oldValue) || this.studentRecord.getMajor().equals(newValue)) {
                this.studentRecord.setMajor(newValue);
            } else {
                this.studentRecord.changeSkill(oldValue, newValue);
            }

        }//end of else
    }


}
