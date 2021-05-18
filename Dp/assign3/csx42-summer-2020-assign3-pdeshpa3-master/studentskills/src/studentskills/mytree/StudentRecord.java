package studentskills.mytree;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StudentRecord implements Cloneable, ObserverI,SubjectI{
    private int bNum;
    private String fName;
    private String lName;
    private float gpa;
    private String major;
    private Set<String> skillSet = new HashSet<>();
    public StudentRecord left, right;
    private List<ObserverI> list = new ArrayList<>();

    public StudentRecord(){
        this.left = null;
        this.right = null;
    }

    /**
     * Overrided method
     * @return : returns Cloned Object
     * @throws CloneNotSupportedException : cloning may cause this
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Function to convert skills to String
     * @return : comma seperated skills
     */
    public String getStringSet(){
        StringBuilder skill = new StringBuilder();
        for(String s: skillSet){
            skill.append(s);
            skill.append(", ");}

       return skill.toString();

    }

    public StudentRecord getLeft() {
        return left;
    }

    public void setLeft(StudentRecord left) {
        this.left = left;
    }

    public StudentRecord getRight() {
        return right;
    }

    public void setRight(StudentRecord right) {
        this.right = right;
    }

    public int getbNum() {
        return bNum;
    }

    public void setbNum(int bNum) {
        this.bNum = bNum;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Set<String> getSkillSet() {
        return skillSet;
    }

    public void setSkillSet(Set<String> skillSet) {
        this.skillSet = skillSet;
    }

    /**
     * Function to update the current record, provided input
     * @param inp : input
     */
    @Override
    public void update(String inp) {
        String inp1[] = (inp.split(":")[0]).split(",");
        String inp2 = inp.split(":")[1];
   //     System.out.println("updating record");
        if(this.fName.equals(inp1[2])){
           // System.out.println("firstname modified");
            fName = inp2;
        }else if(lName.equals(inp1[2])){
           // System.out.println("lastname modified");
            lName = inp2;
        }else if(major.equals(inp1[2])){
          //  System.out.println("major modified");
            major = inp2;
        }else{
           // System.out.println("skills modief");
            skillSet.remove(inp1[2]);
            skillSet.add(inp2);
        }
    }

    /**
     * Function registers an observer
     * @param o : ObserverI object
     */
    @Override
    public void registerO(ObserverI o) {
        list.add(o);
    }

    /**
     * Function to notify observers about update given input
     * @param inp : input
     */
    @Override
    public void notifyO(String inp) {

        for( ObserverI o : list){
            o.update(inp);
        }
    }
}
