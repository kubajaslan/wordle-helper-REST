package com.example.wordleapi.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WordleService {

    private List<String> words;
    private List<String> wordsBackup;

    @PostConstruct
    public void loadWords() {
        words = new ArrayList<>();
        wordsBackup = new ArrayList<>();

        try {
            BufferedReader bf = new BufferedReader(new FileReader("words.txt"));

            String line = bf.readLine();

            while (line != null) {
                words.add(line);
                wordsBackup.add(line);
                line = bf.readLine();
            }

            bf.close();

            System.out.println(words.toString());

        } catch (IOException e) {
            System.out.println(e.getMessage());
            words.add("empty");
        }

    }

    public void resetWords() {
        words.clear();
        words.addAll(wordsBackup);
    }

    public List<String> filterWords(Map<String, String> params) {
        List<String> filteredWords = new ArrayList<>(words);

        String excludeString = params.get("exclude");
        String yellowInclude = params.get("yellow");
        String greenInclude = params.get("green");

        // Apply filtering based on parameters
        filteredWords = exclude(filteredWords, excludeString);
        filteredWords = include(filteredWords, greenInclude, yellowInclude);


        return filteredWords;
    }

    private List<String> exclude(List<String> words, String excludeString) {
        if (excludeString != null) {
            excludeString = excludeString.toLowerCase();
            List<String> wordsToRemove = new ArrayList<>();

            for (String word : words) {
                for (int i = 0; i < excludeString.length(); i++) {
                    if (word.indexOf(excludeString.charAt(i)) > -1) {
                        wordsToRemove.add(word);
                    }
                }
            }

            words.removeAll(wordsToRemove);
        }

        return words;
    }

    private List<String> include(List<String> words, String greenLetters, String yellowLetters) {
        List<String> wordsToRemove = new ArrayList<>();
        if (greenLetters != null) {
            greenLetters = greenLetters.toLowerCase();
            Map<Integer, String> includeMap = new HashMap<>();


            for (int i = 0; i < greenLetters.length(); i += 2) {
                int index = Character.getNumericValue(greenLetters.charAt(i));
                String letter = String.valueOf(greenLetters.charAt(i + 1));
                includeMap.put(index, letter);
            }


            for (String word : words) {
                for (Map.Entry<Integer, String> entry : includeMap.entrySet()) {
                    int index = entry.getKey();
                    String letter = entry.getValue();

                    if (!String.valueOf(word.charAt(index - 1))
                               .equals(letter)) {
                        wordsToRemove.add(word);
                    }
                }
            }
        }

        if (yellowLetters != null) {
            for (String word : words) {
                for (char letter : yellowLetters.toCharArray()) {
                    if (!word.contains(String.valueOf(letter))) {
                        wordsToRemove.add(word);
                    }
                }
            }
            words.removeAll(wordsToRemove);
        }


        words.removeAll(wordsToRemove);
        return words;
    }

    public List<String> getWords() {
        return words;
    }
}





