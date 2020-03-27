package Lesson21.LambdaSorting;

import org.junit.Assert;
import org.junit.Test;

public class MyTreeSetImplTest {
    @Test
    public void getMarkByLesson_HappyCase() {
        Student student = new Student("A", "B");
        for (int i = 1; i <= 10; i++) {
            Assert.assertTrue(student.getMark(i) >= 0);
        }
    }

    @Test(expected = NoSuchLessonException.class)
    public void getMarkByLesson_FailCase() {
        Student student = new Student("A", "B");
        student.getMark(11);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creating_Student_With_Empty_Firstname(){
        Student student = new Student("lastname", "");
    }
    @Test(expected = NullPointerException.class)
    public void creating_Student_With_Null_Firstname(){
        Student student = new Student("lastname", null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void creating_Student_With_Empty_Lastname(){
        Student student = new Student("lastname", "");
    }
    @Test(expected = NullPointerException.class)
    public void creating_Student_With_Null_Lastname(){
        Student student = new Student("lastname", null);
    }
}
