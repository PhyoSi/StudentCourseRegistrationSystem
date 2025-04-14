import model.Course;
import model.Student;
import utils.FileManager;
import utils.RegistrationSystem;
import exception.CourseNotFoundException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RegistrationSystem system = new RegistrationSystem();

        try {
            system.getCourses().addAll(FileManager.loadCourses());
        } catch (Exception e) {
            System.out.println("Could not load courses: " + e.getMessage());
        }

        while (true) {
            System.out.println("\n=== Student Course Registration System ===");
            System.out.println("1. Register Student for Course");
            System.out.println("2. Add New Course");
            System.out.println("3. Drop Course");
            System.out.println("4. View All Students");
            System.out.println("5. View All Courses");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
	            case "1":
	                System.out.print("Enter Student ID: ");
	                String studentId = scanner.nextLine();
	                System.out.print("Enter Student Name: ");
	                String studentName = scanner.nextLine();
	                System.out.print("Enter Course Code: ");
	                String courseId = scanner.nextLine();
	
	                Student existingStudent = findStudentById(system, studentId);
	                if (existingStudent == null) {
	                    existingStudent = new Student(studentId, studentName);
	                    system.addStudent(existingStudent);
	                }
	
	                try {
	                    Course course = system.getCourseById(courseId); // Get course object
	                    system.registerStudentToCourse(existingStudent, courseId);
	                    System.out.println("[SUCCESS] " + existingStudent.getName() + " registered for " + course.getCourseName() + "!");
	                } catch (CourseNotFoundException e) {
	                    System.out.println("[ERROR] " + e.getMessage());
	                }
	                break;

                case "2":
                    System.out.print("Enter Course ID: ");
                    String cid = scanner.nextLine();
                    System.out.print("Enter Course Name: ");
                    String cname = scanner.nextLine();
                    system.addCourse(new Course(cid, cname));
                    System.out.println("Course added.");
                    break;
                case "3":
                    System.out.print("Enter Student ID: ");
                    String sid = scanner.nextLine();
                    System.out.print("Enter Course ID to drop: ");
                    String dropCourseId = scanner.nextLine();
                    Student dropStudent = findStudentById(system, sid);
                    if (dropStudent == null) {
                        System.out.println("Student not found.");
                    } else {
                        boolean removed = dropStudent.getRegisteredCourses()
                                .removeIf(course -> course.getCourseId().equalsIgnoreCase(dropCourseId));
                        if (removed) {
                            System.out.println("Course dropped successfully.");
                        } else {
                            System.out.println("Course not found in student's registration.");
                        }
                    }
                    break;
                case "4":
                    for (Student s : system.getStudents()) {
                        s.displayInfo();
                        System.out.println("Courses:");
                        for (Course c : s.getRegisteredCourses()) {
                            System.out.println("  - " + c);
                        }
                        System.out.println();
                    }
                    break;
                case "5":
                    System.out.println("Available Courses:");
                    for (Course c : system.getCourses()) {
                        System.out.println(" - " + c);
                    }
                    break;
                case "6":
                    try {
                        FileManager.saveStudents(system.getStudents());
                        FileManager.saveCourses(system.getCourses());
                        System.out.println("Data saved. Goodbye!");
                    } catch (Exception e) {
                        System.out.println("Error saving data: " + e.getMessage());
                    }
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static Student findStudentById(RegistrationSystem system, String id) {
        for (Student s : system.getStudents()) {
            if (s.getId().equalsIgnoreCase(id)) {
                return s;
            }
        }
        return null;
    }
}
