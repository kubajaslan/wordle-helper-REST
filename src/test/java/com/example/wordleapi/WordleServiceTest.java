package com.example.wordleapi;

import com.example.wordleapi.exception.InvalidParameterFormatException;
import com.example.wordleapi.service.WordleService;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest

public class WordleServiceTest {
    @Autowired
    private WordleService wordleService;

    private static final String TEST_WORDS_FILE_PATH = "words_test.txt";

    @Test
    public void testLoadWords() {


        List<String> loadedWords = wordleService.loadWords(TEST_WORDS_FILE_PATH);

        assertEquals(2, loadedWords.size());
        assertEquals("hello", loadedWords.get(0));
        assertEquals("world", loadedWords.get(1));
    }

    @Test
    public void testFilterWords() throws InvalidParameterFormatException {
        // Setup
        wordleService.loadWords("words.txt");  // Load some example words

        // Test with some parameters
        Map<String, String> params = new HashMap<>();
        params.put("exclude", "a");
        params.put("green", "2b");
        params.put("yellow", "1y");

        List<String> filteredWords = wordleService.filterWords(params);


        assertEquals(1, filteredWords.size());
        assertEquals("ebony", filteredWords.get(0));
    }
}
