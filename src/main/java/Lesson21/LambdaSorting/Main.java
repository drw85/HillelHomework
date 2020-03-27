package Lesson21.LambdaSorting;

import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    private static Scanner inputScanner;
    private static TreeSet<Student> studentsSet;

    public static void main(String[] args) {
        inputScanner = new Scanner(System.in);
        studentsSet = initSet();
        Comparator<Student> comparator;
        int selectedOption = -1;
        while (selectedOption != ComparingVars.values().length + 1) {
            selectedOption = getOption("ВЫБЕРИТЕ СПОСОБ СОРТИРОВКИ : ", ComparingVars.values());
            ComparingVars[] comparingVars = ComparingVars.values();
            ComparingVars currentCompareWay;
            currentCompareWay = comparingVars[selectedOption - 1];
            comparator = currentCompareWay.getComparator();
            TreeSet<Student> temp = new TreeSet<>(comparator);
            temp.addAll(studentsSet);
            printSet(temp);
        }
    }

    private static void printSet(TreeSet<Student> setToPrint) {
        for (Student student : setToPrint) {
            System.out.println(student.toString());
        }
    }

    private static int getOption(String message, ComparingVars[] values) {
        int selected = -1;
        do {
            System.out.println(message);
            for (int i = 0; i < values.length; i++) {
                System.out.print(i + 1 + " - " + values[i].getComparingWay() + "  ");
            }
            System.out.println();
            selected = getInt();
        } while (selected < 1 || selected > values.length + 1);
        return selected;
    }

    public static int getInt() {
        try {
            return Integer.parseInt(inputScanner.next());
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return -1;
        }
    }

    private static TreeSet<Student> initSet() {
        TreeSet<Student> toReturn = new TreeSet<>();
        toReturn.add(new Student("Page", "Larry"));
        toReturn.add(new Student("Ritchie", "Dennis"));
        toReturn.add(new Student("Gates", "Bill"));
        toReturn.add(new Student("Zuckerberg", "Mark"));
        toReturn.add(new Student("Thompson", "Ken"));
        return toReturn;
    }
}
