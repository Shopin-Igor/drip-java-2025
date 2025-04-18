package Task2;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FactorialCalculator {

    public static BigInteger computeFactorial(int n, int numThreads) throws InterruptedException, ExecutionException {
        if (n < 0) {
            throw new IllegalArgumentException("n must be non-negative");
        }
        if (n == 0 || n == 1) {
            return BigInteger.ONE;
        }

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        List<Future<BigInteger>> futures = new ArrayList<>();

        int chunkSize = n / numThreads;
        int start = 1;
        int end;

        for (int i = 0; i < numThreads; i++) {
            end = (i == numThreads - 1) ? n : start + chunkSize - 1;
            futures.add(executor.submit(new FactorialTask(start, end)));
            start = end + 1;
        }
        BigInteger result = BigInteger.ONE;
        for (Future<BigInteger> future : futures) {
            result = result.multiply(future.get());
        }

        executor.shutdown();
        return result;
    }

    private static class FactorialTask implements Callable<BigInteger> {
        private final int start;
        private final int end;

        FactorialTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public BigInteger call() {
            BigInteger product = BigInteger.ONE;
            for (int i = start; i <= end; i++) {
                product = product.multiply(BigInteger.valueOf(i));
            }
            return product;
        }
    }
}