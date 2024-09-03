package com.datpl.moeapoc;

import org.moeaframework.core.Solution;
import org.moeaframework.core.variable.EncodingUtils;
import org.moeaframework.problem.AbstractProblem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentDormitoryProblem extends AbstractProblem {
    private final List<Student> students;
    private final List<Dormitory> dormitories;

    public StudentDormitoryProblem(List<Student> students, List<Dormitory> dormitories) {
        // The number of variables is equal to the number of students
        // 3 objectives: minimize the number of students without a dormitory, minimize overcrowding, and minimize preference mismatch
        super(students.size(), 3);
        this.students = students;
        this.dormitories = dormitories;
    }

    @Override
    public void evaluate(Solution solution) {
        // Initialize objectives number and the logic of fitness function will be in this method
        int studentsWithoutDormitory = 0;
        int studentsInOvercrowdedDormitory = 0;
        int preferenceMismatch = 0;

        // Clear previous assignments
        for (Dormitory dormitory : dormitories) dormitory.getStudents().clear();
        for (Student student : students) student.getPreferences().clear();

        // Assign students to dormitories based on the solution's variables
        for (int i = 0; i < students.size(); i++) {
            int dormitoryIndex = EncodingUtils.getInt(solution.getVariable(i));
            Student student = students.get(i);
            if (dormitoryIndex == -1) studentsWithoutDormitory++;
            else {
                Dormitory dormitory = dormitories.get(dormitoryIndex);
                dormitory.addStudent(student);
                // Check for overcrowding
                if (dormitory.getStudents().size() > dormitory.getCapacity()) studentsInOvercrowdedDormitory++;
                // Check for preference matching
                if (!student.getPreferences().contains(dormitory) || !dormitory.getPreferences().contains(student)) preferenceMismatch++;
                // check the dormitory trait and student preferences
                if (!dormitory.getStudentPreferences().contains(student.getPersonalityTrait())) preferenceMismatch++;
                // check the student trait and dormitory preferences
                if (student.getDormPreferences().stream()
                        .noneMatch(trait -> trait.equals(dormitory.getDormTrait()))) {
                    preferenceMismatch++;
                }
            }
        }
        // Set the objectives of the solution
        solution.setObjective(0, studentsWithoutDormitory);
        solution.setObjective(1, studentsInOvercrowdedDormitory);
        solution.setObjective(2, preferenceMismatch);
    }

    @Override
    public Solution newSolution() {
        // Create a new solution with the number of variables equal to the number of students
        Solution solution = new Solution(students.size(), 3);

        // Initialize each student's assignment to a dormitory
        for (int i = 0; i < students.size(); i++) {
            // Set a variable with a lower bound of -1 (no assignment) and an upper bound equal to the number of dormitories - 1
            // This means we are generating a random assignment for each student to a dormitory
            solution.setVariable(i, EncodingUtils.newInt(-1, dormitories.size() - 1));
        }
        return solution;
    }
}
