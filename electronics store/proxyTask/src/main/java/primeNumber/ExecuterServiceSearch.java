package primeNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * search for primes in a multi-threaded environment based on executors
 */
public class ExecuterServiceSearch implements MultiThreadSearchPrime {
    @Override
    public List<Integer> searchPrimeInternalCollection(int from, int to, int numberThread) {
        CopyOnWriteArrayList<Integer> integerList = new CopyOnWriteArrayList<>();
        SearchPrimeImpl primeNumber = new SearchPrimeImpl();
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

    @Override
    public List<Integer> searchPrimeShareCollection(int from, int to, int numberThread) {
        CopyOnWriteArrayList<Integer> integerList = new CopyOnWriteArrayList<>();
        SearchPrimeImpl primeNumber = new SearchPrimeImpl();
        List<Future<List<Integer>>> listFuture = new ArrayList<>();
        ExecutorService service = Executors.newFixedThreadPool(numberThread);
        int i = 1;
        while (i < numberThread + 1) {
            int partRange = to / numberThread;
            int newTo = partRange * i;
            int newFrom = from;
            Callable<List<Integer>> task = () -> {
                List<Integer> finalList = new ArrayList<>();
                finalList.addAll(primeNumber.searchPrimeNumber(newFrom, newTo, new ArrayList<>()));
                return finalList;
            };
            listFuture.add(service.submit(task));
            from = (partRange * i) + 1;
            i++;
        }
        for (Future<List<Integer>> future : listFuture) {
            try {
                integerList.addAll(future.get());
            } catch (InterruptedException | ExecutionException ex) {
                ex.printStackTrace();
            }
        }
        service.shutdown();
        return integerList;


    }
}
