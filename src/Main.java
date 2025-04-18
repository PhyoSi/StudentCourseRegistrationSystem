import exception.CourseNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import model.Course;
import model.Student;
import utils.FileManager;
import utils.RegistrationSystem;

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
                    try {
                        System.out.print("Enter Student ID: ");
                        String studentIdInput = scanner.nextLine();
                        int studentId = Integer.parseInt(studentIdInput);

                        System.out.print("Enter Student Name: ");
                        String studentName = scanner.nextLine();

                        // Prevent numeric names
                        if (studentName.matches("\\d+")) {
                            throw new IllegalArgumentException("Student name cannot be numeric.");
                        }

                        // Check if student ID is valid
                        if (studentId <= 0) {
                            throw new IllegalArgumentException("Student ID must be a positive number.");
                        }

                        // Check if student name contains only alphabetic characters and spaces
                        if (!studentName.matches("^[a-zA-Z ]+$")) {
                            throw new IllegalArgumentException("Student name must contain only alphabetic characters and spaces.");
                        }

                        System.out.print("Enter Course Code: ");
                        String courseId = scanner.nextLine();

                        // Convert int ID back to string for consistency
                        String studentIdStr = String.valueOf(studentId);

                        Student existingStudent = findStudentById(system, studentIdStr);
                        if (existingStudent == null) {
                            existingStudent = new Student(studentIdStr, studentName);
                            system.addStudent(existingStudent);
                        }

                        Course course = system.getCourseById(courseId);
                        system.registerStudentToCourse(existingStudent, courseId);
                        System.out.println("[SUCCESS] " + studentName + " registered for " + course.getCourseName() + "!");

                    } catch (NumberFormatException e) {
                        System.out.println("[ERROR] Student ID must be a number.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("[ERROR] " + e.getMessage());
                    } catch (CourseNotFoundException e) {
                        System.out.println("[ERROR] " + e.getMessage());
                    }
                    break;

                case "2":
                    try {
                        System.out.print("Enter Course ID: ");
                        String cid = scanner.nextLine();
                        System.out.print("Enter Course Name: ");
                        String cname = scanner.nextLine();

                        // Prevent numeric course names
                        if (cname.matches("\\d+")) {
                            throw new IllegalArgumentException("Course name cannot be numeric.");
                        }

                        system.addCourse(new Course(cid, cname));
                        System.out.println("Course added.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("[ERROR] " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("[ERROR] Could not add course: " + e.getMessage());
                    }
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
                        boolean removed = false;
                        Iterator<Course> iterator = dropStudent.getRegisteredCourses().iterator();      // Iterate through the list of registered courses
                        while (iterator.hasNext()) {
                            Course course = iterator.next();
                            if (course.getCourseId().equalsIgnoreCase(dropCourseId)) {
                                iterator.remove();
                                removed = true;
                                break;
                            }
                        }
                        if (removed) {
                            System.out.println("Course dropped successfully.");
                        } else {
                            System.out.println("Course not found in student's registration.");
                        }
                    }
                    break;

                case "4":
                    if (system.getStudents().isEmpty()) {
                        System.out.println("No students registered.");
                        break;
                    }
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
                    if (system.getCourses().isEmpty()) {
                        System.out.println("No courses available.");
                        break;
                    }
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

    // This method finds a student by ID in the registration system.
    private static Student findStudentById(RegistrationSystem system, String id) {
        for (Student s : system.getStudents()) {
            if (s.getId().equalsIgnoreCase(id)) {
                return s;
            }
        }
        return null;
    }
}
