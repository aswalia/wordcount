package com.asingh.wordcount;

import com.asingh.wordcount.fileprocessing.ProcessTextFile;
import com.asingh.wordcount.lineprocessing.ProcessLineFromTextFile;
import com.asingh.wordcount.reporting.RankWords;
import com.asingh.wordcount.reporting.Ranking;
import com.asingh.wordcount.wordprocessing.Pair;
import com.asingh.wordcount.wordprocessing.ProcessWordsFromLine;
import com.asingh.wordcount.wordprocessing.StoreWords;
import com.asingh.wordcount.wordprocessing.StoreWordsSimpleImpl;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: wordcount <textfilename>");
            System.exit(-1);
        }
        String filename = args[0];
        ProcessTextFile processFile = null;
        try {
            processFile = new ProcessTextFile(filename);
        } catch (IOException ex) {
            System.out.println("File processing error:" + ex.getLocalizedMessage());
            System.exit(-1);
        }
        List<String> lineAsString = processFile.getLinesFromTextFile();
        List<String> wordList = null;
        ProcessLineFromTextFile processLine = new ProcessLineFromTextFile();
        StoreWords store = new StoreWordsSimpleImpl();
        ProcessWordsFromLine convertLine = new ProcessWordsFromLine();
        for (String line:lineAsString) {
            wordList = processLine.getWordList(line);
            convertLine.processWordList(wordList);
            store.store(wordList);
        }
        Ranking ranking = new RankWords(store);
        List<Pair<String,Integer>> result = ranking.top(10);
        for (Pair p:result) {
            System.out.println(p.getKey() + " (" + p.getValue() + ")");
        }
    }
}