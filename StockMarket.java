import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Objects;

class Stock {
    private String name;
    private double price;

    public Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) { // раньше сравнивались ссылки
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Double.compare(stock.price, price) == 0 &&
                Objects.equals(name, stock.name);
    }


    @Override
    public String toString() {
        return "Stock [name=" + name + ", price=" + price + "]";
    }
}


interface StockMarket {
    void add(Stock stock);
    void remove(Stock stock);
    Stock mostValuableStock();
}

class StockMarketImpl implements StockMarket {
    private PriorityQueue<Stock> stockQueue;
    public StockMarketImpl() {
        stockQueue = new PriorityQueue<>(Comparator.comparingDouble(Stock::getPrice).reversed());
    }

    @Override
    public void add(Stock stock) {
        stockQueue.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        stockQueue.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return stockQueue.peek();
    }
}