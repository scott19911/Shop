package com.epam.verizhenko_andrii.electronicsStore.fileReader;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

class FilterByTypeTest {
    private static final InputStream STD_IN = System.in;
    private static final PrintStream STD_OUT = System.out;
    private File test1;
    private File test2;
    @BeforeEach
    public void createTestFile(){
        test1 = new File("test.txt");
        test2 = new File("test.xml");
        try {
            test1.createNewFile();
            test2.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @AfterEach
    public void cleanUp() {
        System.setIn(STD_IN);
        System.setOut(STD_OUT);
        test1.delete();
        test2.delete();
    }

    @Test
    void testFilter() {
        FilterByType filter = new FilterByType("txt");
        assertEquals(test1,filter.filter(test1));
        assertNotEquals(test2,filter.filter(test2));
    }
}