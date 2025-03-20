package Task7;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DiskMap implements Map<String, String> {
    private final File file;
    private final Map<String, String> map;

    public DiskMap(String filename) throws IOException {
        this.file = new File(filename);
        this.map = new HashMap<>();
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("=", 2);
                    if (parts.length == 2) {
                        map.put(parts[0], parts[1]);
                    }
                }
            }
        }
    }

    @Override
    public String put(String key, String value) {
        String oldValue = map.put(key, value);
        saveToFile();
        return oldValue;
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return map.get(key);
    }

    @Override
    public String remove(Object key) {
        String value = map.remove(key);
        saveToFile();
        return value;
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> m) {
        map.putAll(m);
        saveToFile();
    }

    @Override
    public void clear() {
        map.clear();
        saveToFile();
    }

    @Override
    public java.util.Set<String> keySet() {
        return map.keySet();
    }

    @Override
    public java.util.Collection<String> values() {
        return map.values();
    }

    @Override
    public java.util.Set<Entry<String, String>> entrySet() {
        return map.entrySet();
    }
}