package com.datpl.moeapoc.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/match")
@RequiredArgsConstructor
public class MatchingController {

    private final MatchingService matchingService;

    @GetMapping("/run")
    public String runMatching() {
        matchingService.matchStudentsToDormitories();
        return "Matching completed.";
    }

    @GetMapping("/res")
    public String getResults() {
        return matchingService.getMatchingResults();
    }
}
