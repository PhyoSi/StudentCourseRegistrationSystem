package model;

// This interface defines a method for registering courses
// in a system. Any class that implements this interface must provide an implementation
// for the registerCourse method, which takes a Course object as a parameter.

// The interface is designed to be implemented by classes that represent entities
// that can register for courses, such as students or instructors.
public interface Registrable {
    void registerCourse(Course course);
}
