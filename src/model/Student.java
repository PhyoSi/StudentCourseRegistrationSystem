package model;

import java.util.ArrayList;
import java.util.List;

public class Student extends User implements Registrable {
    private List<Course> registeredCourses;

    public Student(String id, String name) {
        super(id, name);
        this.registeredCourses = new ArrayList<>();
    }

    @Override
    public void registerCourse(Course course) {
        registeredCourses.add(course);
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    @Override
    public void displayInfo() {
        System.out.println("Student ID: " + getId() + ", Name: " + getName());
    }
}
