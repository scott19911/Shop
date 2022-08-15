package com.epam.verizhenko_andrii.electronicsStore.fileReader;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class ReadTextFileByLineTest {
    private File test1;
    @BeforeEach
    public void createTestFile() {
        test1 = new File("filename.txt");
        try {
            FileWriter myWriter = new FileWriter(test1);
            myWriter.write("Files in Java\nmight be tricky\nbut it is fun enough!");
            myWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    public void cleanUp() {
        test1.delete();
    }
    @Test
    void iteratorTest(){
        ReadTextFileByLine readTextFileByLine = new ReadTextFileByLine(test1.getName());
        Iterator<String> itr = readTextFileByLine.iterator();
        String exp = "Files in Java";
        assertTrue(itr.hasNext());
        assertEquals(exp, itr.next());
        for (String s: readTextFileByLine
             ) {
            assertNotNull(s);
        }
    }

}