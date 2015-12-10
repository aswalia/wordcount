package com.asingh.wordcount.fileprocessing;

import java.io.IOException;
import junit.framework.Assert;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author arsi
 */
public class TestProcessTextFile {
    
    public TestProcessTextFile() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void fileExists() {
        try {
            prepareSetup("src/test/resources/okTextFile.txt");
        } catch (IOException ex) {
            fail("File exists");
        }
    }

    @Test
    public void fileNotExists() {
        try {
            prepareSetup("bob.dk");
            Assert.fail("File does not exist");
        } catch (IOException ex) {
            assertTrue(true);
        }
    }

    private void prepareSetup(String filename) throws IOException {
        ProcessTextFile processFile = new ProcessTextFile();
        processFile.setFileName(filename);
    }
}
