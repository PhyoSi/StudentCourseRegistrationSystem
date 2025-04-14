package utils;

import exception.CourseNotFoundException;
import java.util.ArrayList;
import java.util.List;
import model.Course;
import model.Student;

public class RegistrationSystem {
    private List<Student> students = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void registerStudentToCourse(Student student, String courseId) throws CourseNotFoundException {
        Course course = getCourseById(courseId);
        if (course == null) {
            throw new CourseNotFoundException("Course not found: " + courseId);
        }
        student.registerCourse(course);
    }
    
    public List<Student> getStudents() {
        return students;
    }

	public Course getCourseById(String id) throws CourseNotFoundException {
        for (Course c : courses) {
            if (c.getCourseId().equalsIgnoreCase(id)) {
                return c;
            }
        }
        throw new CourseNotFoundException("Course not found: " + id);
    }
}
