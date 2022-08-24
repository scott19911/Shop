package primeNumber;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchPrimeNumberTest {

    @Test
    public void shouldReturn2_3_5_7WhenInputRange2_10() {
        SearchPrimeNumber search = new SearchPrimeNumber();
        List<Integer> expected = new ArrayList<>(Arrays.asList(2, 3, 5, 7));
        assertEquals(expected, search.searchPrimeNumber(2, 10, new ArrayList<>()));
    }
}