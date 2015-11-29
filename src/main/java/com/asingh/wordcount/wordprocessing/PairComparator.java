package com.asingh.wordcount.wordprocessing;

import java.util.Comparator;

/**
 *
 * @author arsi
 */
public class PairComparator implements Comparator {

    @Override
    public int compare(Object p, Object q) {
        Pair<String,Integer> p1 = (Pair<String,Integer>)p;
        Pair<String,Integer> q1 = (Pair<String,Integer>)q;
        if (p1.getValue() > q1.getValue()) {
            return 1;
        } else if (p1.getValue() < q1.getValue()) {
            return -1;
        } else {
            return 0;
        }
    }
    
}
