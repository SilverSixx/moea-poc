package com.datpl.moeapoc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockData {

    public static List<Dormitory> getDormitories() {
        List<Dormitory> dormitories = new ArrayList<>();

        Dormitory dorm1 = new Dormitory("Dorm A", new ArrayList<>(), 2);
        Dormitory dorm2 = new Dormitory("Dorm B", new ArrayList<>(), 2);
        Dormitory dorm3 = new Dormitory("Dorm C", new ArrayList<>(), 2);

        dormitories.add(dorm1);
        dormitories.add(dorm2);
        dormitories.add(dorm3);

        Dormitory.setDormitories(dormitories); // Set the dormitories in the static list

        return dormitories;
    }

    public static List<Student> getStudents() {
        List<Student> students = new ArrayList<>();

        List<Dormitory> dormitories = getDormitories();

        Student student1 = new Student("Alice", Arrays.asList(dormitories.get(0), dormitories.get(1), dormitories.get(2)));
        Student student2 = new Student("Bob", Arrays.asList(dormitories.get(1), dormitories.get(0), dormitories.get(2)));
        Student student3 = new Student("Charlie", Arrays.asList(dormitories.get(2), dormitories.get(1), dormitories.get(0)));

        students.add(student1);
        students.add(student2);
        students.add(student3);

        Student.setStudents(students); // Set the students in the static list

        return students;
    }
}
