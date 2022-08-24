package repeatingSequence;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Semaphore;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AdditionalThreadTest {

    @Test
    public void throwIllegalArgumentExeption_When_ArrLenght_0() {
        AdditionalThread thread = new AdditionalThread(new byte[0], new Semaphore(1));
        assertThrows(IllegalArgumentException.class, () -> thread.repeatingSequancyLength(new byte[0]));
    }

    @Test
    public void findSequancyLenght_4() {
        byte[] testArr = new byte[]{12, 12, 21, 32, 32, 23, 32, 23, 32, 3, 25, 5, 5, 5, 5, 1};
        AdditionalThread thread = new AdditionalThread(testArr, new Semaphore(1));
        int[] expected = new int[]{4, 11, 14};
        int[] act = thread.repeatingSequancyLength(testArr);
        assertArrayEquals(expected, act);
    }
}