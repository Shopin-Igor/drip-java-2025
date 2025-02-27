import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class WordFrequency {
    public static <T> Map<T, Integer> freqDict(List<T> list) {
        Map<T, Integer> map = new HashMap<>();

        for (T item : list) {
            map.put(item, map.getOrDefault(item, 0) + 1);
        }

        return map;
    }
}
