package com.asingh.wordcount.wordprocessing;

/**
 *
 * @author arsi
 */
public class ProcessWordSimpleImpl implements ProcessWord {

    @Override
    public String process(String originalWord) {
        return originalWord.toLowerCase();
    }
    
}
