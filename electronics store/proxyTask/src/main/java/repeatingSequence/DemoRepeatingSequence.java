package repeatingSequence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

/**
 * Creates an additional stream to search for a sequence of bytes in a file and output the information to the console
 */
public class DemoRepeatingSequence {
    /**
     *Synchronizes the main thread with the additional one, allowing only one to work at a time
     */
    public static final Semaphore SEMAPHORE = new Semaphore(1, true);
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter file path");
        byte[] bytesArray = getFile();
        while (true) {
            byte[] finalBytesArray = bytesArray;
            AdditionalThread task = new AdditionalThread(finalBytesArray, SEMAPHORE);
            Thread thread = new Thread(task);
            thread.start();
            while (thread.getState().equals(Thread.State.RUNNABLE)) {
                if (task.getCurentSequancy() > 0) {
                    System.out.println(task.getCurentSequancy());
                }
            }
            System.out.println("Result of search: ");
            for (int i : task.getResult()
            ) {
                System.out.println(i);
            }
            System.out.println("Exit 0/1");
            if (scanner.nextInt() > 0) {
                return;
            }
            System.out.println("Enter file path");
            bytesArray = getFile();
        }
    }

    /**
     * Reads a sequence of bytes from a file
     *
     * @return - bytes sequence
     */
    public static byte[] getFile() {
        try {
            SEMAPHORE.tryAcquire();
            return Files.readAllBytes(Paths.get(scanner.next()));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            SEMAPHORE.release();
        }
    }
}
