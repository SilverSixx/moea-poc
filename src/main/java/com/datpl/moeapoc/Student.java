package com.datpl.moeapoc;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private List<Dormitory> preferences; // List of preferred dormitories by name
    private static List<Student> students = new ArrayList<>(); // Static list to store all students

    // Default constructor
    public Student() {}

    // Constructor with parameters
    public Student(String name, List<Dormitory> preferences) {
        if (name == null || preferences == null) {
            throw new IllegalArgumentException("Name and preferences cannot be null");
        }
        this.name = name;
        this.preferences = preferences;
        students.add(this); // Automatically add the student to the list
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.name = name;
    }

    public List<Dormitory> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<Dormitory> preferences) {
        if (preferences == null) {
            throw new IllegalArgumentException("Preferences cannot be null");
        }
        this.preferences = preferences;
    }

    public static List<Student> getStudents() {
        return students;
    }

    public static void setStudents(List<Student> students) {
        if (students == null) {
            throw new IllegalArgumentException("Students list cannot be null");
        }
        Student.students = students;
    }

    // Method to add a student to the list
    public static void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        students.add(student);
    }

    // Method to remove a student from the list
    public static void removeStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        students.remove(student);
    }
}
