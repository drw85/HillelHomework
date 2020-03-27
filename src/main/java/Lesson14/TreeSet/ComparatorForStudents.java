package Lesson14.TreeSet;

import java.util.Comparator;

public class ComparatorForStudents implements Comparator{
    @Override
    public int compare(Object o1, Object o2) throws NullPointerException{
            Student student1 = (Student) o1;
            Student student2 = (Student) o2;
            return student1.getLastname().compareTo(student2.getLastname());
    }
}
