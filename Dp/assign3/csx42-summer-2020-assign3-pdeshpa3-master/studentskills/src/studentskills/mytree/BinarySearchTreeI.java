package studentskills.mytree;

import studentskills.util.Results;

public interface BinarySearchTreeI {
    void insert(StudentRecord sr);
    StudentRecord search(int bNum);
    void inorder(Results r);
}
