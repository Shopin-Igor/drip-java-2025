package Task2;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;


class FixedThreadPool implements ThreadPool {
    private final Thread[] threads;
    private final Queue<Runnable> taskQueue = new ArrayDeque<>();
    private final AtomicBoolean isRunning = new AtomicBoolean(true);

    private FixedThreadPool(int numThreads) {
        this.threads = new Thread[numThreads];
    }

    public static FixedThreadPool create(int numThreads) {
        return new FixedThreadPool(numThreads);
    }

    @Override
    public void start() {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                while (isRunning.get() || !taskQueue.isEmpty()) {
                    Runnable task;
                    synchronized (taskQueue) {
                        while (taskQueue.isEmpty() && isRunning.get()) {
                            try {
                                taskQueue.wait();
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                                return;
                            }
                        }
                        task = taskQueue.poll();
                    }
                    if (task != null) {
                        task.run();
                    }
                }
            });
            threads[i].start();
        }
    }


    @Override
    public void execute(Runnable runnable) {
        synchronized (taskQueue) {
            if (!isRunning.get()) {
                throw new IllegalStateException("Пул остановлен");
            }
            taskQueue.offer(runnable);
            taskQueue.notify();
        }
    }


    @Override
    public void close() {
        isRunning.set(false);
        synchronized (taskQueue) {
            taskQueue.notifyAll();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}