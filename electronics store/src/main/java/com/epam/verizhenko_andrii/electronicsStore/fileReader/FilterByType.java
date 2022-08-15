package com.epam.verizhenko_andrii.electronicsStore.fileReader;

import java.io.File;

public class FilterByType extends FilteredFiles{
    String type;

    public FilterByType(String type) {
        this.type = type;
    }

    @Override
    public boolean doFilter(File file) {
        return file.getName().endsWith(type);
    }
}
