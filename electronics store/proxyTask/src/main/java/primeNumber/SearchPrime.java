package primeNumber;

import java.util.List;

public interface SearchPrime {
    /**
     * Writing all primes to given collection
     *
     * @param from        - start number
     * @param to          - end search number
     * @param integerList - save collection
     * @return - list of primes
     */
    List<Integer> searchPrimeNumber(int from, int to, List<Integer> integerList);
}
