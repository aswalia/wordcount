package com.asingh.wordcount.lineprocessing;

import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author arsi
 */
public class TestProcessLineFromTextFile {
    private ProcessLineFromTextFile tokenizeLine = null;
    
    public TestProcessLineFromTextFile() {
    }
    
    @Before
    public void setUp() {
        tokenizeLine = new ProcessLineFromTextFile();
    }
    
    @After
    public void tearDown() {
        tokenizeLine = null;
    }

    @Test
    public void tokenizeSimpleLine() {
        // input a String - representing the line
        // output a list of words tokenized
        String line = "of thunder and lightning heard.";
        List<String> wordList = tokenizeLine.getWordList(line);
        assertEquals(5, wordList.size());
    }
    
    @Test
    public void tokenizeEmptyLine() {
        String line = "      ";
        List<String> wordList = tokenizeLine.getWordList(line);
        assertEquals(0, wordList.size());
    }
    
    @Test
    public void tokenizeNullLine() {
        List<String> wordList = tokenizeLine.getWordList(null);
        assertEquals(null, wordList);
    }
    
    @Test
    public void tokenizeComplexLineWithApostrofe() {
        String line = "Good boatswain, have care. Where's the master?";
        List<String> wordList = tokenizeLine.getWordList(line);
        assertEquals(7, wordList.size());
        
    }

    @Test
    public void tokenizeComplexLineWithDash() {
        String line = "Re-enter SEBASTIAN, ANTONIO, and GONZALO";
        List<String> wordList = tokenizeLine.getWordList(line);
        assertEquals(5, wordList.size());
        
    }
    
    @Test
    public void tokenizeComplexLine() {
        String line = "    A confused noise within: 'Mercy on us!'-- 'We split, we split!'--'Farewell, my wife and children!'-- 'Farewell, brother!'--'We split, we split, we split!'";
        List<String> wordList = tokenizeLine.getWordList(line);
        assertEquals(24, wordList.size());
        
    }
    
}
