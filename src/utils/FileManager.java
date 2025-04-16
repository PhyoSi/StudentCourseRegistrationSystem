package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.Course;
import model.Student;

public class FileManager {
    private static final String STUDENT_FILE = "students.txt";
    private static final String COURSE_FILE = "courses.txt";

    // Save students to the file
    public static void saveStudents(List<Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENT_FILE))) {
            for (Student s : students) {
                writer.write(s.getId() + "," + s.getName() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error saving students: " + e.getMessage());
        }
    }

    // Save courses to the file
    public static void saveCourses(List<Course> courses) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(COURSE_FILE))){
            for (Course c : courses) {
                writer.write(c.getCourseId() + "," + c.getCourseName() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error saving courses: " + e.getMessage());
        }
    }

    // Load courses from the file
    // If the file does not exist, return an empty list
    public static List<Course> loadCourses() {
        List<Course> courses = new ArrayList<>();
        File file = new File(COURSE_FILE);
        if (!file.exists()) return courses;
    
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    courses.add(new Course(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading courses: " + e.getMessage());
        }
        return courses;
    }

    // Load students from the file
    // If the file does not exist, return an empty list
    public static List<Student> loadStudents(){
        List<Student> students = new ArrayList<>();
        File file = new File(STUDENT_FILE);
        if (!file.exists()) return students;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    students.add(new Student(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading students: " + e.getMessage());
        }
        return students;
    }
}