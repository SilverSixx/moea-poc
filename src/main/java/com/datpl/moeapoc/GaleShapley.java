package com.datpl.moeapoc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GaleShapley {

    public static Map<Student, Dormitory> match(List<Student> students, List<Dormitory> dormitories) {
        // Initialize the matching map
        Map<Student, Dormitory> matching = new HashMap<>();

        // Initialize the list of students without a dormitory
        List<Student> studentsWithoutDormitory = new ArrayList<>(students);

        // Loop until all students are matched
        while (!studentsWithoutDormitory.isEmpty()) {
            // Get the first student without a dormitory
            Student student = studentsWithoutDormitory.get(0);
            // Get the preferences of the student
            List<Dormitory> preferences = student.getPreferences();

            // Loop through the student's preferences in order
            for (Dormitory dormitory : preferences) {
                // If the dormitory is not full, assign the student
                if (dormitory.getCapacity() > 0) {
                    matching.put(student, dormitory);
                    dormitory.setCapacity(dormitory.getCapacity() - 1);
                    studentsWithoutDormitory.remove(student);
                    break;
                } else {
                    // Dormitory is full, check if the new student is preferred
                    Student currentStudent = matching.entrySet().stream()
                            .filter(entry -> entry.getValue().equals(dormitory))
                            .findFirst()
                            .get()
                            .getKey();
                    List<Student> dormitoryPreferences = dormitory.getPreferences();

                    if (dormitoryPreferences.indexOf(student) < dormitoryPreferences.indexOf(currentStudent)) {
                        // Reassign the dormitory to the new student
                        matching.put(student, dormitory);
                        dormitory.setCapacity(dormitory.getCapacity()); // No change in capacity
                        studentsWithoutDormitory.remove(student);
                        studentsWithoutDormitory.add(currentStudent);
                        matching.remove(currentStudent);
                        break;
                    }
                }
            }
        }

        // Ensure the matching is stable
        for (Map.Entry<Student, Dormitory> entry : matching.entrySet()) {
            Student student = entry.getKey();
            Dormitory dormitory = entry.getValue();
            List<Student> dormitoryPreferences = dormitory.getPreferences();

            for (Student otherStudent : dormitoryPreferences) {
                if (dormitoryPreferences.indexOf(otherStudent) < dormitoryPreferences.indexOf(student)) {
                    if (matching.get(otherStudent) != dormitory) {
                        // If the dormitory prefers another student over the current match
                        // and that other student is not currently assigned to this dormitory
                        // then the matching is unstable.
                        return null;  // Indicate that the matching is not stable
                    }
                }
            }
        }

        return matching;  // Return the stable matching
    }

}
