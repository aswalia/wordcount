package com.asingh.wordcount.wordprocessing;

import com.asingh.wordcount.lineprocessing.ProcessLineFromTextFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.nullValue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author arsi
 */
public class TestStoreWords {
    private StoreWords storeWords = null;
    private ProcessLineFromTextFile splitLine = null;
    private ProcessWordsFromLine convertWords = null;
    private List<String> wordList = null;
    
    public TestStoreWords() {
    }
    
    @Before
    public void setUp() {
        splitLine = new ProcessLineFromTextFile();
        convertWords = new ProcessWordsFromLine(new ProcessWordSimpleImpl());
        storeWords = new StoreWordsSimpleImpl();
    }
    
    @After
    public void tearDown() {
        splitLine = null;
        convertWords = null;
        storeWords = null;
    }
    
    @Test
    public void getNull() {
        storeWords.store(null);
        Object result = storeWords.get(null);
        assertThat(result,is(nullValue()));
    }

    @Test
    public void getEmpty() {
        storeWords.store(new ArrayList<String>());
        Object result = storeWords.get("");
        assertThat(result,is(nullValue()));
    }

    @Test
    public void getFromSetOfOneWord() {
        wordList = Arrays.asList("test");
        storeWords.store(wordList);
        Object result = storeWords.get("test");
        assertTrue("not a Pair",result instanceof Pair);
        Pair<String,Integer> p = (Pair)result;
        assertEquals("Key not matching","test",p.getKey());
        assertEquals("Value not matching", new Integer(1), p.getValue());
    }

    @Test
    public void getFromSetOfMultipleWords() {
        wordList = Arrays.asList("we","are","here","testing","if","we","know","what","we","are","doing");
        storeWords.store(wordList);
        // test a word not in list
        Object result = storeWords.get("test");
        Pair<String,Integer> p = (Pair)result;
        assertThat(p,is(new Pair("test",0)));
         // test a word found several times
        result = storeWords.get("we");
        p = (Pair)result;
        assertThat(p,is(new Pair("we",3)));
        
        // test a word found one time
        result = storeWords.get("testing");
        p = (Pair)result;
        assertThat(p,is(new Pair("testing",1)));
    }
    @Test
    public void storeNullList() {
        storeWords.store(null);
        List<Pair<String,Integer>> actual = storeWords.getAll();
        assertEquals(null,actual);
    }

    @Test
    public void storeEmptyList() {
        storeWords.store(new ArrayList<String>());
        List<Pair<String,Integer>> actual = storeWords.getAll();
        assertEquals(null,actual);
    }

    @Test
    public void storeSingleLine() {
        wordList = splitLine.getWordList("A confused noise within: 'Mercy on us!'-- 'We split, we split!'--'Farewell, my wife and children!'-- 'Farewell, brother!'--'We split, we split, we split!'");
        convertWords.processWordList(wordList);
        storeWords.store(wordList);
        List<Pair<String,Integer>> actual = storeWords.getAll();
        assertThat(actual,containsInAnyOrder(new Pair("confused",1),new Pair("noise",1),new Pair("within",1),
                new Pair("mercy",1),new Pair("on",1),new Pair("us",1),new Pair("we",5),
                new Pair("split",5),new Pair("farewell",2),new Pair("my",1),new Pair("wife",1),new Pair("and",1),
                new Pair("children",1),new Pair("brother",1),new Pair("a",1)));
    }
    
    @Test
    public void stroreMultipleLine() {
        wordList = splitLine.getWordList("Enter a Master and a Boatswain");
        convertWords.processWordList(wordList);
        storeWords.store(wordList);
        wordList = splitLine.getWordList("Master");
        convertWords.processWordList(wordList);
        storeWords.store(wordList);
        wordList = splitLine.getWordList("Boatswain!");
        convertWords.processWordList(wordList);
        storeWords.store(wordList);
        wordList = splitLine.getWordList("Boatswain");
        convertWords.processWordList(wordList);
        storeWords.store(wordList);
        wordList = splitLine.getWordList("Here, master: what cheer?");
        convertWords.processWordList(wordList);
        storeWords.store(wordList);
        wordList = splitLine.getWordList("Master");
        convertWords.processWordList(wordList);
        storeWords.store(wordList);
        wordList = splitLine.getWordList("Good, speak to the mariners: fall to't, yarely,");
        convertWords.processWordList(wordList);
        storeWords.store(wordList);
        List<Pair<String,Integer>> actual = storeWords.getAll();
        assertThat(actual,containsInAnyOrder(new Pair("enter",1),new Pair("a",2),new Pair("master",4),
                new Pair("and",1),new Pair("boatswain",3),new Pair("here",1),new Pair("what",1),
                new Pair("cheer",1),new Pair("good",1),new Pair("speak",1),new Pair("to",1),new Pair("the",1),
                new Pair("mariners",1),new Pair("fall",1),new Pair("tot",1),new Pair("yarely",1)));
        
    }
}
