package com.example.wordleapi.helper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Writer {
//in case the txt file is lost this can be used to write to file from the controller PostConstruct
    public void write(ArrayList<String> words) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("words.txt"));

            for (String str : words ) {
                writer.write(str + System.lineSeparator());
            }

            writer.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
