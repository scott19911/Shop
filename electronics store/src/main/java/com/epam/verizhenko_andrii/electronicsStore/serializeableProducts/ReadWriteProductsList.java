package com.epam.verizhenko_andrii.electronicsStore.serializeableProducts;

import com.epam.verizhenko_andrii.electronicsStore.products.Product;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

/**
 *
 The class allows you to serialize and deserialize ProductList
 */

public class ReadWriteProductsList {
    public void writeToFile(Map<Product,Integer> productList, String fileName) {
        try {
            FileOutputStream f = new FileOutputStream(fileName);
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(productList);
            o.close();
            f.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }
    }

    /**
     * Overwrites file n times
     * @param productList - save list
     * @param fileName - file name
     * @param n - overwrites times
     */
    public void writeNTimeToFile(Map<Product,Integer> productList, String fileName, int n) {
        try {
            FileOutputStream f = new FileOutputStream(fileName);
            ObjectOutputStream o = new ObjectOutputStream(f);
            for (int i = 0; i < n; i++) {
                o.writeObject(productList);
            }
            o.close();
            f.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }
    }

    /**
     * Compress file to GZip
     * @param file - compressed file
     * @param gzipFile - output file name
     */
    public void compressGzipFile(String file, String gzipFile) {
        try {
            FileInputStream fis = new FileInputStream(file);
            FileOutputStream fos = new FileOutputStream(gzipFile);
            GZIPOutputStream gzipOS = new GZIPOutputStream(fos);
            byte[] buffer = new byte[1024];
            int len;
            while((len=fis.read(buffer)) != -1){
                gzipOS.write(buffer, 0, len);
            }
            gzipOS.close();
            fos.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     *  deserialize file
     * @param fileName - input file name
     * @return - product list
     */
    public Map<Product,Integer> readProducts(String fileName) {
        Map<Product,Integer> list = new HashMap<>();
        try {
            FileInputStream fi = new FileInputStream(fileName);
            ObjectInputStream oi = new ObjectInputStream(fi);
            list = (Map<Product, Integer>) oi.readObject();
            oi.close();
            fi.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
}
