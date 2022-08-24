package primeNumber;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MultiThreadSearchTest {
    @Test
    public void shouldReturn2_3_5_7WhenInputRange2_10_forSearchPrimeNumberThreadIternalCollection() {
        MultiThreadSearch search = new MultiThreadSearch();
        List<Integer> expected = new ArrayList<>(Arrays.asList(2, 3, 5, 7));
        List<Integer> act = search.searchPrimeNumberThreadIternalCollection(2, 10, 4);
        for (Integer number : act) {
            assertTrue(expected.contains(number));
        }
        assertEquals(expected.size(), act.size());
    }

    @Test
    public void shouldReturn2_3_5_7WhenInputRange2_10_forSearchPrimeNumberThreadShareCollection() {
        MultiThreadSearch search = new MultiThreadSearch();
        List<Integer> expected = new ArrayList<>(Arrays.asList(2, 3, 5, 7));
        List<Integer> act = search.searchPrimeNumberThreadShareCollection(2, 10, 4);
        for (Integer number : act) {
            assertTrue(expected.contains(number));
        }
        assertEquals(expected.size(), act.size());
    }
}