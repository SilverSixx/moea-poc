package com.datpl.moeapoc.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/opt")
@RequiredArgsConstructor
public class OptimizationController {

    private final OptimizationService optimizationService;

    @GetMapping("/run")
    public String runOptimization() {
        optimizationService.runOptimization();
        return "Optimization completed.";
    }
}
