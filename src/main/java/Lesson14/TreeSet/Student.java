package Lesson14.TreeSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Student {

    private static int MIN = 1;
    private static int MAX = 10;

    private String lastname;
    private String firstname;
    private ArrayList<Integer> marks;

    public Student(String lastname, String firstname) throws IllegalArgumentException, NullPointerException{
        if (lastname.trim().equals("")) {
            throw new IllegalArgumentException("Lastname must be not null and not empty");
        }
        if (firstname.trim().equals("")) {
            throw new IllegalArgumentException("Firstname must be not null and not empty");
        }
        this.lastname = lastname;
        this.firstname = firstname;
        this.marks = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            marks.add(ThreadLocalRandom.current().nextInt(MIN, MAX + 1));
        }
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public ArrayList<Integer> getAllMarks() {
        return marks;
    }

    public Integer getMark(int lessonNo) throws NoSuchLessonException {
        if (lessonNo > 0 && lessonNo <= marks.size()) {
            return marks.get(lessonNo - 1);
        } else {
            throw new NoSuchLessonException("No such lesson");
        }
    }

    @Override
    public String toString() {
        return lastname + " " + firstname + " " + Arrays.toString(marks.toArray());
    }

    public static class ComparableStudent extends Student implements Comparable {

        public ComparableStudent(String lastname, String firstname) {
            super(lastname, firstname);
        }

        @Override
        public int compareTo(Object o) {
            try {
                Student otherStudent = (Student) o;
            } catch (ClassCastException ex) {
                System.out.println("Casting error");
            }
            return getFirstname().compareTo(((Student) o).getFirstname());
        }
    }
}
