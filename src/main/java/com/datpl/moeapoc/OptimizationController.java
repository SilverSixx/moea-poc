package com.datpl.moeapoc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/optimization")
public class OptimizationController {
    @Autowired
    private OptimizationService optimizationService;

    @GetMapping("/run")
    public String runOptimization() {
        optimizationService.runOptimization();
        return "Optimization completed.";
    }
}
