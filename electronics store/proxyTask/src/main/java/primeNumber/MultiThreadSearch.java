package primeNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * search for primes in a multi-threaded
 */
public class MultiThreadSearch implements MultiThreadSearchPrime {
    @Override
    public List<Integer> searchPrimeInternalCollection(int from, int to, int numberThread) {
        CopyOnWriteArrayList<Integer> integerList = new CopyOnWriteArrayList<>();
        SearchPrimeImpl primeNumber = new SearchPrimeImpl();
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

   @Override
    public List<Integer> searchPrimeShareCollection(int from, int to, int numberThread) {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        SearchPrimeImpl primeNumber = new SearchPrimeImpl();
        Thread thread = null;
        int i = 1;
        while (i < numberThread + 1) {
            int partRange = to / numberThread;
            int newTo = partRange * i;
            int newFrom = from;
            Runnable runnable = () -> {
                List<Integer> finalList = new ArrayList<>();
                finalList.addAll(primeNumber.searchPrimeNumber(newFrom, newTo, new ArrayList<>()));
                list.addAll(finalList);
            };
            from = (partRange * i) + 1;
            thread = new Thread(runnable);
            thread.start();
            i++;
        }
        try {
            if (thread != null) {
                thread.join();
            }
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }
}
