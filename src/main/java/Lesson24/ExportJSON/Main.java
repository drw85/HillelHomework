package Lesson24.ExportJSON;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.*;
import java.util.ArrayList;

public class Main {
    private static long started;
    private static long finished;

    private static class Student implements Serializable {
        private String firstname;
        private String lastname;
        private int averageScore;

        public Student() {
        }

        public Student(String firstname, String lastname, int averageScore) {
            this.firstname = firstname;
            this.lastname = lastname;
            this.averageScore = averageScore;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public int getAverageScore() {
            return averageScore;
        }

        public void setAverageScore(int averageScore) {
            this.averageScore = averageScore;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "firstname='" + firstname + '\'' +
                    ", lastname='" + lastname + '\'' +
                    ", averageScore=" + averageScore +
                    '}';
        }
    }

    private static class CalculationStorage {
        private long cryptingTime;
        private long fileSize;

        public long getCryptingTime() {
            return cryptingTime;
        }

        public void setCryptingTime(long cryptingTime) {
            this.cryptingTime = cryptingTime;
        }

        public long getFileSize() {
            return fileSize;
        }

        public void setFileSize(long fileSize) {
            this.fileSize = fileSize;
        }
    }

    public static void main(String[] args) {
        CalculationStorage cs;
        Student student = new Student("Ivan", "Ivanov", 0);

        ArrayList<Student> studentsArr10000 = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            studentsArr10000.add(new Student("Ivan" + i, "Ivanov" + i, i));
        }

        System.out.println("СЕРИАЛИЗУЕМ 10000 СТУДЕНТОВ");
        cs = jsonStudentsArraySerialization(studentsArr10000);
        System.out.println("JSON справился за " + cs.getCryptingTime() + " миллисекунд | Размер файла: " + cs.getFileSize() + " bytes");
        cs=javaStudentsArraySerialization(studentsArr10000);
        System.out.println("JAVA справилась за " + cs.getCryptingTime() + " миллисекунд | Размер файла: " + cs.getFileSize() + " bytes");
        cs=yamlStudentsArraySerialization(studentsArr10000);
        System.out.println("YAML справился за " + cs.getCryptingTime() + " миллисекунд | Размер файла: " + cs.getFileSize() + " bytes");
        cs=xmlStudentsArraySerialization(studentsArr10000);
        System.out.println("XML справился за " + cs.getCryptingTime() + " миллисекунд | Размер файла: " + cs.getFileSize() + " bytes");

        System.out.println("ДЕСЕРИАЛИЗУЕМ 10000 СТУДЕНТОВ");
        cs=jsonStudentsArrayDeserialization();
        System.out.println("JSON справился за " + cs.getCryptingTime() + " миллисекунд");
        cs=javaStudentsArrayDeserialization();
        System.out.println("JAVA справилась за " + cs.getCryptingTime() + " миллисекунд");
        cs=yamlStudentsArrayDeserialization();
        System.out.println("YAML справился за " + cs.getCryptingTime() + " миллисекунд");
        cs=xmlStudentsArrayDeserialization();
        System.out.println("XML справился за " + cs.getCryptingTime() + " миллисекунд");

        ArrayList<Student> studentsArr1000000 = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            studentsArr1000000.add(new Student("Ivan" + i, "Ivanov" + i, i));
        }

        System.out.println("СЕРИАЛИЗУЕМ 1000000 СТУДЕНТОВ");
        cs = jsonStudentsArraySerialization(studentsArr1000000);
        System.out.println("JSON справился за " + cs.getCryptingTime() + " миллисекунд | Размер файла: " + cs.getFileSize() + " bytes");
        cs=javaStudentsArraySerialization(studentsArr1000000);
        System.out.println("JAVA справилась за " + cs.getCryptingTime() + " миллисекунд | Размер файла: " + cs.getFileSize() + " bytes");
        cs=yamlStudentsArraySerialization(studentsArr1000000);
        System.out.println("YAML справился за " + cs.getCryptingTime() + " миллисекунд | Размер файла: " + cs.getFileSize() + " bytes");
        cs=xmlStudentsArraySerialization(studentsArr1000000);
        System.out.println("XML справился за " + cs.getCryptingTime() + " миллисекунд | Размер файла: " + cs.getFileSize() + " bytes");

