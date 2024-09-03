package com.datpl.moeapoc.demo;

import com.datpl.moeapoc.Dormitory;
import com.datpl.moeapoc.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchingService {

    private final List<Student> students;
    private final List<Dormitory> dormitories;

    // Gale-Shapley algorithm (also known as the Stable Marriage Problem) for matching students to dormitories
    // the key is to ensure that no student-dormitory pair exists where both the student prefers a different dormitory over their current match,
    // and that dormitory also prefers the student over at least one of its currently matched students.
    public void matchStudentsToDormitories() {
        boolean stable = false;
        // Keep iterating until all students are matched and the matching is stable
        while (!stable) {
            stable = true;
            for (Student student : students) {
                // If the student is not currently assigned to a dormitory
                if (student.getPreferences().isEmpty()) continue;

                Dormitory preferredDormitory = student.getPreferences().get(0);

                // If the dormitory has capacity, assign the student
                if (preferredDormitory.getStudents().size() < preferredDormitory.getCapacity()) {
                    preferredDormitory.addStudent(student);
                    student.getPreferences().remove(preferredDormitory); // Remove the assigned dormitory from preferences
                } else {
                    // If the dormitory is full, check if the student is preferred over the least preferred current resident
                    Student leastPreferred = preferredDormitory.getStudents().get(preferredDormitory.getStudents().size() - 1);

                    // Dormitory prefers the new student more than its least preferred current resident
                    if (preferredDormitory.getPreferences().indexOf(student) <
                            preferredDormitory.getPreferences().indexOf(leastPreferred)) {
                        // Replace the least preferred student with the new one
                        preferredDormitory.removeStudent(leastPreferred);
                        preferredDormitory.addStudent(student);
                        student.getPreferences().remove(preferredDormitory); // Remove the assigned dormitory from preferences

                        // The replaced student is now unmatched and will have to propose to the next dormitory on their list
                        leastPreferred.getPreferences().remove(preferredDormitory); // Remove the dormitory that rejected the student
                        stable = false; // We need to run another iteration
                    }
                }
            }
            // Check if all students are matched and if any student still prefers a different dormitory over the current one
            for (Student student : students) {
                if (!student.getPreferences().isEmpty()) {
                    stable = false;
                    break;
                }
            }
        }
    }

    public String getMatchingResults() {
        StringBuilder results = new StringBuilder();
        for (Dormitory dormitory : dormitories) {
            results.append(dormitory.getName()).append(" has the following students assigned:\n");
            for (Student student : dormitory.getStudents()) {
                results.append(" - ").append(student.getName()).append("\n");
            }
            results.append("\n");
        }
        return results.toString();
    }
}

