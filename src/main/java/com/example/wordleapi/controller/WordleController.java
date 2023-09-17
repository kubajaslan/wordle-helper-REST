package com.example.wordleapi.controller;

import com.example.wordleapi.exception.MalformedRequestParamException;
import com.example.wordleapi.helper.Writer;
import com.example.wordleapi.service.WordleService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/words")
public class WordleController {

    @Autowired
    private WordleService wordService;

    @GetMapping("/home")
    public String home() {
        return "docs";
    }

    @GetMapping("/reset")
    public List<String> reset() {
        wordService.resetWords();
        return wordService.getWords();
    }

    @GetMapping("/param")
    public List<String> filterWordsByParams(@RequestParam Map<String, String> params) {
        return wordService.filterWords(params);
    }
}


