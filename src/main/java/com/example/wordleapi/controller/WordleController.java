package com.example.wordleapi.controller;

import com.example.wordleapi.exception.InvalidParameterFormatException;
import com.example.wordleapi.service.WordleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/words")
public class WordleController {

    @Autowired
    private WordleService wordleService;

    @GetMapping("/home")
    public String home() {
        return "docs";
    }

    @GetMapping("/reset")
    public List<String> reset() {
        wordleService.resetWords();
        return wordleService.getWords();
    }

    @GetMapping("/param")
    public List<String> filterWordsByParams(@RequestParam Map<String, String> params) throws InvalidParameterFormatException {
        return wordleService.filterWords(params);
    }
}


