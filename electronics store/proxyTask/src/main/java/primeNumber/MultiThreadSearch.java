package primeNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * search for primes in a multi-threaded
 */
public class MultiThreadSearch {
    /**
     *Writing by all threads to one collection
     * @param from - start number
     * @param to - end search number
     * @param numberThread - quantity threads
     * @return - list of primes number
     */
    public List<Integer> searchPrimeNumberThreadIternalCollection(int from, int to, int numberThread) {
        List<Integer> integerList = new ArrayList<>();
        SearchPrimeNumber primeNumber = new SearchPrimeNumber();
        FutureTask<List<Integer>> future = null;
        Lock lock = new ReentrantLock();
        int i = 1;
        while (i < numberThread + 1) {
            int partRange = to / numberThread;
            int newTo = partRange * i;
            int newFrom = from;
            Callable task = () -> {
                try {
                    lock.lock();
                    return primeNumber.searchPrimeNumber(newFrom, newTo, integerList);
                } finally {
                    lock.unlock();
                }
            };
            future = new FutureTask<>(task);
            new Thread(future).start();
            from = (partRange * i) + 1;
            i++;
        }
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException ex) {
            throw new RuntimeException(ex);
        }
    }
    /**
     * Writing by all threads to different collection and then save to one
     * @param from - start number
     * @param to - end search number
     * @param numberThread - quantity threads
     * @return - list of primes
     */
    public List<Integer> searchPrimeNumberThreadShareCollection(int from, int to, int numberThread) {
        List<Integer> list = new ArrayList<>();
        SearchPrimeNumber primeNumber = new SearchPrimeNumber();
        Thread thread;
        int i = 1;
        while (i < numberThread + 1) {
            int partRange = to / numberThread;
            int newTo = partRange * i;
            int newFrom = from;
            List<Integer> finalList = new ArrayList<>();
            Runnable runnable = () -> finalList.addAll(primeNumber.searchPrimeNumber(newFrom, newTo, new ArrayList<>()));
            from = (partRange * i) + 1;
            thread = new Thread(runnable);
            thread.start();
            i++;
            try {
                thread.join();
                list.addAll(finalList);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
        return list;
    }
}
