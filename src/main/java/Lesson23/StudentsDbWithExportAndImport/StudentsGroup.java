package Lesson23.StudentsDbWithExportAndImport;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentsGroup {
    String groupName;
    int lessonsCount;
    Student[] studentsList = new Student[0];
    int id = 0;

    public StudentsGroup(String groupName, int lessonsCount) {
        this.groupName = groupName;
        this.lessonsCount = lessonsCount;
    }

    public void initStudentAdding(Student student) {
        Student[] findedStudentsList = findStudent(student.getSurname());
        if (findedStudentsList.length > 0) {
            System.out.println("НАЙДЕНО " + findedStudentsList.length + " ПОХОЖИХ СТУДЕНТА");
            switch (Main.getOption("ЧТО ДЕЛАТЬ?", new String[]{"ДОБАВИТЬ", "НЕ ДОБАВЛЯТЬ", "ПОКАЗАТЬ НАЙДЕНЫХ"})) {
                case 1:
                    this.studentsList = addStudent(student, this.studentsList);
                    break;
                case 2:
                    System.out.println("СТУДЕНТ " + student.getSurname() + " НЕ БУДЕТ ДОБАВЛЕН");
                    break;
                case 3:
                    for (int i = 0; i < findedStudentsList.length; i++) {
                        System.out.println(findedStudentsList[i].getSurname());
                    }
                    switch (Main.getOption("ДОБАВИТЬ " + student.getSurname() + " ?", new String[]{"ДА", "НЕТ"})) {
                        case 1:
                            this.studentsList = addStudent(student, this.studentsList);
                            break;
                        case 2:
                            System.out.println("СТУДЕНТ " + student.getSurname() + " НЕ БУДЕТ ДОБАВЛЕН");
                            break;
                    }
                    break;
            }
        } else {
            this.studentsList = addStudent(student, this.studentsList);
        }

    }

    private Student[] addStudent(Student student, Student[] array) {
        int arrLength = array.length + 1;
        Student[] newArr = new Student[arrLength];
        if (array.length > 0) {
            for (int i = 0; i < array.length; i++) {
                newArr[i] = array[i];
            }
            newArr[arrLength - 1] = student;
        } else {
            newArr[arrLength - 1] = student;
        }
        return newArr;
    }

    public Student[] findStudent(String surnameToFind) {
        Student[] findedStudents = new Student[0];
        if (countStudents() > 0) {
            for (int i = 0; i < studentsList.length; i++) {
                if (studentsList[i].getSurname().equals(surnameToFind)) {
                    int arrLength = findedStudents.length + 1;
                    Student[] arr = new Student[arrLength];
                    if (findedStudents.length > 0) {
                        for (int j = 0; j < findedStudents.length; j++) {
                            arr[j] = findedStudents[j];
                        }
                    }
                    arr[arrLength - 1] = studentsList[i];
                    findedStudents = arr;
                }
            }
        }
        return findedStudents;
    }

    public int countStudents() {
        return this.studentsList.length;
    }

    public void printStudents() {
        for (Student student :
                this.studentsList) {
            student.print(getMaximumSurnameLength());
            System.out.println();
        }
    }

    public void deleteStudent(String surnameToDelete) {
        Student[] findedStudents = findStudent(surnameToDelete);
        int idToDelete = -1;
        if (findedStudents.length == 0) {
            System.out.println("СТУДЕНТ " + surnameToDelete + " НЕ НАЙДЕН");
        }
        if (findedStudents.length == 1) {
            idToDelete = findedStudents[0].getId();
        }
        if (findedStudents.length > 1) {
            String[] options = new String[findedStudents.length];
            for (int i = 0; i < findedStudents.length; i++) {
                options[i] = findedStudents[i].getSurname() + " "
                        + findedStudents[i].getName()
                        + " (id: " + findedStudents[i].getId() + ")";
            }
            int selected = Main.getOption("КАКОГО ИЗ НИХ УДАЛИТЬ?", options);
            idToDelete = findedStudents[selected - 1].getId();
        }

        Student[] newArr = new Student[0];
        for (Student student :
                this.studentsList) {
            if (student.getId() != idToDelete) {
                newArr = addStudent(student, newArr);
            }
        }
        this.studentsList = newArr;
    }

    public int getNextId() {
        return ++id;
    }

    public void setMark(String surnameToSetMark) {
        Student[] findedStudents = findStudent(surnameToSetMark);
        int idToSetMark = -1;
        if (findedStudents.length == 0) {
            System.out.println("СТУДЕНТ " + surnameToSetMark + " НЕ НАЙДЕН");
            return;
        }
        if (findedStudents.length == 1) {
            idToSetMark = findedStudents[0].getId();
        }
        if (findedStudents.length > 1) {
            String[] options = new String[findedStudents.length];
            for (int i = 0; i < findedStudents.length; i++) {
                options[i] = findedStudents[i].getSurname() + " (id: " + findedStudents[i].getId() + ")";
            }
            int selected = Main.getOption("КОМУ ИЗ НИХ ПОСТАВИТЬ ОЦЕНКУ?", options);
            idToSetMark = findedStudents[selected - 1].getId();
        }
        Student studentToSetMark = null;
        for (Student student :
                this.studentsList) {
            if (student.getId() == idToSetMark) {
                studentToSetMark = student;
            }
        }
        int lessonToSetMark = -1;
        do {
            lessonToSetMark = Main.getInt("КАКОЙ УРОК ОЦЕНИТЬ?");
        } while (lessonToSetMark < 1 || lessonToSetMark > studentToSetMark.getLessonsCount());
        int markToSet = -1;
        do {
            markToSet = Main.getInt("КАКУЮ ОЦЕНКУ ПОСТАВИТЬ?");
        } while (markToSet < 1 || markToSet > 5);
        if (studentToSetMark.getMark(lessonToSetMark) > 0) {
            System.out.println("У СТУДЕНТА " + studentToSetMark.getSurname() + " УЖЕ СТОИТ ОЦЕНКА " + studentToSetMark.getMark(lessonToSetMark) + " ЗА УРОК № " + lessonToSetMark);
            int option = Main.getOption("ИЗМЕНИТЬ ОЦЕНКУ?", new String[]{"ДА", "НЕТ"});
            if (option == 1) {
                studentToSetMark.setMark(lessonToSetMark, markToSet);
            }
            if (option == 2) {
                System.out.println("ОЦЕНКА ОСТАНЕТСЯ БЕЗ ИЗМЕНЕНИЙ");
            }
        } else {
            studentToSetMark.setMark(lessonToSetMark, markToSet);
        }
    }

    public void setAttendance(String surnameToSetAttendance) {
        Student[] findedStudents = findStudent(surnameToSetAttendance);
        int idToSetAttendance = -1;
        if (findedStudents.length == 0) {
            System.out.println("СТУДЕНТ " + surnameToSetAttendance + " НЕ НАЙДЕН");
            return;
        }
        if (findedStudents.length == 1) {
            idToSetAttendance = findedStudents[0].getId();
        }
        if (findedStudents.length > 1) {
            String[] options = new String[findedStudents.length];
            for (int i = 0; i < findedStudents.length; i++) {
                options[i] = findedStudents[i].getSurname() + " (id: " + findedStudents[i].getId() + ")";
            }
            int selected = Main.getOption("КОМУ ИЗ НИХ УСТАНОВИТЬ ПОСЕЩЕНИЕ?", options);
            idToSetAttendance = findedStudents[selected - 1].getId();
        }
        Student studentToSetAttendance = null;
        for (Student student :
                this.studentsList) {
            if (student.getId() == idToSetAttendance) {
                studentToSetAttendance = student;
            }
        }
        int lessonToSetAttendance = -1;
        do {
            lessonToSetAttendance = Main.getInt("ВЫСТАВИТЬ ПОСЕЩАЕМОСТЬ КАКОГО УРОКА?");
        } while (lessonToSetAttendance < 1 || lessonToSetAttendance > studentToSetAttendance.getLessonsCount());
        int option = -1;
        do {
            option = Main.getOption("СТУДЕНТ " + studentToSetAttendance.getSurname() + " БЫЛ НА УРОКЕ №" + lessonToSetAttendance + "?", new String[]{"ДА", "НЕТ"});
        } while (option < 1 || option > 2);
        if (option == 1) {
            studentToSetAttendance.setAttendance(lessonToSetAttendance, true);
        }
        if (option == 2) {
            studentToSetAttendance.setAttendance(lessonToSetAttendance, false);
        }
    }

    public void search(String surnameToSearch) {
        Student[] findedStudents = findStudent(surnameToSearch);
        int idToSetMark = -1;
        if (findedStudents.length == 0) {
            System.out.println("СТУДЕНТ " + surnameToSearch + " НЕ НАЙДЕН");
            return;
        }
        if (findedStudents.length > 0) {
            for (Student student :
                    findedStudents) {
                student.print(getMaximumSurnameLength());
                System.out.println();
            }
        }
    }

    public int getMaximumSurnameLength() {
        int maximumSurnameLength = 0;
        for (Student student :
                this.studentsList) {
            if (maximumSurnameLength < student.getSurname().length()) {
                maximumSurnameLength = student.getSurname().length();
            }
        }
        return maximumSurnameLength;
    }

    public int getMaximumNameLength() {
        int maximumNameLength = 0;
        for (Student student :
                this.studentsList) {
            if (maximumNameLength < student.getName().length()) {
                maximumNameLength = student.getName().length();
            }
        }
        return maximumNameLength;
    }

    public int getMaximumMarksLength() {
        int maximumMarksLength = 0;
        for (Student student :
                this.studentsList) {
            if (maximumMarksLength < String.valueOf(student.getMarksAverage()).length()) {
                maximumMarksLength = String.valueOf(student.getMarksAverage()).length();
            }
        }
        return maximumMarksLength;
    }

    public int getLessonsCount() {
        return this.lessonsCount;
    }

    public void sort() {
        Student temp;
        for (int i = 0; i < studentsList.length; i++) {
            for (int j = i + 1; j < studentsList.length; j++) {
                if (studentsList[i].getSurname().compareTo(studentsList[j].getSurname()) > 0) {
                    temp = studentsList[i];
                    studentsList[i] = studentsList[j];
                    studentsList[j] = temp;
                }
            }
        }
    }

    public String exportToFile(String filenameToExport) {
        String message = "";
        File fileToExport = new File(filenameToExport + ".txt");
        try (Writer out = new FileWriter(fileToExport)) {
            String data = "";
            for (int i = 0; i < studentsList.length; i++) {
                Student currStudent = studentsList[i];
                data = currStudent.id + "," + currStudent.surname + "," + currStudent.name + ",";
                for (int j = 0; j < currStudent.marks.length; j++) {
                    data += currStudent.marks[j] + ",";
                }
                for (int j = 0; j < currStudent.attendance.length; j++) {
                    data += currStudent.attendance[j] + ",";
                }
                data += "\n";
                out.write(data);
                message = "ЭКСПОРТ ВЫПОЛНЕН В ФАЙЛ: " + fileToExport.getAbsolutePath();
            }
        } catch (IOException ex) {
            return "ОШИБКА ЭКСПОРТА: " + ex;
        }
        return message;
    }

    public String importFromFile(String filenameToImport) {
        File fileToImport = new File(filenameToImport + ".txt");
        if (!fileToImport.exists()) {
            return "ОШИБКА ИМПОРТА: НЕТ ТАКОГО ФАЙЛА";
        }
        try (BufferedReader br = new BufferedReader(new FileReader(fileToImport))) {
            List<Student> tempList = new ArrayList<>();
            for (String line; (line = br.readLine()) != null; ) {
                if (line != null) {
                    String[] split = line.split(",");
                    if (split.length != (lessonsCount * 2) + 3) {
                        return "ОШИБКА ИМПОРТА: ОШИБКА ФОРМАТА ФАЙЛА";
                    }
                    int id = Integer.parseInt(split[0]);
                    String surname = split[1];
                    String name = split[2];
                    int[] marks = new int[(split.length - 3) / 2];
                    boolean[] attendance = new boolean[(split.length - 3) / 2];
                    for (int i = 0; i < split.length; i++) {
                        for (int j = 3; j < lessonsCount + 3; j++) {
                            marks[j - 3] = Integer.parseInt(split[j]);
                        }
                        for (int j = 3 + lessonsCount; j < (lessonsCount * 2) + 3; j++) {
                            attendance[j - (3 + lessonsCount)] = Boolean.parseBoolean(split[j]);
                        }
                    }
                    Student st = new Student(id, surname, name, marks, attendance);
                    tempList.add(st);
                }
            }
            studentsList = new Student[tempList.size()];
            for (int i = 0; i < tempList.size(); i++) {
                studentsList[i] = tempList.get(i);
            }
            return "ИМПОРТ ЗАВЕРШЕН";
        } catch (IOException ex) {
            return "ОШИБКА ИМПОРТА: " + ex;
        }
    }
}

