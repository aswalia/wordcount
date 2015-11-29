package com.asingh.wordcount.wordprocessing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 *
 * @author arsi
 */
public class StoreWordsSimpleImpl implements StoreWords {
    private HashMap<String,Integer> wordStorage = null;
    
    public StoreWordsSimpleImpl() {
        wordStorage = new HashMap<>();
    }

    @Override
    public void store(List<String> wordList) {
        if (wordList == null) {
            return;
        }
        Integer v,w;
        for (String s: wordList) {
            if (wordStorage.containsKey(s)) {
                v = wordStorage.get(s);
                w = v+1;
                wordStorage.put(s,w);
            }
            else {
                wordStorage.put(s, 1);
            }
        }
    }

    @Override
    public Object get(String key) {
        if (wordStorage.isEmpty()) {
            return null;
        }
        Integer value = wordStorage.get(key);
        if (value == null) {
            value = 0;
        }
        Pair p = new Pair(key,value);
        return p;
    }

    @Override
    public List<Pair<String,Integer>> getAll() {
        if (wordStorage.isEmpty()) {
            return null;
        }
        List<Pair<String,Integer>> result = new ArrayList<>();
        Set<String> keys = wordStorage.keySet();
        for (String key:keys) {
            result.add((Pair)get(key));
        }
        return result;
    }
    
}
