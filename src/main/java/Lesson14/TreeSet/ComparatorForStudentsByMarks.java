package Lesson14.TreeSet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.OptionalDouble;
import java.util.stream.IntStream;

public class ComparatorForStudentsByMarks implements Comparator {
    @Override
    public int compare(Object o1, Object o2) throws NullPointerException{
        Student student1 = (Student) o1;
        Student student2 = (Student) o2;
        double averageMarkOfStudent1 = getAverage(student1.getAllMarks());
        double averageMarkOfStudent2 = getAverage(student2.getAllMarks());
        return Double.compare(averageMarkOfStudent1, averageMarkOfStudent2);
    }

    private double getAverage(ArrayList<Integer> array) {
        double answer = 0;
        if (array.size() > 0) {
            double sum = 0;
            for (int j = 0; j < array.size(); j++) {
                sum += array.get(j);
            }
            answer = sum / array.size();
        }
        return answer;
    }
}
