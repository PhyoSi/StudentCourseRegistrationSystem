package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.Course;
import model.Student;

public class FileManager {
    private static final String STUDENT_FILE = "students.txt";
    private static final String COURSE_FILE = "courses.txt";

    public static void saveStudents(List<Student> students) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENT_FILE));
        for (Student s : students) {
            writer.write(s.getId() + "," + s.getName() + "\n");
        }
        writer.close();
    }

    public static void saveCourses(List<Course> courses) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(COURSE_FILE));
        for (Course c : courses) {
            writer.write(c.getCourseId() + "," + c.getCourseName() + "\n");
        }
        writer.close();
    }

    public static List<Course> loadCourses() throws IOException {
        List<Course> courses = new ArrayList<>();
        File file = new File(COURSE_FILE);
        if (!file.exists()) return courses;

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 2) {
                courses.add(new Course(parts[0], parts[1]));
            }
        }
        return courses;
    }

    public static List<Student> loadStudents() throws IOException {
        List<Student> students = new ArrayList<>();
        File file = new File(STUDENT_FILE);
        if (!file.exists()) return students;

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 2) {
                students.add(new Student(parts[0], parts[1]));
            }
        }
        return students;
    }
}
