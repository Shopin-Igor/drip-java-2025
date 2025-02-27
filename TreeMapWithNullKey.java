import java.util.Comparator;
import java.util.TreeMap;
import static org.assertj.core.api.Assertions.assertThat;

public class TreeMapWithNullKey {

    // Кастомный метод add, который вызывает put
    public static <K, V> void add(TreeMap<K, V> tree, K key, V value) {
        tree.put(key, value);
    }

    public static void main(String[] args) {
        Comparator<String> nullHandlingComparator = Comparator.nullsFirst(Comparator.naturalOrder());
        TreeMap<String, String> tree = new TreeMap<>(nullHandlingComparator);
        add(tree, null, "test");

        assertThat(tree.containsKey(null)).isTrue();
    }
}