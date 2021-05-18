package studentskills.mytree;

import java.util.HashSet;
import java.util.Set;

public class StudentRecord implements Cloneable{
    private int bNumber;
    private String firstName;
    private String lastName;
    private double gpa;
    private String major;
    private Set<String> skills = new HashSet<>(); //max 10


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * This method changes skills from old to new value
     * @param oldValue : old value to be changed
     * @param newValue : new value to change into
     */
    public void changeSkill(String oldValue, String newValue){

            skills.remove(oldValue);
            skills.add(newValue);
    }

    public Set<String> getSkills() {
        return skills;
    }

    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }

    public int getbNumber() {
        return bNumber;
    }

    public void setbNumber(int bNumber) {
        this.bNumber = bNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }


    @Override
    public String toString() {
        return this.bNumber+":"+this.firstName+","+this.lastName+","+this.gpa+","+this.major+","+String.join(",", skills);
    }
}
