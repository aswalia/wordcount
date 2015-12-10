package com.asingh.wordcount.reporting;

import com.asingh.wordcount.wordprocessing.Pair;
import com.asingh.wordcount.wordprocessing.PairComparator;
import com.asingh.wordcount.wordprocessing.StoreWords;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author arsi
 */
public class RankWords implements Ranking {
    
    private StoreWords store = null;
    private List<Pair<String,Integer>> sortedList = null;
    
    @Override
    public void setStoreWords(StoreWords s) {
        store = s;
        if (store != null) {
            sort();
        }
    }

    @Override
    public List<Pair<String,Integer>> top(int rank) {
        List<Pair<String,Integer>> result = null;
        if (sortedList != null) {
            result = new ArrayList<>();
            int count = rank;
            for (int i = sortedList.size()-1; (count > 0) && (i >= 0); count --,i--) {
                result.add(sortedList.get(i));
            }
        }
        return result;
    }

    @Override
    public List<Pair<String,Integer>> bottom(int rank) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int get(String word) {
        if (sortedList == null) {
            return 0;
        }
        Pair found = null;
        for (Pair p:sortedList) {
            if (word.equals(p.getKey())) {
                found = p;
                break;
            }
        }
        if (found != null) {
            return sortedList.indexOf(found);
        } else {
            return -1;
        }
    }
    
    private void sort() {
        List<Pair<String,Integer>> list = store.getAll();
        if (list != null) {
            sortedList = new ArrayList<>();
            Collections.sort(list, new PairComparator());
            sortedList.addAll(list);
        }
    }
}