        System.out.println("ДЕСЕРИАЛИЗУЕМ 1000000 СТУДЕНТОВ");
        cs=jsonStudentsArrayDeserialization();
        System.out.println("JSON справился за " + cs.getCryptingTime() + " миллисекунд");
        cs=javaStudentsArrayDeserialization();
        System.out.println("JAVA справилась за " + cs.getCryptingTime() + " миллисекунд");
        cs=yamlStudentsArrayDeserialization();
        System.out.println("YAML справился за " + cs.getCryptingTime() + " миллисекунд");
        cs=xmlStudentsArrayDeserialization();
        System.out.println("XML справился за " + cs.getCryptingTime() + " миллисекунд");

    }

    public static CalculationStorage jsonStudentSerialization(Student student) {
        CalculationStorage cs = new CalculationStorage();
        File jsonSerializationFile = new File("jsonSerializationFile.txt");
        ObjectMapper jsonMapper = new ObjectMapper();
        try {
            started = System.currentTimeMillis();
            jsonMapper.writeValue(jsonSerializationFile, student);
            finished = System.currentTimeMillis();
            cs.setCryptingTime(finished - started);
            cs.setFileSize(jsonSerializationFile.length());
        } catch (JsonProcessingException ex) {
            System.out.println("ОШИБКА JSON СЕРИАЛИЗАЦИИ");
        } catch (IOException ex) {
            System.out.println("ОШИБКА JSON СЕРИАЛИЗАЦИИ");
        }
        return cs;
    }

    public static CalculationStorage jsonStudentDeserialization() {
        CalculationStorage cs = new CalculationStorage();
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("jsonSerializationFile.txt");
        Student studentToReturn = null;
        try {
            started = System.currentTimeMillis();
            studentToReturn = objectMapper.readValue(file, Student.class);
            finished = System.currentTimeMillis();
            cs.setCryptingTime(finished - started);
            cs.setFileSize(file.length());
        } catch (IOException ex) {
            System.out.println("ОШИБКА JSON ДЕСЕРИАЛИЗАЦИИ");
        }
        return cs;
    }

    public static CalculationStorage jsonStudentsArraySerialization(ArrayList<Student> studentsArr) {
        CalculationStorage cs = new CalculationStorage();
        File jsonSerializationFile = new File("jsonArraySerializationFile.txt");
        ObjectMapper jsonMapper = new ObjectMapper();
        try {
            started = System.currentTimeMillis();
            jsonMapper.writeValue(jsonSerializationFile, studentsArr);
            finished = System.currentTimeMillis();
            cs.setCryptingTime(finished - started);
            cs.setFileSize(jsonSerializationFile.length());
        } catch (JsonProcessingException ex) {
            System.out.println("ОШИБКА JSON СЕРИАЛИЗАЦИИ");
        } catch (IOException ex) {
            System.out.println("ОШИБКА JSON СЕРИАЛИЗАЦИИ");
        }
        return cs;
    }


    public static CalculationStorage jsonStudentsArrayDeserialization() {
        CalculationStorage cs = new CalculationStorage();

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("jsonArraySerializationFile.txt");
        ArrayList<Student> studentsToReturn = null;
        try {
            started = System.currentTimeMillis();
            objectMapper.readValue(file, ArrayList.class);
            finished = System.currentTimeMillis();
            cs.setCryptingTime(finished - started);
            cs.setFileSize(file.length());
        } catch (IOException ex) {
            System.out.println("ОШИБКА JSON ДЕСЕРИАЛИЗАЦИИ");
        }
        return cs;
    }

    public static CalculationStorage javaStudentSerialization(Student student) {
        CalculationStorage cs = new CalculationStorage();
        try {
            File fileToWrite = new File("javaSerializationFile.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileToWrite));
            started = System.currentTimeMillis();
            objectOutputStream.writeObject(student);
            finished = System.currentTimeMillis();
            objectOutputStream.close();
            cs.setCryptingTime(finished - started);
            cs.setFileSize(fileToWrite.length());
        } catch (IOException ex) {
            System.out.println("ОШИБКА JAVA ДЕСЕРИАЛИЗАЦИИ");
        }
        return cs;
    }

    public static CalculationStorage javaStudentDeserialization() {
        CalculationStorage cs = new CalculationStorage();
        File fileToWrite = new File("javaSerializationFile.txt");
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileToWrite));
            started = System.currentTimeMillis();
            objectInputStream.readObject();
            finished = System.currentTimeMillis();
            cs.setCryptingTime(finished - started);
            cs.setFileSize(fileToWrite.length());
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("ОШИБКА JAVA ДЕСЕРИАЛИЗАЦИИ");
        }
        return cs;
    }

    private static CalculationStorage javaStudentsArraySerialization(ArrayList<Student> studentsArr) {
        CalculationStorage cs = new CalculationStorage();
        File fileToWrite = new File("javaArraySerializationFile.txt");
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileToWrite));
            started = System.currentTimeMillis();
            objectOutputStream.writeObject(studentsArr);
            finished = System.currentTimeMillis();
            cs.setCryptingTime(finished - started);
            cs.setFileSize(fileToWrite.length());
            objectOutputStream.close();
        } catch (IOException ex) {
            System.out.println("ОШИБКА JAVA ДЕСЕРИАЛИЗАЦИИ");
        }
        return cs;
    }

    private static CalculationStorage javaStudentsArrayDeserialization() {
        CalculationStorage cs = new CalculationStorage();
        File fileToWrite = new File("javaArraySerializationFile.txt");
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileToWrite));
            started = System.currentTimeMillis();
            objectInputStream.readObject();
            finished = System.currentTimeMillis();
            cs.setCryptingTime(finished - started);
            cs.setFileSize(fileToWrite.length());
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("ОШИБКА JAVA ДЕСЕРИАЛИЗАЦИИ");
        }
        return cs;
    }

    private static CalculationStorage yamlStudentSerialization(Student student) {
        CalculationStorage cs = new CalculationStorage();
        File yamlSerializationFile = new File("yamlSerializationFile.txt");
        ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());
        try {
            started = System.currentTimeMillis();
            yamlMapper.writeValue(yamlSerializationFile, student);
            finished = System.currentTimeMillis();
            cs.setCryptingTime(finished - started);
            cs.setFileSize(yamlSerializationFile.length());
        } catch (IOException ex) {
            System.out.println("ОШИБКА YAML СЕРИАЛИЗАЦИИ");
        }
        return cs;
    }

    private static CalculationStorage yamlStudentDeserialization() {
        CalculationStorage cs = new CalculationStorage();
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        File file = new File("yamlSerializationFile.txt");
        try {
            started = System.currentTimeMillis();
            objectMapper.readValue(file, Student.class);
            finished = System.currentTimeMillis();
            cs.setCryptingTime(finished - started);
            cs.setFileSize(file.length());
        } catch (IOException ex) {
            System.out.println("ОШИБКА YAML ДЕСЕРИАЛИЗАЦИИ");
        }
        return cs;
    }

    public static CalculationStorage yamlStudentsArraySerialization(ArrayList<Student> studentsArr) {
        CalculationStorage cs = new CalculationStorage();
        File yamlSerializationFile = new File("yamlArraySerializationFile.txt");
        ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());
        try {
            started = System.currentTimeMillis();
            yamlMapper.writeValue(yamlSerializationFile, studentsArr);
            finished = System.currentTimeMillis();
            cs.setCryptingTime(finished - started);
            cs.setFileSize(yamlSerializationFile.length());
        } catch (IOException ex) {
            System.out.println("ОШИБКА YAML СЕРИАЛИЗАЦИИ");
        }
        return cs;
    }

    public static CalculationStorage yamlStudentsArrayDeserialization() {
        CalculationStorage cs = new CalculationStorage();
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        File file = new File("yamlArraySerializationFile.txt");
        try {
            started = System.currentTimeMillis();
            objectMapper.readValue(file, ArrayList.class);
            finished = System.currentTimeMillis();
            cs.setCryptingTime(finished - started);
            cs.setFileSize(file.length());
        } catch (IOException ex) {
            System.out.println("ОШИБКА YAML ДЕСЕРИАЛИЗАЦИИ");
        }
        return cs;
    }

    private static CalculationStorage xmlStudentSerialization(Student student) {
        CalculationStorage cs = new CalculationStorage();
        File xmlSerializationFile = new File("xmlSerializationFile.txt");
        ObjectMapper xmlMapper = new XmlMapper();
        try {
            started = System.currentTimeMillis();
            xmlMapper.writeValue(xmlSerializationFile, student);
            finished = System.currentTimeMillis();
            cs.setCryptingTime(finished - started);
            cs.setFileSize(xmlSerializationFile.length());
        } catch (IOException ex) {
            System.out.println("ОШИБКА XML СЕРИАЛИЗАЦИИ");
        }
        return cs;
    }

    private static CalculationStorage xmlStudentDeserialization() {
        CalculationStorage cs = new CalculationStorage();
        ObjectMapper xmlMapper = new XmlMapper();
        File file = new File("xmlSerializationFile.txt");
        try {
            started = System.currentTimeMillis();
            xmlMapper.readValue(file, Student.class);
            finished = System.currentTimeMillis();
            cs.setCryptingTime(finished - started);
            cs.setFileSize(file.length());
        } catch (IOException ex) {
            System.out.println("ОШИБКА XML ДЕСЕРИАЛИЗАЦИИ");
        }
        return cs;
    }

    public static CalculationStorage xmlStudentsArraySerialization(ArrayList<Student> studentsArr) {
        CalculationStorage cs = new CalculationStorage();
        File xmlSerializationFile = new File("xmlArraySerializationFile.txt");
        ObjectMapper xmlMapper = new XmlMapper();
        try {
            started = System.currentTimeMillis();
            xmlMapper.writeValue(xmlSerializationFile, studentsArr);
            finished = System.currentTimeMillis();
            cs.setCryptingTime(finished - started);
            cs.setFileSize(xmlSerializationFile.length());
        } catch (IOException ex) {
            System.out.println("ОШИБКА XML СЕРИАЛИЗАЦИИ");
        }
        return cs;
    }

    public static CalculationStorage xmlStudentsArrayDeserialization() {
        CalculationStorage cs = new CalculationStorage();
        ObjectMapper xmlMapper = new XmlMapper();
        File file = new File("xmlArraySerializationFile.txt");
        try {
            started = System.currentTimeMillis();
            xmlMapper.readValue(file, ArrayList.class);
            finished = System.currentTimeMillis();
            cs.setCryptingTime(finished - started);
            cs.setFileSize(file.length());
        } catch (IOException ex) {
            System.out.println("ОШИБКА XML ДЕСЕРИАЛИЗАЦИИ");
        }
        return cs;
    }
}
