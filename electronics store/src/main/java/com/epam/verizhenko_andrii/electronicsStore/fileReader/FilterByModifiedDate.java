package com.epam.verizhenko_andrii.electronicsStore.fileReader;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FilterByModifiedDate extends FilteredFiles{
    private final long fromDate;
    private final long toDate;

    public FilterByModifiedDate(String fromDate,String toDate) {
        this.fromDate =  timeMilliseconds(fromDate);
        this.toDate = timeMilliseconds(toDate);
    }


    /**
     *
     * @param file - filtered file
     * @return - boolean that filtered by period time
     */
    @Override
    public boolean doFilter(File file) {
        return file.lastModified() >= fromDate && file.lastModified() <= toDate;
    }

    /**
     *parses the given string, returning the milliseconds elapsed since the epoch
     * @param myDate - date in the format yyyy/MM/dd HH:mm:ss
     * @return - milliseconds elapsed since the epoch
     */
    private long timeMilliseconds(String myDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date;
        try {
            date = sdf.parse(myDate);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date.getTime();
    }
}
