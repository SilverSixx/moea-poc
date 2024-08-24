package com.datpl.moeapoc;

import java.util.ArrayList;
import java.util.List;

public class Dormitory {

    private String name;
    private List<Student> preferences; // List of preferred students
    private int capacity;
    private List<Student> students; // List of students assigned to this dormitory
    private static List<Dormitory> dormitories = new ArrayList<>(); // Static list to store all dormitories

    // Constructor
    public Dormitory(String name, List<Student> preferences, int capacity) {
        if (name == null || preferences == null) {
            throw new IllegalArgumentException("Name and preferences cannot be null");
        }
        this.name = name;
        this.preferences = preferences;
        this.capacity = capacity;
        this.students = new ArrayList<>();
        dormitories.add(this); // Automatically add the dormitory to the list
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

    public List<Student> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<Student> preferences) {
        if (preferences == null) {
            throw new IllegalArgumentException("Preferences cannot be null");
        }
        this.preferences = preferences;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }
        this.capacity = capacity;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        if (students.size() < capacity) {
            students.add(student);
        } else {
            throw new IllegalStateException("Dormitory is full");
        }
    }

    public void removeStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        students.remove(student);
    }

    public static List<Dormitory> getDormitories() {
        return dormitories;
    }

    public static void setDormitories(List<Dormitory> dormitories) {
        if (dormitories == null) {
            throw new IllegalArgumentException("Dormitories list cannot be null");
        }
        Dormitory.dormitories = dormitories;
    }
}
