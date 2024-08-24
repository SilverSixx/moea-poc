package com.datpl.moeapoc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/matching")
public class MatchingController {

    @Autowired
    private MatchingService matchingService;

    @GetMapping("/initialize")
    public String initializeData() {
        matchingService.initializeData();
        return "Data initialized.";
    }

    @GetMapping("/run")
    public String runMatching() {
        matchingService.matchStudentsToDormitories();
        return "Matching completed.";
    }

    @GetMapping("/results")
    public String getResults() {
        return matchingService.getMatchingResults();
    }
}
