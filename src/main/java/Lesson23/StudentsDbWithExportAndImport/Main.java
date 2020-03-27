package Lesson23.StudentsDbWithExportAndImport;

import java.util.Scanner;

public class Main {
    static Scanner inputScanner = new Scanner(System.in);

    public static void main(String[] args) {
        StudentsGroup group = new StudentsGroup("JAVA ELEMENTARY", 10);
        int selectedOption = -1;
        while (selectedOption != 10) {
            selectedOption = getOption("ВЫБЕРИТЕ ОПЕРАЦИЮ", new String[]{"ДОБАВИТЬ СТУДЕНТА",
                    "УДАЛИТЬ СТУДЕНТА",
                    "ПОКАЗАТЬ СТУДЕНТОВ",
                    "ПОСТАВИТЬ ОЦЕНКУ",
                    "ПОСЕЩАЕМОСТЬ",
                    "ПОИСК",
                    "СОРТИРОВКА",
                    "ЭКСПОРТ В ФАЙЛ",
                    "ИМПОРТ ИЗ ФАЙЛА",
                    "ВЫХОД"});
            switch (selectedOption) {
                case 1:
                    String surnameToAdd = getString("ВВЕДИТЕ ФАМИЛИЮ:");
                    String nameToAdd = getString("ВВЕДИТЕ ИМЯ:");
                    int howManyLessons = group.getLessonsCount();
                    int id = group.getNextId();
                    if (surnameToAdd.length() > 0 && howManyLessons > 0) {
                        Student newStudent = new Student(id, surnameToAdd, nameToAdd, howManyLessons);
                        group.initStudentAdding(newStudent);
                    } else {
                        System.out.println("ПРОВЕРЬТЕ КОРРЕКТНОСТЬ ВВОДА");
                    }
                    break;
                case 2:
                    String surnameToDelete = getString("ВВЕДИТЕ ФАМИЛИЮ СТУДЕНТА ДЛЯ УДАЛЕНИЯ:");
                    group.deleteStudent(surnameToDelete);
                    break;
                case 3:
                    group.printStudents();
                    break;
                case 4:
                    String surnameToSetMark = getString("ВВЕДИТЕ ФАМИЛИЮ ЧТОБЫ ПОСТАВИТЬ ОЦЕНКУ:");
                    group.setMark(surnameToSetMark);
                    break;
                case 5:
                    String surnameToSetAttendance = getString("ВВЕДИТЕ ФАМИЛИЮ ЧТОБЫ ОТМЕТИТЬ ПОСЕЩАЕМОСТЬ:");
                    group.setAttendance(surnameToSetAttendance);
                    break;
                case 6:
                    String surnameToSearch = getString("ВВЕДИТЕ ФАМИЛИЮ СТУДЕНТА ДЛЯ ПОИСКА:");
                    group.search(surnameToSearch);
                    break;
                case 7:
                    group.sort();
                    group.printStudents();
                    break;
                case 8:
                    String filenameToExport = getString("ВВЕДИТЕ ИМЯ ФАЙЛА ДЛЯ ЭКСПОРТА");
                    System.out.println(group.exportToFile(filenameToExport));
                    break;
                case 9:
                    String filenameToImport = getString("ВВЕДИТЕ ИМЯ ФАЙЛА ДЛЯ ИМПОРТА");
                    System.out.println(group.importFromFile(filenameToImport));
                    break;
            }
        }
    }

    public static int getOption(String title, String[] options) {
        int selected = -1;
        do {
            System.out.println(title);
            for (int i = 0; i < options.length; i++) {
                System.out.print(i + 1 + " - " + options[i] + "  ");
            }
            System.out.println();
            selected = getInt();
        } while (selected < 1 || selected > options.length);
        return selected;
    }

    public static int getInt() {
        try {
            return Integer.parseInt(inputScanner.next());
        } catch (Exception ex) {
            return -1;
        }
    }

    public static int getInt(String str) {
        System.out.println(str);
        try {
            return Integer.parseInt(inputScanner.next());
        } catch (Exception ex) {
            return -1;
        }
    }

    public static String getString(String str) {
        System.out.println(str);
        return inputScanner.next();
    }
}
