package com.asingh.wordcount;

import com.asingh.wordcount.fileprocessing.ProcessTextFile;
import com.asingh.wordcount.lineprocessing.ProcessLineFromTextFile;
import com.asingh.wordcount.reporting.Ranking;
import com.asingh.wordcount.wordprocessing.Pair;
import com.asingh.wordcount.wordprocessing.ProcessWordsFromLine;
import com.asingh.wordcount.wordprocessing.StoreWords;
import java.io.IOException;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: wordcount <textfilename> <n>");
            System.exit(-1);
        }
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        
        String filename = args[0];
        Integer rank = Integer.parseInt(args[1]);
        ProcessTextFile processFile = (ProcessTextFile) context.getBean("processFile");
        try {
            processFile.setFileName(filename);
        } catch (IOException ex) {
            System.out.println("File processing error:" + ex.getLocalizedMessage());
            System.exit(-1);
        }
        List<String> lineAsString = processFile.getLinesFromTextFile();
        List<String> wordList = null;
        ProcessLineFromTextFile processLine = (ProcessLineFromTextFile) context.getBean("processLine");
        StoreWords store = (StoreWords) context.getBean("store");
        ProcessWordsFromLine convertLine = (ProcessWordsFromLine) context.getBean("convertLine");
        for (String line:lineAsString) {
            wordList = processLine.getWordList(line);
            convertLine.processWordList(wordList);
            store.store(wordList);
        }
        Ranking ranking = (Ranking) context.getBean("ranking");
        ranking.setStoreWords(store);
        List<Pair<String,Integer>> result = ranking.top(rank);
        for (Pair p:result) {
            System.out.println(p.getKey() + " (" + p.getValue() + ")");
        }
    }
}