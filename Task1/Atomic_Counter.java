package Task1;
import java.util.concurrent.atomic.AtomicInteger;

public class Atomic_Counter {
    private final AtomicInteger count = new AtomicInteger(0);
    public void increment() {
        count.getAndIncrement();
    }


    public int getCount() {
        return count.get();
    }
}
