package com.example.wordleapi.controller;

import com.example.wordleapi.exception.MalformedRequestParamException;
import com.example.wordleapi.helper.Writer;
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

    private ArrayList<String> words;
    private ArrayList<String> wordsBackup;
    private ArrayList<String> wordsRemove;


    @PostConstruct
    public void loadWords() {

        wordsRemove = new ArrayList<>();

        words = new ArrayList<>();
        wordsBackup = new ArrayList<>();

        try {
            BufferedReader bf = new BufferedReader(new FileReader("src\\main\\resources\\static\\words.txt"));

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
        }


    }

    @GetMapping("")
    public ArrayList<String> getWords() {
        return words;
    }


    @GetMapping("/reset")
    public ArrayList<String> reset() {

        Collections.copy(words, wordsBackup);

        return words;
    }

    @GetMapping("/param")
    public List<String> excludeLettersParameter(@RequestParam Map<String, String> map) {


        String excludeString = map.get("exclude");
        String yellowInclude = map.get("yellow");
        String greenInclude = map.get("green");

        words = exclude(excludeString);

        words = include(yellowInclude, "yellow");
        words = include(greenInclude, "green");


        return words;

    }


    public ArrayList<String> exclude(String excludeString) {
        if (excludeString != null) {
            excludeString = excludeString.toLowerCase();
            for (String word : words) {
                for (int i = 0; i < excludeString.length(); i++) {
                    if (word.indexOf(excludeString.charAt(i)) > -1) {
                        wordsRemove.add(word);
                    }
                }
            }
            words.removeAll(wordsRemove);
        }
        return words;
    }

    public ArrayList<String> include(String includeString, String colour) {


        Map<Integer, String> includeMap = new HashMap<>();


        if (includeString != null) {

            includeString = includeString.toLowerCase();

            for (int i = 0; i < includeString.length(); i += 2) {

                int tempInt = 0;

                String tempString = null;


                //checking if the request is properly formed (if the green string follows the patters digit-char-digit-char... etc)

                if (!Character.isDigit(includeString.charAt(i))) {
                    throw new MalformedRequestParamException("The pattern of the GREEN or YELLOW request parameter does not follow the pattern digit-char-digit-char...");
                }

                if (includeString.length() % 2 != 0) {
                    throw new MalformedRequestParamException("The pattern of the GREEN or YELLOW request parameter does not follow the pattern digit-char-digit-char... and is not even length");
                }

                tempInt = Character.getNumericValue(includeString.charAt(i));

                tempString = Character.toString(includeString.charAt(i + 1));

                includeMap.put(tempInt, tempString);
            }

            for (String word : words) {

                for (Map.Entry<Integer, String> entry : includeMap.entrySet()) {
                    String letter = entry.getValue();
                    int index = entry.getKey();

                    boolean wordContainsLetterAtGivenIndex = Character.toString(word.charAt(index - 1))
                                                                      .equals(letter);

                    if (colour == "green") {
                        if (!wordContainsLetterAtGivenIndex) {
                            wordsRemove.add(word);

                        }
                    }

                    if (colour == "yellow") {
                        if (!word.contains(letter)) {
                            wordsRemove.add(word);
                        }

                        if (word.contains(letter) && wordContainsLetterAtGivenIndex) {
                            wordsRemove.add(word);

                        }
                    }

                }
            }

            words.removeAll(wordsRemove);
        }


        return words;

    }
}


