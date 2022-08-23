package com.epam.verizhenko_andrii.electronicsStore.fileReader;

import java.io.File;


public abstract class FilteredFiles {
    private FilteredFiles next;

    public static FilteredFiles nextFilter(FilteredFiles filteredFiles) {
        FilteredFiles next = filteredFiles;
        while (next.getNext() != null) {
            next = next.getNext();
        }
        return next;
    }

    public FilteredFiles getNext() {
        return next;
    }

    public void setNext(FilteredFiles next) {
        this.next = next;
    }

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
