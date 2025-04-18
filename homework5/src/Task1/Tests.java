package Task1;
import org.junit.Test;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;

public class Tests {
    private static final int THREADS = 1000;
    private static final int INCREMENTS_PER_THREAD = 1000;
    private static final int EXPECTED_TOTAL = THREADS * INCREMENTS_PER_THREAD;

    @Test
    public void testSynchronizedCounter() throws InterruptedException {
        Synchronized_Counter counter = new Synchronized_Counter();
        runConcurrentTest(counter);
        assertEquals(EXPECTED_TOTAL, counter.getCount());
    }



    @Test
    public void testAtomicCounter() throws InterruptedException {
        Atomic_Counter counter = new Atomic_Counter();
        runConcurrentTest(counter);
        assertEquals(EXPECTED_TOTAL, counter.getCount());
    }



    @Test
    public void testReentrantLockCounter() throws InterruptedException {
        ReentrantLock_Counter counter = new ReentrantLock_Counter();
        runConcurrentTest(counter);
        assertEquals(EXPECTED_TOTAL, counter.getCount());
    }



    @Test
    public void testSemaphoreCounter() throws InterruptedException {
        Semaphore_Counter counter = new Semaphore_Counter();
        runConcurrentTest(counter);
        assertEquals(EXPECTED_TOTAL, counter.getCount());
    }



    private void runConcurrentTest(RunnableCounter counter) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(THREADS);
        for (int i = 0; i < THREADS; i++) {
            executor.submit(() -> {
                for (int j = 0; j < INCREMENTS_PER_THREAD; j++) {
                    counter.increment();
                }
            });
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
    }



    private interface RunnableCounter {
        void increment();
        int getCount();
    }
}