package StudentsDB;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static String[] surnames = new String[0];
    public static int[][] marks = new int[0][0];
    public static boolean[][] attendance = new boolean[0][0];
    public static Scanner inputScanner = new Scanner(System.in);

    public static void main(String[] args) {
        int selectedOption;
        do {
            selectedOption = getInt("ВЫБЕРИТЕ ДЕЙСТВИЕ:\n(1-ДОБАВИТЬ, 2-УДАЛИТЬ, 3-ПОИСК, 4-ПОКАЗАТЬ ВСЕХ, 5-ОЦЕНКА, 6-ПОСЕЩЕНИЕ, 7-СОРТИРОВКА, 8-СБРОС, 0-ВЫХОД)");
            switch (selectedOption) {
                case 1:
                    addNewStudent();
                    break;
                case 2:
                    deleteStudent();
                    break;
                case 3:
                    String surnameToSearch = getString("ВВЕДИТЕ ФАМИЛИЮ ДЛЯ ПОИСКА:");
                    if (find(surnameToSearch) > -1) {
                        System.out.println("СТУДЕНТ '" + surnameToSearch + "' НАЙДЕН");
                    } else {
                        System.out.println("СТУДЕНТ НЕ НАЙДЕН");
                    }
                    break;
                case 4:
                    printAllStudents();
                    break;
                case 5:
                    rate();
                    break;
                case 6:
                    setAttendance();
                    break;
                case 7:
                    sort();
                    break;
                case 8:
                    reset();
                    break;
            }
        } while (selectedOption != 0);
    }

    private static void addNewStudent() {
        String surname = getString("ВВЕДИТЕ ФАМИЛИЮ:");
        if (find(surname) > -1) {
            System.out.println("ТАКОЙ СТУДЕНТ УЖЕ ЕСТЬ");
        } else {
            String[] newArray = new String[surnames.length + 1];
            for (int i = 0; i <= surnames.length - 1; i++) {
                newArray[i] = surnames[i];
            }
            newArray[newArray.length - 1] = surname;
            surnames = newArray;
            createStudentMarks();
            createStudentAttendance();
        }
    }

    private static void createStudentAttendance() {
        boolean[][] newArray = new boolean[surnames.length][10];
        for (int i = 0; i < attendance.length; i++) {
            newArray[i] = attendance[i];
        }
        newArray[surnames.length - 1] = new boolean[10];
        attendance = newArray;
    }

    private static void createStudentMarks() {
        int[][] newArray = new int[surnames.length][10];
        for (int i = 0; i < marks.length; i++) {
            newArray[i] = marks[i];
        }
        newArray[surnames.length - 1] = new int[10];
        marks = newArray;
    }

    private static void deleteStudent() {
        String surnameToDelete = getString("ВВЕДИТЕ ФАМИЛИЮ ДЛЯ УДАЛЕНИЯ:");
        int id = find(surnameToDelete);
        if (id > -1) {
            int toDelete;
            do {
                toDelete = getInt("УДАЛИТЬ СТУДЕНТА '" + surnames[id] + "'? (1-ДА 2-НЕТ)");
            } while (toDelete < 1 || toDelete > 2);
            if (toDelete == 1) {
                int counter = 0;
                String[] newArraySurname = new String[surnames.length - 1];
                int[][] newArrayMarks = new int[marks.length - 1][10];
                boolean[][] newArrayAttendance = new boolean[attendance.length - 1][10];
                for (int i = 0; i < surnames.length; i++) {
                    if (i != id) {
                        newArraySurname[counter] = surnames[i];
                        newArrayMarks[counter] = marks[i];
                        newArrayAttendance[counter] = attendance[i];
                        counter++;
                    }
                }
                surnames = newArraySurname;
                marks = newArrayMarks;
                attendance = newArrayAttendance;
            }
        } else {
            System.out.println("ТАКОЙ СТУДЕНТ НЕ НАЙДЕН");
        }
    }

    private static int find(String str) {
        int id = -1;
        for (int i = 0; i < surnames.length; i++) {
            if (surnames[i].equals(str)) {
                id = i;
            }
        }
        return id;
    }

    private static void printAllStudents() {
        if (surnames.length > 0) {
            String[] stringsArray = new String[(surnames.length * 2) + 2];
            int maxLenght = 0;
            for (String s :
                    surnames) {
                if (maxLenght < s.length()) {
                    maxLenght = s.length();
                }
            }
            for (int i = 0; i < (surnames.length); i++) {
                String str = "|    ";
                String upString = "+----";
                String strAttendance = "|    ";
                for (int j = 0; j < maxLenght; j++) {
                    if (j < surnames[i].length()) {
                        upString += "-";
                        str += surnames[i].charAt(j);
                        strAttendance += " ";
                    } else {
                        upString += "-";
                        str += " ";
                        strAttendance += " ";
                    }
                }
                upString += "----+";
                str += "    |";
                strAttendance += "    |";
                for (int j = 0; j < marks[i].length; j++) {
                    upString += "---+";
                    str += " " + marks[i][j] + " |";
                    if (attendance[i][j]) {
                        strAttendance += " + |";
                    } else {
                        strAttendance += " - |";
                    }
                }
                System.out.println(upString);
                System.out.println(str);
                System.out.println(strAttendance);
                if (i == surnames.length - 1) {
                    System.out.println(upString);
                }
            }
        } else {
            System.out.println("ГРУППА ПУСТА. ВВЕДИТЕ СТУДЕНТОВ.");
        }
    }

    private static void rate() {
        String surnameToMark = getString("ВВЕДИТЕ ФАМИЛИЮ СТУДЕНТА ЧТОБЫ ВЫСТАВИТЬ ОЦЕНКУ:");
        int id = find(surnameToMark);
        if (id > -1) {
            int lessonNo = getInt("ВВЕДИТЕ НОМЕР УРОКА:");
            int mark;
            if (lessonNo < marks[0].length || lessonNo < 1) {
                if (marks[id][lessonNo - 1] > 0) {
                    System.out.println("У ЭТОГО СТУДЕНТА УЖЕ СТОИТ ОЦЕНКА " + marks[id][lessonNo - 1] + " ЗА ЭТОТ УРОК");
                    int toOverwrite;
                    do {
                        toOverwrite = getInt("ХОТИТЕ ИЗМЕНИТЬ ОЦЕНКУ? (1-ДА, 2-НЕТ)");
                    } while (toOverwrite < 1 || toOverwrite > 2);
                    if (toOverwrite == 1) {
                        do {
                            mark = getInt("КАКУЮ ОЦЕНКУ УСТАНОВИТЬ?");
                        } while (mark < 1 || mark > 5);
                        marks[id][lessonNo - 1] = mark;
                    } else if (toOverwrite == 2) {
                        System.out.println("ОЦЕНКА НЕ БУДЕТ ИЗМЕНЕНА");
                    }
                } else {
                    do {
                        mark = getInt("ВВЕДИТЕ ОЦЕНКУ:");
                    } while (mark < 1 || mark > 5);
                    marks[id][lessonNo - 1] = mark;
                }
            } else {
                System.out.println("ТАКОЙ УРОК НЕ НАЙДЕН");
            }
        } else {
            System.out.println("ТАКОЙ СТУДЕНТ НЕ НАЙДЕН");
        }
    }

    private static void setAttendance() {
        String surnameToSetAttendance = getString("ВВЕДИТЕ ФАМИЛИЮ ДЛЯ ВВОДА ПОСЕЩАЕМОСТИ:");
        int id = find(surnameToSetAttendance);
        if (id > -1) {
            int lessonNo = getInt("ВВЕДИТЕ НОМЕР УРОКА:");
            boolean toSet;
            int option;
            if (lessonNo < attendance[0].length || lessonNo < 1) {
                do {
                    option = getInt("СТУДЕНТ ПРИСУТСТВОВАЛ НА ЭТОМ УРОКЕ? (1-ДА, 2-НЕТ)");
                } while (option < 1 || option > 2);
                if (option == 1) {
                    attendance[id][lessonNo - 1] = true;
                } else if (option == 2) {
                    attendance[id][lessonNo - 1] = false;
                }
            } else {
                System.out.println("ТАКОЙ УРОК НЕ НАЙДЕН");
            }
        } else {
            System.out.println("ТАКОЙ СТУДЕНТ НЕ НАЙДЕН");
        }
    }

    private static void sort() {
        String[] newSurname = surnames.clone();
        int[][] newMarks = marks.clone();
        boolean[][] newAttendance = attendance.clone();
        Arrays.sort(newSurname);
        for (int i = 0; i < newSurname.length; i++) {
            for (int j = 0; j < surnames.length; j++) {
                if (newSurname[i] == surnames[j]) {
                    newMarks[i] = marks[j];
                    newAttendance[i] = attendance[j];
                }
            }
        }
        surnames = newSurname;
        marks = newMarks;
        attendance = newAttendance;
        printAllStudents();
    }

    private static void reset() {
        String surname = getString("ВВЕДИТЕ ФАМИЛИЮ:");
        int id = find(surname);
        if (id > -1) {
            int option;
            do {
                option = getInt("ЧТО НЕОБХОДИМО СБРОСИТЬ? (1-ОЦЕНКИ, 2-ПОСЕЩАЕМОСТЬ)");
            } while (option < 1 || option > 2);
            if (option == 1) {
                marks[id] = new int[10];
            }
            if (option == 2) {
                attendance[id] = new boolean[10];
            }
        } else {
            System.out.println("ТАКОЙ СТУДЕНТ НЕ НАЙДЕН");
        }
    }

    private static String getStudentAttendance(int id) {
        String attendanceInString = "";
        if (id > -1) {
            boolean[] attendanceArray = attendance[id];
            for (boolean a : attendanceArray) {
                if (a) {
                    attendanceInString += "| +";
                } else {
                    attendanceInString += "| -";
                }
            }
        } else {
            return "НЕ МОГУ НАЙТИ ПОСЕЩАЕМОСТЬ СТУДЕНТА";
        }
        return attendanceInString;
    }

    private static String getStudentMarks(int id) {
        String marksInString = "";
        if (id > -1) {
            int[] marksArray = marks[id];
            for (int m : marksArray) {
                marksInString += "| " + m;
            }
        } else {
            return "НЕ МОГУ НАЙТИ ОЦЕНКИ СТУДЕНТА";
        }
        return marksInString;
    }

    private static int getInt(String message) {
        System.out.println(message);
        try {
            return Integer.parseInt(inputScanner.next());
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

    private static String getString(String message) {
        System.out.println(message);
        return inputScanner.next();
    }

}