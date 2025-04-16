package model;

import java.util.ArrayList;
import java.util.List;

public class Student extends User implements Registrable {
    private List<Course> registeredCourses;

    public Student(String id, String name) {
        super(id, name);                                    // inherited properties from User class
        this.registeredCourses = new ArrayList<>();         // Initialize the list of registered courses
    }

    // Override the method from Registrable interface
    @Override
    public void registerCourse(Course course) {
        registeredCourses.add(course);
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    // Override the method from User abstract class
    @Override
    public void displayInfo() {
        System.out.println("Student ID: " + getId() + ", Name: " + getName());
    }
}
