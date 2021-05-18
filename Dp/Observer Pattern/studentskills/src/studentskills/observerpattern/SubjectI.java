package studentskills.observerpattern;

import studentskills.mytree.StudentRecord;
import studentskills.mytree.UpdateCallTypes;

public interface SubjectI {
    public void registerObserver(ObserverI o);
    public void removeObserver(ObserverI o);
    public void notifyObservers(UpdateCallTypes type, String oldValue, String newValue, StudentRecord studentRecord);
}
