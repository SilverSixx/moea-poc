package com.datpl.moeapoc;

import org.moeaframework.algorithm.NSGAII;
import org.moeaframework.core.Algorithm;
import org.moeaframework.core.NondominatedPopulation;
import org.moeaframework.core.Solution;
import org.moeaframework.core.operator.real.SBX;
import org.moeaframework.core.selection.TournamentSelection;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptimizationService {

    public void runOptimization() {
        // Initialize students and dormitories
        List<Student> students = Student.getStudents();
        List<Dormitory> dormitories = Dormitory.getDormitories();

        // Create the problem instance
        StudentDormitoryProblem problem = new StudentDormitoryProblem();

        // Set up the NSGA-II algorithm
//        Algorithm algorithm = new NSGAII(
//                problem,
//                new TournamentSelection(),
//                new SBX(1.0, 20), // crossover
//                new PolynomialMutation(1.0 / problem.getNumberOfVariables(), 20), // mutation
//                100 // population size
//        );

        // Run the algorithm for a specified number of generations
//        algorithm.run(250); // number of generations
//
//        // Get the results
//        for (Solution solution : algorithm.getResult()) {
//            // Process or print the results
//            int studentsWithoutDormitory = (int) solution.getObjective(0);
//            int studentsInOvercrowdedDormitory = (int) solution.getObjective(1);
//            System.out.println("Students without dormitory: " + studentsWithoutDormitory);
//            System.out.println("Students in overcrowded dormitory: " + studentsInOvercrowdedDormitory);
//        }
    }
}
