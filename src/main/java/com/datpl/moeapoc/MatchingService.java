package com.datpl.moeapoc;

import com.datpl.moeapoc.Dormitory;
import com.datpl.moeapoc.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchingService {

    public void initializeData() {
        // Initialize dormitories and students
        MockData.getDormitories();
        MockData.getStudents();
    }

    public void matchStudentsToDormitories() {
        // Matching logic
        List<Student> students = Student.getStudents();
        List<Dormitory> dormitories = Dormitory.getDormitories();

        boolean stable = false;

        while (!stable) {
            stable = true;

            for (Student student : students) {
                if (!student.getPreferences().isEmpty()) {
                    Dormitory preferredDormitory = student.getPreferences().get(0);

                    if (preferredDormitory.getStudents().size() < preferredDormitory.getCapacity()) {
                        preferredDormitory.addStudent(student);
                        student.getPreferences().clear();
                    } else {
                        Student leastPreferred = preferredDormitory.getStudents().get(preferredDormitory.getStudents().size() - 1);

                        if (preferredDormitory.getPreferences().indexOf(student) <
                                preferredDormitory.getPreferences().indexOf(leastPreferred)) {
                            preferredDormitory.removeStudent(leastPreferred);
                            preferredDormitory.addStudent(student);
                            student.getPreferences().clear();
                            stable = false;
                        }
                    }
                }
            }
        }
    }

    public String getMatchingResults() {
        StringBuilder results = new StringBuilder();
        for (Dormitory dormitory : Dormitory.getDormitories()) {
            results.append(dormitory.getName()).append(" has the following students assigned:\n");
            for (Student student : dormitory.getStudents()) {
                results.append("  - ").append(student.getName()).append("\n");
            }
            results.append("\n");
        }
        return results.toString();
    }
}
