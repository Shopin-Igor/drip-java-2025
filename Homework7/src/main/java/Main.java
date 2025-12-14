import generator.RandomObjectGenerator;
import model.MyClass;
import model.MyRecord;
import proxy.CacheProxy;
import annotations.Cache;
import proxy.FibCalculator;

public class Main {
    public static void main(String[] args) {
        // генератор объектов
        RandomObjectGenerator rog = new RandomObjectGenerator();

        var myClass = rog.nextObject(MyClass.class, "create"); //через фабрику
        System.out.println("MyClass (factory): " + myClass);


        var myRecord = rog.nextObject(MyRecord.class); //через конструктор
        System.out.println("MyRecord (random): " + myRecord);

        // обычная реализация
        FibCalculator original = new FibCalculator() {
            @Override
            @Cache(persist = true)
            public long fib(int number) {
                if (number <= 1) return number;
                return fib(number - 1) + fib(number - 2);
            }
        };

        // кэш-прокси
        FibCalculator proxy = CacheProxy.create(original, FibCalculator.class);

        // вызовы с кэшированием
        System.out.println("fib(5): " + proxy.fib(5));
        System.out.println("fib(6): " + proxy.fib(6));
        System.out.println("fib(5) again (from cache): " + proxy.fib(5));
    }

}
