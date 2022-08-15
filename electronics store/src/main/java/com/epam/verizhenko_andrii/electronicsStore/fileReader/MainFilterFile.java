package com.epam.verizhenko_andrii.electronicsStore.fileReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainFilterFile {

    public static FilteredFiles next;
    private static File dir;
    private static List<File> fileList = new ArrayList<>();

    public static void main(String[] args) {
        init();
        if (next == null) {
            System.out.println("Не выбрано не одного фильтра");
            return;
        }
        searchAllFiles(dir);
        System.out.println(fileList);
    }

    /**
     * Appends files found in the given directory and its folders
     *
     * @param dir - given directory
     */
    public static void searchAllFiles(File dir) {
        if (dir.isFile()) {
            if (next.filter(dir) != null) {
                fileList.add(dir);
            }
        } else {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File dirOrFile : files) {
                    searchAllFiles(dirOrFile);
                }
            }
        }
    }

    /**
     * Initializes the filter chain. By setting filtering parameters and file paths
     */
    public static void init() {
        Scanner sc = new Scanner(System.in);
        System.out.println("введите путь: ");
        dir = new File(sc.next());
        System.out.println("искать по имени файла? (0\\1)");
        if (sc.nextInt() == 1) {
            System.out.println("введите имя файла");
            next = new FilterByName(sc.next());
        }
        System.out.println("искать по расширению файла? (0\\1)");
        if (sc.nextInt() == 1) {
            System.out.println("введите тип расширение");
            if (next == null) {
                next = new FilterByType(sc.next());
            } else {
                FilteredFiles last = FilteredFiles.nextFilter(next);
                last.setNext(new FilterByType(sc.next()));
            }
        }
        System.out.println("искать по размеру файла? (0\\1)");
        if (sc.nextInt() == 1) {
            System.out.println("введите min размер файла в байтах");
            long from = sc.nextLong();
            System.out.println("введите max размер файла в байтах");
            long to = sc.nextLong();
            if (next == null) {
                next = new FilterBySize(from, to);
            } else {
                FilteredFiles last = FilteredFiles.nextFilter(next);
                last.setNext(new FilterBySize(from, to));
            }
        }
        System.out.println("искать по дате изминения файла? (0\\1)");
        if (sc.nextInt() == 1) {
            System.out.println("введите min дату в формате yyyy/MM/dd HH:mm:ss");
            sc.nextLine();
            String from = sc.nextLine();
            System.out.println("введите max дату в формате yyyy/MM/dd HH:mm:ss");
            String to = sc.nextLine();
            if (next == null) {
                next = new FilterByModifiedDate(from, to);
            } else {
                FilteredFiles last = FilteredFiles.nextFilter(next);
                last.setNext(new FilterByModifiedDate(from, to));
            }

        }
    }


}
