package Task1;
import java.util.concurrent.Semaphore;

public class Semaphore_Counter {
    private int count = 0;
    private final Semaphore semaphore = new Semaphore(1); // Используем стандартный Semaphore

    public void increment() {
        try {
            semaphore.acquire(); // Метод acquire() из java.util.concurrent.Semaphore
            count++;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release(); // Метод release() из java.util.concurrent.Semaphore
        }
    }

    public int getCount() {
        return count;
    }
