package com.epam.verizhenko_andrii.electronicsStore.fileReader;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class FilterByModifiedDateTest {

    private File test1;
    private File test2;

    @BeforeEach
    public void createTestFile() {
        test2 = new File("test.xml");
        test1 = new File("filename.txt");

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            Date newLastModifiedDate = dateFormat.parse("11/09/1991");
            test2.createNewFile();
            FileWriter myWriter = new FileWriter(test1);
            myWriter.write("Files in Java might be tricky, but it is fun enough!");
            myWriter.close();
            test1.setLastModified(newLastModifiedDate.getTime());
        } catch (IOException | ParseException e) {
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

        FilterByModifiedDate filter = new FilterByModifiedDate("1990/01/01 12:45:44", "2000/12/12 12:11:44");
        assertEquals(test1, filter.filter(test1));
        assertNotEquals(test2, filter.filter(test2));

    }


}