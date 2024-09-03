package com.datpl.moeapoc.demo;

import com.datpl.moeapoc.Dormitory;
import com.datpl.moeapoc.Student;
import com.datpl.moeapoc.StudentDormitoryProblem;
import lombok.RequiredArgsConstructor;
import org.moeaframework.algorithm.NSGAII;
import org.moeaframework.core.*;
import org.moeaframework.core.initialization.RandomInitialization;
import org.moeaframework.core.operator.CompoundVariation;
import org.moeaframework.core.operator.binary.BitFlip;
import org.moeaframework.core.operator.binary.HUX;
import org.moeaframework.core.selection.TournamentSelection;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class OptimizationService {

    private static final Logger logger = LoggerFactory.getLogger(OptimizationService.class);
    private final List<Student> students;
    private final List<Dormitory> dormitories;

    public void runOptimization() {

        StudentDormitoryProblem problem = new StudentDormitoryProblem(students, dormitories);

        // The number of solutions in the initial population.
        int populationSize = 100;

        // A population that maintains nondominated solutions.
        NondominatedSortingPopulation population = new NondominatedSortingPopulation();

        // This can be used to maintain a diverse set of solutions
        EpsilonBoxDominanceArchive archive = new EpsilonBoxDominanceArchive(new double[]{0.01, 0.01, 0.01});

        // The mechanism to select parents for crossover. In this case, tournament selection is used with a tournament size of 2.
        Selection selection = new TournamentSelection(2);

        // The operators that apply crossover and mutation. In this case, HUX crossover and bit flip mutation are used.
        Variation variation = new CompoundVariation(
                new HUX(1.0),  // Crossover operator (HUX crossover for binary problems)
                new BitFlip(0.01) // Mutation operator (bit flip for binary problems)
        );
        Initialization initialization = new RandomInitialization(problem);

        // Create the custom NSGA-II algorithm
        Algorithm algorithm = new NSGAII(problem, populationSize, population, archive, selection, variation, initialization);
        algorithm.run(10); // number of generations

        for (Solution solution : algorithm.getResult()) {
            // Extract and log objectives
            double objective1 = solution.getObjective(0); // Students without a dormitory
            double objective2 = solution.getObjective(1); // Overcrowding
            double objective3 = solution.getObjective(2); // Preference mismatch
            // Extract and log decision variables
            StringBuilder assignments = new StringBuilder();
            for (int i = 0; i < solution.getNumberOfVariables(); i++) {
                Variable studentIndex = solution.getVariable(i);
                assignments.append(studentIndex.toString()).append(" ");
            }
            logger.info("Solution:");
            logger.info("Objectives: [Students Without Dormitory: {}, Overcrowding: {}, Preference Mismatch: {}]",
                    objective1, objective2, objective3);
            logger.info("Assignments: {}", assignments.toString().trim());
            // Process the optimization results
        }
    }

    // rematch after optimization
    public void processOptimizationResults(double[] outputValues) {
        int numberOfDormitories = dormitories.size();
        List<Integer> assignments = new ArrayList<>();

        // Convert values from [-1, 1] to [0, 1]
        for (double value : outputValues) {
            double normalizedValue = (value + 1) / 2;  // Normalize to [0, 1]
            int dormitoryIndex = (int) (normalizedValue * (numberOfDormitories - 1));  // Map to dormitory index
            assignments.add(dormitoryIndex);
        }

        // Assign students to dormitories based on computed indices
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            int dormitoryIndex = assignments.get(i);
            Dormitory dormitory = dormitories.get(dormitoryIndex);
            // Ensure the dormitory has not exceeded its capacity
            if (dormitory.getStudents().size() < dormitory.getCapacity()) {
                dormitory.addStudent(student);
            } else {
                // Handle overcrowding or other constraints
            }
        }
        for (Dormitory dormitory : dormitories) {
            logger.info("{} has the following students assigned:", dormitory.getName());
            for (Student student : dormitory.getStudents()) logger.info(" - {}", student.getName());
        }
    }

}
