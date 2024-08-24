package com.datpl.moeapoc;

import org.moeaframework.core.Solution;
import org.moeaframework.core.variable.EncodingUtils;
import org.moeaframework.problem.AbstractProblem;

public class StudentDormitoryProblem extends AbstractProblem {

    private int numberOfStudents;
    private int numberOfDormitories;

    public StudentDormitoryProblem() {
        // The number of variables is equal to the number of students
        // 2 objectives: minimize the number of students without a dormitory and minimize overcrowding
        super(Student.getStudents().size(), 2);
        this.numberOfStudents = Student.getStudents().size();
        this.numberOfDormitories = Dormitory.getDormitories().size();
    }

    @Override
    public void evaluate(Solution solution) {

        // Initialize the number of students without a dormitory
        int studentsWithoutDormitory = 0;
        int studentsInOvercrowdedDormitory = 0;

        // Clear previous assignments
        for (Dormitory dormitory : Dormitory.getDormitories()) {
            dormitory.getStudents().clear();
        }

        // Assign students to dormitories based on the solution's variables
        for (int i = 0; i < numberOfStudents; i++) {
            int dormitoryIndex = EncodingUtils.getInt(solution.getVariable(i));
            if (dormitoryIndex == -1) {
                studentsWithoutDormitory++;
            } else {
                Dormitory dormitory = Dormitory.getDormitories().get(dormitoryIndex);
                dormitory.addStudent(Student.getStudents().get(i));
                if (dormitory.getStudents().size() > dormitory.getCapacity()) {
                    studentsInOvercrowdedDormitory++;
                }
            }
        }

        // Set the objectives of the solution
        solution.setObjective(0, studentsWithoutDormitory);
        solution.setObjective(1, studentsInOvercrowdedDormitory);

        // No constraints in this case; only objectives are considered
    }

    @Override
    public Solution newSolution() {

        // Create a new solution with the number of variables equal to the number of students
        Solution solution = new Solution(numberOfStudents, 2);

        // Initialize each student's assignment to a dormitory
        for (int i = 0; i < numberOfStudents; i++) {
            // Set a variable with a lower bound of -1 (no assignment) and an upper bound equal to the number of dormitories - 1
            solution.setVariable(i, EncodingUtils.newInt(-1, numberOfDormitories - 1));
        }

        return solution;
    }
}
