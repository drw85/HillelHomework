package Lesson14.TreeSet;

import java.math.BigDecimal;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        System.out.println("СОЗДАЕМ ОБЫЧНЫХ СТУДЕНТОВ (БЕЗ ИМПЛЕМЕНТАЦИИ ИНТЕРФЕЙСА COMPARABLE)");
        System.out.println("СОЗДАЕМ ДЕРЕВО С ПАРАМЕТРОМ ComparatorForStudents");

        TreeSet<Student> treeSetOfNotComparableStudents = new TreeSet(new ComparatorForStudents());
        Student student1 = null;
        Student student2 = null;
        Student student3 = null;
        Student bad_Student = null;
        try {
            student1 = new Student("Торвальдс", "Линус");
            student2 = new Student("Кнут", "Дональд");
            student3 = new Student("Бернерс-Ли", "Тим");
            bad_Student = new Student("", "");
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.toString());
        } catch (NullPointerException ex) {
            System.out.println(ex.toString());
        }
        if (student1 != null) {
            treeSetOfNotComparableStudents.add(student1);
        }
        if (student2 != null) {
            treeSetOfNotComparableStudents.add(student2);
        }
        if (student3 != null) {
            treeSetOfNotComparableStudents.add(student3);
        }
        if (bad_Student != null) {
            treeSetOfNotComparableStudents.add(bad_Student);
        }

        try {
            System.out.println(student1.getMark(5));
            System.out.println(student1.getMark(11));
        } catch (NoSuchLessonException ex) {
            System.out.println(ex.toString());
        } catch (NullPointerException ex) {
            System.out.println(ex.toString());
        }


        System.out.println(treeSetOfNotComparableStudents.toString());
        System.out.println("СОРТИРУЮТСЯ ПО ПРАВИЛАМ ComparatorForStudents (ФАМИЛИИ ПО АЛФАВИТУ)");
        System.out.println();

        System.out.println("СОЗДАЕМ ДЕРЕВО С ПАРАМЕТРОМ ComparatorForStudentsByMarks");
        System.out.println("ДОБАВЛЯЕМ ЭТИХ ЖЕ СТУДЕНТОВ В НОВОЕ ДЕРЕВО");

        TreeSet<Student> treeSetOfStudentsComparedByMarks = new TreeSet(new ComparatorForStudentsByMarks());

        treeSetOfStudentsComparedByMarks.add(student1);
        treeSetOfStudentsComparedByMarks.add(student3);
        treeSetOfStudentsComparedByMarks.add(student2);

        System.out.println(treeSetOfStudentsComparedByMarks.toString());
        System.out.println("СОРТИРУЮТСЯ ПО ПРАВИЛАМ ComparatorForStudentsByMarks (ПО ВОЗРАСТАНИЮ СРЕДНЕЙ ОЦЕНКИ)");
        System.out.println();

        System.out.println("СОЗДАЕМ СТУДЕНТОВ (С ИМПЛЕМЕНТАЦИЕЙ ИНТЕРФЕЙСА COMPARABLE)");
        System.out.println("СОЗДАЕМ ДЕРЕВО БЕЗ ПАРАМЕТРОВ");
        Student.ComparableStudent comparableStudent1 = new Student.ComparableStudent("Гослинг", "Джеймс");
        Student.ComparableStudent comparableStudent2 = new Student.ComparableStudent("Хейлсберг", "Андерс");
        Student.ComparableStudent comparableStudent3 = new Student.ComparableStudent("Цукерберг", "Марк");

        TreeSet<Student> treeSetOfComparableStudents = new TreeSet();
        treeSetOfComparableStudents.add(comparableStudent1);
        treeSetOfComparableStudents.add(comparableStudent2);
        treeSetOfComparableStudents.add(comparableStudent3);

        System.out.println(treeSetOfComparableStudents.toString());
        System.out.println("СОРТИРУЮТСЯ ПО ПРАВИЛАМ ОПИСАНОМУ В РЕАЛИЗАЦИИ ИМПЛЕМЕНТАЦИИ (ИМЕНА ПО АЛФАВИТУ)");
        System.out.println();

        System.out.println("СОЗДАЕМ ДЕРЕВО С ПАРАМЕТРОМ ComparatorForObjectsByHashCode");

        TreeSet treeSetOfObjects = new TreeSet(new ComparatorForObjectsByHashCode());

        Integer someInt = 1;
        Student someStudent = new Student("СТУДЕНТ", "НЕ COMPARABLE");
        BigDecimal someBigDecimal = new BigDecimal(2);
        String someString = "some text";
        Student.ComparableStudent someComparableStudent = new Student.ComparableStudent("СРАВНИВАЕМЫЙ", "СТУДЕНТ");

        treeSetOfObjects.add(someInt);
        treeSetOfObjects.add(someStudent);
        treeSetOfObjects.add(someBigDecimal);
        treeSetOfObjects.add(someString);
        treeSetOfObjects.add(someComparableStudent);

        System.out.println(treeSetOfObjects.toString());
        System.out.println("СОРТИРУЮТСЯ ПО ПРАВИЛАМ ComparatorForObjectsByHashCode (ПО HashCode)");


    }
}
