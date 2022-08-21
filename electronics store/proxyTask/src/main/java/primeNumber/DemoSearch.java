package primeNumber;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The class demonstrates the difference
 * in prime number search time between methods with saving to one collection and to different ones.
 */
public class DemoSearch {
    public static void main(String[] args) {
        SearchPrimeNumber simpleNumber = new SearchPrimeNumber();
        MultiThreadSearch multiThreadSearch = new MultiThreadSearch();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number star search");
        int from = scanner.nextInt();
        System.out.println("Enter number end search");
        int to = scanner.nextInt();
        System.out.println("Enter number of thread search");
        int quantytiThread = scanner.nextInt();
        long time2 = System.currentTimeMillis();
        simpleNumber.searchPrimeNumber(from, to, new ArrayList<>());
        System.out.println("1 thread timer = " + (System.currentTimeMillis() - time2));
        long time = System.currentTimeMillis();
        multiThreadSearch.searchPrimeNumberThreadIternalCollection(from, to, quantytiThread);
        System.out.println("ThreadIternal timer = " + (System.currentTimeMillis() - time));
        long time1 = System.currentTimeMillis();
        multiThreadSearch.searchPrimeNumberThreadShareCollection(from, to, quantytiThread);
        System.out.println("ThreadShare timer = " + (System.currentTimeMillis() - time1));
        ExecuterServiceSearch serviceSearch = new ExecuterServiceSearch();
        long time3 = System.currentTimeMillis();
        serviceSearch.searchPrimeNumberIternalCollection(from, to, quantytiThread);
        System.out.println("Iternal timer = " + (System.currentTimeMillis() - time3));
        long time4 = System.currentTimeMillis();
        serviceSearch.searchPrimeNumberShareCollection(from, to, quantytiThread);
        System.out.println("Share timer = " + (System.currentTimeMillis() - time4));
    }
}
