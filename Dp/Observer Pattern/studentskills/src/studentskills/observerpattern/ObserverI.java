package studentskills.observerpattern;

import studentskills.mytree.StudentRecord;
import studentskills.mytree.UpdateCallTypes;

public interface ObserverI {
    public void update(UpdateCallTypes type, String oldValue, String newValue, StudentRecord studentRecord);
}
