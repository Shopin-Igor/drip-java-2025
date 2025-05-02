package Task2;
public class Main { // тут на примере чисел фибоначчи показываю, как просят
    public static void main(String[] args) {
        try (FixedThreadPool pool = FixedThreadPool.create(4)) {
            pool.start();
            for (int i = 0; i < 30; i++) {
                int num = i;
                pool.execute(() -> {
                    long result = fibonacci(num);
                    System.out.printf("fib(%d) = %d (поток: %s)%n",
                            num, result, Thread.currentThread().getName());
                });
            }
        }
    }



    private static long fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
