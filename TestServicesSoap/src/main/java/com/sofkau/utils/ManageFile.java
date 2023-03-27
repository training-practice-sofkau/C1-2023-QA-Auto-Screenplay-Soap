package com.sofkau.utils;


import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ManageFile {

    private static final Logger LOGGER= Logger.getLogger(ManageFile.class);

    public static String readFile (String path){
        String line;
        StringBuilder stringBuilder= new StringBuilder();
        try (BufferedReader br= Files.newBufferedReader(Paths.get(path))){
            while((line= br.readLine())!=null)
                stringBuilder.append(line+"\n");

        }catch (IOException ioException){
            LOGGER.warn(" **** tengo problemas con la ruta especificada de los archivos ***");
            LOGGER.info(ioException.getMessage());
        }

        return stringBuilder.toString();
    }


    public static String readFiles(String path1, String path2) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader br1 = Files.newBufferedReader(Paths.get(path1));
             BufferedReader br2 = Files.newBufferedReader(Paths.get(path2))) {
            String line;
            while ((line = br1.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            while ((line = br2.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        } catch (IOException ioException) {
            LOGGER.warn("**** tengo problemas con la ruta especificada de los archivos ***");
            LOGGER.info(ioException.getMessage());
        }
        return stringBuilder.toString();
    }
}
