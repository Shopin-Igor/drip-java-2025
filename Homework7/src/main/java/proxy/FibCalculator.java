package proxy;

import annotations.Cache;

public interface FibCalculator {
    @Cache(persist = true)
    long fib(int number);
}
