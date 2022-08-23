package com.epam.verizhenko_andrii.electronicsStore.fileReader;

import java.io.File;


public class FilterBySize extends FilteredFiles {
    private final Long fromSize;
    private final Long toSize;

    public FilterBySize(Long fromSize, Long toSize) {
        this.fromSize = fromSize;
        this.toSize = toSize;
    }

    @Override
    public boolean doFilter(File file) {
        return file.length() >= fromSize && file.length() <= toSize;
    }
}
