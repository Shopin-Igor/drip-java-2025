import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BackwardIteratorImpl<T> implements Iterator<T> {
    private final List<T> list;
    private int position;

    public BackwardIteratorImpl(List<T> list) {
        this.list = list;
        this.position = list.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return (position >= 0);
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements");
        }
        return list.get(position--);
    }
}