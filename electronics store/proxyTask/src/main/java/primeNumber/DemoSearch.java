package primeNumber;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The class demonstrates the difference
 * in prime number search time between methods with saving to one collection and to different ones.
 */
public class DemoSearch {
    public static void main(String[] args) {
        SearchPrime simpleNumber = new SearchPrimeImpl();
        MultiThreadSearchPrime multiThreadSearch = new MultiThreadSearch();
        MultiThreadSearchPrime serviceSearch = new ExecuterServiceSearch();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number star search");
        int from = scanner.nextInt();
        System.out.println("Enter number end search");
        int to = scanner.nextInt();
        System.out.println("Enter number of thread search");
        int quantytiThread = scanner.nextInt();
        long time2 = System.currentTimeMillis();
        System.out.println(simpleNumber.searchPrimeNumber(from, to, new ArrayList<>()).size());
        System.out.println("1 thread timer = " + (System.currentTimeMillis() - time2));
        long time = System.currentTimeMillis();
        System.out.println(multiThreadSearch.searchPrimeInternalCollection(from, to, quantytiThread).size());
        System.out.println("ThreadIternal timer = " + (System.currentTimeMillis() - time));
        long time1 = System.currentTimeMillis();
        System.out.println(multiThreadSearch.searchPrimeShareCollection(from, to, quantytiThread).size());
        System.out.println("ThreadShare timer = " + (System.currentTimeMillis() - time1));
        long time3 = System.currentTimeMillis();
        System.out.println(serviceSearch.searchPrimeInternalCollection(from, to, quantytiThread).size());
        System.out.println("Iternal timer = " + (System.currentTimeMillis() - time3));
        long time4 = System.currentTimeMillis();
        System.out.println( serviceSearch.searchPrimeShareCollection(from, to, quantytiThread).size());
        System.out.println("Share timer = " + (System.currentTimeMillis() - time4));
    }
}
