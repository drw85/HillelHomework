package Lesson23.StudentsDbWithExportAndImport;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Student {
    String surname;
    String name;
    int[] marks;
    boolean[] attendance;
    int id;

    public Student(int id, String surname, String name, int numberOfLessons) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.marks = new int[numberOfLessons];
        this.attendance = new boolean[numberOfLessons];
    }

    public Student(int id, String surname, String name, int[] marks, boolean[] attendance) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.marks = marks;
        this.attendance = attendance;
    }


    public int getId() {
        return this.id;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getName() {
        return name;
    }

    public int[] getAllMarks() {
        return marks;
    }

    public int getMark(int lessonNo) {
        return this.marks[lessonNo - 1];
    }

    public boolean[] getAttendance() {
        return attendance;
    }

    public int getLessonsCount() {
        return this.marks.length;
    }

    public void resetMarks() {
        int marksLength = marks.length;
        marks = new int[marksLength];
        System.out.println("ОЦЕНКИ СТУДЕНТА " + getSurname() + " УДАЛЕНЫ");
    }

    public void resetAttendance() {
        int attendanceLength = attendance.length;
        attendance = new boolean[attendanceLength];
        System.out.println("ПОСЕЩАЕМОСТЬ СТУДЕНТА " + getSurname() + " УДАЛЕНА");
    }

    public void setMark(int lessonNo, int mark) {
        String message;
        if (lessonNo > 0 && lessonNo <= marks.length) {
            if (mark > 0 && mark <= 5) {
                this.marks[lessonNo - 1] = mark;
                message = "СТУДЕНТ " + getSurname() + " ПОЛУЧИЛ " + mark + " НА ЗАНЯТИИ №" + lessonNo;
            } else {
                message = "ОШИБКА УСТАНОВКИ ОЦЕНКИ. ОЦЕНКА ЗА ПРЕДЕЛАМИ ЗНАЧЕНИЙ (" + mark + ")";
            }
        } else {
            message = "ОШИБКА УСТАНОВКИ ОЦЕНКИ. НЕТ ТАКОГО УРОКА (" + lessonNo + ")";
        }
        System.out.println(message);
    }

    public void setAttendance(int lessonNo, boolean isPresent) {
        String message;
        if (lessonNo > 0 && lessonNo <= attendance.length) {
            attendance[lessonNo - 1] = isPresent;
            if (isPresent) {
                message = "СТУДЕНТ " + getSurname() + " ПРИСУТСТВОВАЛ НА УРОКЕ № " + lessonNo;
            } else {
                message = "СТУДЕНТ " + getSurname() + " ОТСУТСТВОВАЛ НА УРОКЕ № " + lessonNo;
            }
        } else {
            message = "ОШИБКА УСТАНОВКИ ПОСЕЩЕНИЙ. НЕТ ТАКОГО УРОКА (" + lessonNo + ")";
        }
        System.out.println(message);
    }

    public void print(int maxLength) {
        int surnameWidth = 4 + this.getSurname().length() + (maxLength - this.getSurname().length());
        int rowWidth = 4 + this.getSurname().length() + (maxLength - this.getSurname().length());
        String s = "| " + this.getSurname();
        for (int i = this.getSurname().length(); i < maxLength; i++) {
            s += " ";
        }
        s += " |";
        for (int i = 0; i < getAllMarks().length; i++) {
            s += " " + getAllMarks()[i] + " |";
            rowWidth += 4;
        }
        System.out.println(s);
        System.out.print("|");
        for (int i = 0; i < surnameWidth - 2; i++) {
            System.out.print(" ");
        }
        System.out.print("|");
        for (int i = 0; i < getAttendance().length; i++) {
            if (getAttendance()[i] == true) {
                System.out.print(" + |");
            } else {
                System.out.print(" - |");

            }
        }
        System.out.println();
        for (int i = 0; i < rowWidth; i++) {
            System.out.print("-");
        }
    }

    public double getMarksAverage() {
        double summ = 0L;
        int markedLessons = 0;
        for (int mark : marks) {
            summ += mark;
            if (mark > 0) {
                markedLessons++;
            }
        }
        BigDecimal toTruncate;
        if (markedLessons == 0) {
            toTruncate = new BigDecimal(0);
        } else {
            toTruncate = new BigDecimal(summ / markedLessons);
        }
        double toReturn = toTruncate.setScale(2, RoundingMode.HALF_UP).doubleValue();
        return toReturn;
    }

    public String getExportString(int maxSurnameLength, int maxNameLength, int maxMarksLength) {
        int surnameWidth = 10;
        if (maxSurnameLength > 7) {
            for (int i = 0; i < maxSurnameLength - 7; i++) {
                surnameWidth++;
            }
        }
        int nameWidth = 6;
        if (maxNameLength > 3) {
            for (int i = 0; i < maxSurnameLength - 3; i++) {
                nameWidth++;
            }
        }
        int marksWidth = 16 + String.valueOf(this.getMarksAverage()).length()
                + (maxMarksLength - String.valueOf(this.getMarksAverage()).length());

        String s = "| " + this.getSurname();
        for (int i = this.getSurname().length(); i < surnameWidth - 3; i++) {
            s += " ";
        }
        s += " | ";
        s += this.getName();
        for (int i = this.getName().length(); i < nameWidth - 3; i++) {
            s += " ";
        }
        s += " | ";
        s += getMarksAverage();
        for (int i = String.valueOf(this.getMarksAverage()).length() + 7; i < marksWidth; i++) {
            s += " ";
        }
        s += " |";
        return s;
    }
}
