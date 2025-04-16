package model;

// This is an abstract class that represents a user in the system. It contains common properties and methods
// that are shared among different types of users, such as students and instructors. The class has two properties: id and name.
// It also has a constructor to initialize these properties and an abstract method displayInfo() that must be implemented by subclasses.
public abstract class User {
    private String id;
    private String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public abstract void displayInfo();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
