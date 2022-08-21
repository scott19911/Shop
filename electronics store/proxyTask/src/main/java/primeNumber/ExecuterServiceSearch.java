package primeNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * search for primes in a multi-threaded environment based on executors
 */
public class ExecuterServiceSearch {
    /**
     * Writing by all threads to one collection
     *
     * @param from         - start number
     * @param to           - end search number
     * @param numberThread - quantity threads
     * @return - list of primes number
     */
    public List<Integer> searchPrimeNumberIternalCollection(int from, int to, int numberThread) {
        List<Integer> integerList = new ArrayList<>();
        SearchPrimeNumber primeNumber = new SearchPrimeNumber();
        Future<List<Integer>> future = null;
        Lock lock = new ReentrantLock();
        ExecutorService service = Executors.newFixedThreadPool(numberThread);
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
            future = service.submit(task);
            from = (partRange * i) + 1;
            i++;
        }
        try {
            service.shutdown();
            return future.get();
        } catch (InterruptedException | ExecutionException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Writing by all threads to different collection and then save to one
     *
     * @param from         - start number
     * @param to           - end search number
     * @param numberThread - quantity threads
     * @return - list of primes
     */
    public List<Integer> searchPrimeNumberShareCollection(int from, int to, int numberThread) {
        List<Integer> integerList = new ArrayList<>();
        SearchPrimeNumber primeNumber = new SearchPrimeNumber();
        Future<List<Integer>> future;
        ExecutorService service = Executors.newFixedThreadPool(numberThread);
        int i = 1;
        while (i < numberThread + 1) {
            int partRange = to / numberThread;
            int newTo = partRange * i;
            int newFrom = from;
            Callable<List<Integer>> task = () -> primeNumber.searchPrimeNumber(newFrom, newTo, new ArrayList<>());
            future = service.submit(task);
            from = (partRange * i) + 1;
            i++;
            try {
                integerList.addAll(future.get());
            } catch (InterruptedException | ExecutionException ex) {
                throw new RuntimeException(ex);
            }
        }
        service.shutdown();
        return integerList;


    }
}
