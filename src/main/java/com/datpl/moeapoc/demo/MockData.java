package com.datpl.moeapoc.demo;

import com.datpl.moeapoc.Dormitory;
import com.datpl.moeapoc.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class MockData {

    private List<Dormitory> dormitories = new ArrayList<>();
    private List<Student> students = new ArrayList<>();

    @Bean
    public List<Dormitory> dormitories() {
        return dormitories;
    }

    @Bean
    public List<Student> students() {
        return students;
    }

    @PostConstruct
    public void init() {
        Dormitory dormitory1 = new Dormitory(
                "Dormitory A",
                new ArrayList<>(),
                10,
                "Quiet",
                new ArrayList<>(Arrays.asList("Studious", "Introverted"))
        );

        Dormitory dormitory2 = new Dormitory(
                "Dormitory B",
                new ArrayList<>(),
                10,
                "Social",
                new ArrayList<>(Arrays.asList("Extroverted", "Friendly"))
        );

        dormitories.add(dormitory1);
        dormitories.add(dormitory2);

        Student student1 = new Student(
                "Alice",
                new ArrayList<>(),
                "Introverted",
                new ArrayList<>(Arrays.asList("Quiet", "Studious"))
        );

        Student student2 = new Student(
                "Bob",
                new ArrayList<>(Arrays.asList(dormitory2, dormitory1)),
                "Extroverted",
                new ArrayList<>(Arrays.asList("Social", "Friendly"))
        );

        Student student3 = new Student(
                "Charlie",
                new ArrayList<>(List.of(dormitory2)),
                "Studious",
                new ArrayList<>(Arrays.asList("Quiet", "Studious"))
        );

        Student student4 = new Student(
                "David",
                new ArrayList<>(List.of(dormitory1)),
                "Extroverted",
                new ArrayList<>(Arrays.asList("Social", "Friendly"))
        );

        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);

        // Set students as preferred for dormitories
        dormitory1.setPreferences(new ArrayList<>(Arrays.asList(student1, student2)));
        dormitory2.setPreferences(new ArrayList<>(Arrays.asList(student3)));
    }
}
