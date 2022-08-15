package com.epam.verizhenko_andrii.electronicsStore.fileReader;

import java.io.File;


/**
 * The class filters all files by the presence of specified characters in the file name
 */
public class FilterByName extends FilteredFiles {
    private final String fileName;

    public FilterByName(String fileName) {
        this.fileName = fileName;
    }


    /**
     *
     * @param file - filtered file
     * @return - List<Path> that filtered by period time
     */
    @Override
    public boolean doFilter(File file) {
        return file.getName().contains(fileName);
    }

}
