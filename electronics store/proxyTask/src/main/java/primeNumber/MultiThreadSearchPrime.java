package primeNumber;

import java.util.List;

public interface MultiThreadSearchPrime {
    /**
     * Writing by all threads to one collection
     *
     * @param from         - start number
     * @param to           - end search number
     * @param numberThread - quantity threads
     * @return - list of primes number
     */
    List<Integer> searchPrimeInternalCollection(int from, int to, int numberThread);

    /**
     * Writing by all threads to different collection and then save to one
     *
     * @param from         - start number
     * @param to           - end search number
     * @param numberThread - quantity threads
     * @return - list of primes
     */
    List<Integer> searchPrimeShareCollection(int from, int to, int numberThread);
}
