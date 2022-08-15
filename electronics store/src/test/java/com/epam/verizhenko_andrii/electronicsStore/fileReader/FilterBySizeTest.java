package com.epam.verizhenko_andrii.electronicsStore.fileReader;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

class FilterBySizeTest {

    private File test2;
    private File test1;

    @BeforeEach
    public void createTestFile() {

        test1 = new File("filename.txt");
        test2 = new File("test.xml");
        try {
            test2.createNewFile();
            FileWriter myWriter = new FileWriter(test1);
            myWriter.write("Files in Java might be tricky, but it is fun enough!");
            myWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    public void cleanUp() {
        test2.delete();
        test1.delete();
    }

    @Test
    void testFilter() {

        FilterBySize filter = new FilterBySize(50L, 333L);
        assertEquals(test1, filter.filter(test1));
        assertNotEquals(test2, filter.filter(test2));

    }

}