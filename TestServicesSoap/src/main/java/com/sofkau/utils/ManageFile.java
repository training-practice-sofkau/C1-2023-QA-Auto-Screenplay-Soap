package com.sofkau.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ManageFile {

    public static String readFile(String path) {
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(path))) {
            while ((line = br.readLine()) != null)
                stringBuilder.append(line).append("\n");

        } catch (IOException ioException) {
            System.out.println("ERROR");
        }

        return stringBuilder.toString();
    }
}