package com.asingh.wordcount.lineprocessing;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arsi
 */
public class ProcessLineFromTextFile {
    public List<String> getWordList(String line) {
        if (line == null) {
            return null;
        }
        // strip all apostrofe
        String strippedLine = line.replaceAll("'", "");
        // strip all dash
        strippedLine = strippedLine.replaceAll("-", "");
        String[] result = strippedLine.split("\\W");
        List<String> wordList = new ArrayList<>();
        // only add non-empty words to word-list
        for (String s: result) {
            if (s.length() > 0) {
                wordList.add(s);
            }
        }
        return wordList;
    }
}