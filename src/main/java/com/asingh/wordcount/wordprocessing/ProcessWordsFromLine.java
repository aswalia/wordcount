package com.asingh.wordcount.wordprocessing;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arsi
 */
public class ProcessWordsFromLine {
    ProcessWord processWord = null;
    
    public ProcessWordsFromLine() {
        processWord = new ProcessWordSimpleImpl();
    }
    
    public void processWordList(List<String> wordList) {
        // make a list of words in lower case from the original list
        List<String> convertedList = new ArrayList<>();
        for (String s:wordList) {
            convertedList.add(processWord.process(s));
        }
        // replace original list with list of lower case words
        wordList.clear();
        wordList.addAll(convertedList);
    }
}
