package Task4;

public class PiBenchmark {
    public static void main(String[] args) throws InterruptedException {
        int[] iterations = {10_000_000, 100_000_000, 1_000_000_000};
        int threads = Runtime.getRuntime().availableProcessors();

        for (long n : iterations) {
            long start = System.nanoTime();
            double piSingle = MonteCarloPiSingleThread.calculatePi(n);
            long singleTime = System.nanoTime() - start;


            start = System.nanoTime();
            double piMulti = MonteCarloPiMultiThread.calculatePi(n, threads);
            long multiTime = System.nanoTime() - start;

            System.out.printf("Итераций: %,d%n", n);
            System.out.printf("Однопоточное время: %.3f сек%n", singleTime / 1e9);
            System.out.printf("Многопоточное время: %.3f сек%n", multiTime / 1e9);
            System.out.printf("Ускорение: x%.2f%n", (double) singleTime / multiTime);
            System.out.printf("Погрешность (single): %.6f%n", Math.abs(piSingle - Math.PI));
            System.out.printf("Погрешность (multi): %.6f%n%n", Math.abs(piMulti - Math.PI));
        }
    }
}