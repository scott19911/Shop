package primeNumber;

import java.util.List;

/**
 * search for prime numbers in the range of numbers
 */
public class SearchPrimeNumber {

    /**
     * Writing all primes to given collection
     *
     * @param from        - start number
     * @param to          - end search number
     * @param integerList - save collection
     * @return - list of primes
     */
    public List<Integer> searchPrimeNumber(int from, int to, List<Integer> integerList) {
        int testNumber = from;
        while (testNumber <= to) {
            boolean isPrime = true;
            for (int i = 2; i <= testNumber / 2; i++) {
                if (testNumber % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                integerList.add(testNumber);
            }
            testNumber++;
        }
        return integerList;
    }

}
