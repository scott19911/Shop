package com.epam.verizhenko_andrii.electronicsStore.fileReader;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class FilterByNameTest {

    private File test1;
    private File test2;

    @BeforeEach
    public void createTestFile() {
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
        test1.delete();
        test2.delete();
    }

    @Test
    void testFilter() {

        FilterByName filter = new FilterByName("test");
        assertEquals(test1, filter.filter(test1));
        assertEquals(test2, filter.filter(test2));
    }

}