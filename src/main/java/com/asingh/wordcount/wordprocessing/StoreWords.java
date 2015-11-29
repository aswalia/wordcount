package com.asingh.wordcount.wordprocessing;

import java.util.List;

/**
 *
 * @author arsi
 */
// Strategy pattern to support alternative algorithms for storing the list 
// of words. Can e.g. support a storing the words with a reference to a list
// of positions in the text file where the word is found
public interface StoreWords {
    public void store(List<String> wordList);
    // get the entry for a particular key
    public Object get(String key);
    // get all entirs
    public List<Pair<String,Integer>> getAll();
}
