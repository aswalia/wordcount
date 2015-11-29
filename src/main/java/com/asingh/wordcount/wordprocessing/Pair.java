package com.asingh.wordcount.wordprocessing;

import java.util.Objects;

/**
 *
 * @author arsi
 */
public class Pair<S,I> {
    private S key = null;
    private I value = null;
    
    public Pair(S k, I v) {
        key = k;
        value = v;
    }
    
    public S getKey() {
        return key;
    }
    
    public I getValue() {
        return value;
    }
    
    @Override
    public boolean equals(Object p) {
        if (!(p instanceof Pair)) {
            return false;
        }
        Pair pair = (Pair)p;
        return (this.key.equals(pair.key)) && 
               (this.value.equals(pair.value));
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.key);
        hash = 43 * hash + Objects.hashCode(this.value);
        return hash;
    }

    @Override
    public String toString() {
        return "("+key+","+value+")";
    }
}
