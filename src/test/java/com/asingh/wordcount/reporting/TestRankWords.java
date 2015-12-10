package com.asingh.wordcount.reporting;

import com.asingh.wordcount.wordprocessing.Pair;
import com.asingh.wordcount.wordprocessing.StoreWords;
import com.asingh.wordcount.wordprocessing.StoreWordsSimpleImpl;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author arsi
 */
public class TestRankWords {
    RankWords ranking = null;
    
    public TestRankWords() {
    }
    
   @Before
    public void setUp() {
        ranking = new RankWords();
    }
    
    @After
    public void tearDown() {
        ranking = null;
    }
    
    @Test
    public void getRankOnNull() {
        ranking.setStoreWords(null);
        assertEquals(0,ranking.get("test"));
    }
    
    @Test
    public void getRankOnEmpty() {
        ranking.setStoreWords(new StoreWordsSimpleImpl());
        assertEquals(0,ranking.get("test"));
    }
    
    @Test
    public void getRankOnOneFound() {
        StoreWords s = new StoreWordsSimpleImpl();
        s.store(Arrays.asList("test"));
        ranking.setStoreWords(s);
        assertEquals(0,ranking.get("test"));
    }
    
    @Test
    public void getRankOnOneNotFound() {
        StoreWords s = new StoreWordsSimpleImpl();
        s.store(Arrays.asList("test"));
        ranking.setStoreWords(s);
        assertEquals(-1,ranking.get("tset"));
    }
    @Test
    public void getRankOnManyFound() {
        StoreWords s = new StoreWordsSimpleImpl();
        s.store(Arrays.asList("test","test","test","doing","it","it","right"));
        ranking.setStoreWords(s);
        assertEquals(2,ranking.get("it"));
    }
    @Test
    public void getRankOnManyNotFound() {
        StoreWords s = new StoreWordsSimpleImpl();
        s.store(Arrays.asList("test","test","test","doing","it","it","right"));
        ranking.setStoreWords(s);
        assertEquals(-1,ranking.get("ti"));
    }

    @Test
    public void getTopOnNull() {
        ranking.setStoreWords(null);
        assertThat(ranking.top(10),is(nullValue()));
    }

    @Test
    public void getTopOnEmpty() {
        ranking.setStoreWords(new StoreWordsSimpleImpl());
        assertThat(ranking.top(10),is(nullValue()));
    }
    
    @Test
    public void getTop3On7() {
        StoreWords s = new StoreWordsSimpleImpl();
        s.store(Arrays.asList("test","test","test","doing","it","it","right","right","not","easy"));
        ranking.setStoreWords(s);
        List<Pair<String,Integer>> result = ranking.top(3);
        assertThat(result,contains(new Pair<>("test",3),new Pair<>("right",2),new Pair<>("it",2)));
    }

    @Test
    public void getTop15On10() {
        StoreWords s = new StoreWordsSimpleImpl();
        s.store(Arrays.asList("test","test","test","doing","it","right","test","it","right","right"));
        ranking.setStoreWords(s);
        List<Pair<String,Integer>> result = ranking.top(15);
        assertThat(result,contains(new Pair<>("test",4),new Pair<>("right",3),new Pair<>("it",2),new Pair<>("doing",1)));
    }
}
