package com.asingh.wordcount.wordprocessing;

/**
 *
 * @author arsi
 */

// Strategy pattern to support alternative algorithme used for 
// transforming original word into a form that is used for ranking
// could support e.g. algorthim for word stemming
public interface ProcessWord {
    public String process(String originalWord);
}
