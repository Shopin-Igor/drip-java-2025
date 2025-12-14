package Task4;
import java.util.concurrent.ThreadLocalRandom;

public class MonteCarloPiSingleThread {
    public static double calculatePi(long iterations) {
        long circleCount = 0;
        for (long i = 0; i < iterations; i++) {
            double x = ThreadLocalRandom.current().nextDouble();
            double y = ThreadLocalRandom.current().nextDouble();
            if (x * x + y * y <= 1.0) {
                circleCount++;
            }
        }
        return 4.0 * circleCount / iterations;
    }
}