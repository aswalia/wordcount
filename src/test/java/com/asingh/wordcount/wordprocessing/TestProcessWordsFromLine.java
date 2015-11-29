package com.asingh.wordcount.wordprocessing;

import com.asingh.wordcount.lineprocessing.ProcessLineFromTextFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author arsi
 */
public class TestProcessWordsFromLine {
    private String line = "";
    private List<String> wordList = null;
    private ProcessWordsFromLine processWordList = null;

    
    public TestProcessWordsFromLine() {
    }
    
    @Before
    public void setUp() {
        processWordList = new ProcessWordsFromLine();
    }
    
    @After
    public void tearDown() {
        line = null;
        wordList = null;
        processWordList = null;
    }

    @Test
    public void processSimpleWordList() {
        line = "you do assist the storm";
        wordList = new ArrayList<>();
        List<String> expectedList = Arrays.asList("you","do","assist","the","storm");
        prepareSetup();
        processWordList.processWordList(wordList);
        Assert.assertThat(wordList,is(expectedList));
    }

    @Test
    public void processMixedWordList() {
        line = "A confused noise within: 'Mercy on us!'-- 'We split, we split!'--'Farewell, my wife and children!'-- 'Farewell, brother!'--'We split, we split, we split!'";
        wordList = new ArrayList<>();
        List<String> expectedList = Arrays.asList("a","confused","noise","within","mercy","on","us","we","split","we","split","farewell","my","wife","and","children","farewell","brother","we","split","we","split","we","split");
        prepareSetup();
        processWordList.processWordList(wordList);
        Assert.assertThat(wordList,is(expectedList));
    }
    

    private void prepareSetup() {
        ProcessLineFromTextFile processLine = new ProcessLineFromTextFile();
        wordList = processLine.getWordList(line);
    }
    
}
