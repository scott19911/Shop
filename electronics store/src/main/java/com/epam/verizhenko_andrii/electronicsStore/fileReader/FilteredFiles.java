package com.epam.verizhenko_andrii.electronicsStore.fileReader;

import java.io.File;

/**
 *
 * filters files by given parameters
 */
public abstract class FilteredFiles {
    private FilteredFiles next;

    /**
     *setting the next filter
     * @param filteredFiles - next filter
     * @return - filter
     */
    public static FilteredFiles nextFilter(FilteredFiles filteredFiles) {
        FilteredFiles next = filteredFiles;
        while (next.getNext() != null) {
            next = next.getNext();
        }
        return next;
    }

    /**
     * get next filter
     * @return - filter
     */
    public FilteredFiles getNext() {
        return next;
    }

    public void setNext(FilteredFiles next) {
        this.next = next;
    }

    /**
     *  filters files
     * @param file - check file
     * @return - filtered files
     */
    public File filter(File file) {
        if (!doFilter(file)) {
            return null;
        }

        if (next != null) {
            return next.filter(file);
        }
        return file;
    }

    public abstract boolean doFilter(File file);


}
