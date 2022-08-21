package repeatingSequence;

import java.util.concurrent.Semaphore;

/**
 * Searches for the longest byte sequence, and its start and end index
 */
public class AdditionalThread implements Runnable {
    byte[] finalBytesArray;
    public static final int SEQUANCY_LENGTH = 0;
    public static final int SEQUANCY_FIRST_INDEX = 1;
    public static final int SEQUANCY_LAST_INDEX = 2;
    int[] result = new int[3];
    static int curentSequancy;
    public Semaphore semaphore;

    public AdditionalThread(byte[] finalBytesArray, Semaphore semaphore) {
        this.semaphore = semaphore;
        this.finalBytesArray = finalBytesArray;
    }

    public int getCurentSequancy() {
        return curentSequancy;
    }

    public int[] getResult() {
        return result;
    }

    public int[] repeatingSequancyLength(byte[] bytesArray) {
        curentSequancy = 1;
        int saveResult = 1;
        int[] resultArr = new int[3];
        for (int i = 1; i < bytesArray.length; i++) {
            if (bytesArray[i] == bytesArray[i - 1]) {
                curentSequancy++;
            } else {
                if (curentSequancy >= saveResult) {
                    resultArr[SEQUANCY_LAST_INDEX] = i - 1;
                    resultArr[SEQUANCY_FIRST_INDEX] = i - curentSequancy;
                    saveResult = curentSequancy;
                }
                curentSequancy = 1;
            }
            if (i == bytesArray.length - 1 && curentSequancy > saveResult) {
                resultArr[SEQUANCY_LAST_INDEX] = i;
                resultArr[SEQUANCY_FIRST_INDEX] = i - curentSequancy + 1;
            }
        }
        if (saveResult > curentSequancy) {
            resultArr[SEQUANCY_LENGTH] = saveResult;
        } else {
            resultArr[SEQUANCY_LENGTH] = curentSequancy;
        }
        return resultArr;
    }

    /**
     * Performs a sequence search on an available semaphore
     * And writes data to the result
     */
    @Override
    public void run() {
        try {
            if (semaphore.tryAcquire()) {
                result = repeatingSequancyLength(finalBytesArray);
            }
        } finally {
            semaphore.release();
        }
    }
}
