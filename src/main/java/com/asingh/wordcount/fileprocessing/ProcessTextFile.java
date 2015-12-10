package com.asingh.wordcount.fileprocessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arsi
 */
public class ProcessTextFile {

    private String path = "";
    private List<String> lineAsString = null;
    private String fileName = null;

    public void setFileName(String filename) throws IOException {
        path = new File(filename).getAbsolutePath();
        lineAsString = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                lineAsString.add(line);
            }
        }
    }

    public List<String> getLinesFromTextFile() {
        return lineAsString;
    }
}
