package Task4;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class MonteCarloPiMultiThread {
    public static double calculatePi(long iterations, int threads) throws InterruptedException {
        AtomicLong circleCount = new AtomicLong(0);
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        long perThread = iterations / threads;

        for (int i = 0; i < threads; i++) {
            executor.submit(() -> {
                long localCount = 0;
                ThreadLocalRandom random = ThreadLocalRandom.current();
                for (long j = 0; j < perThread; j++) {
                    double x = random.nextDouble();
                    double y = random.nextDouble();
                    if (x * x + y * y <= 1.0) {
                        localCount++;
                    }
                }
                circleCount.addAndGet(localCount);
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.HOURS);
        return 4.0 * circleCount.get() / iterations;
    }
}