package com.asingh.wordcount.reporting;

import com.asingh.wordcount.wordprocessing.Pair;
import com.asingh.wordcount.wordprocessing.StoreWords;
import java.util.List;

/**
 *
 * @author arsi
 */
public interface Ranking {
    // return a list of the Top <rank> most frequent pairs
    public List<Pair<String,Integer>> top(int rank);
    // return a list of the Bottom <rank> most frequent pairs
    public List<Pair<String,Integer>> bottom(int rank);
    // return the ranking of the word
    public int get(String word);
    // set the store 
    public void setStoreWords(StoreWords s);
}
