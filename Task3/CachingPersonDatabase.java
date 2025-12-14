package Task3;

import java.util.*;

public class CachingPersonDatabase implements PersonDatabase {
    private final Map<Integer, Person> idIndex = new HashMap<>();
    private final Map<String, List<Person>> nameIndex = new HashMap<>();
    private final Map<String, List<Person>> addressIndex = new HashMap<>();
    private final Map<String, List<Person>> phoneIndex = new HashMap<>();

    @Override
    public synchronized void add(Person person) {
        if (idIndex.containsKey(person.id())) {
            return;
        }
        idIndex.put(person.id(), person);
        addToIndex(nameIndex, person.name(), person);
        addToIndex(addressIndex, person.address(), person);
        addToIndex(phoneIndex, person.phoneNumber(), person);
    }

    @Override
    public synchronized void delete(int id) {
        Person person = idIndex.remove(id);
        if (person == null) {
            return;
        }
        removeFromIndex(nameIndex, person.name(), person);
        removeFromIndex(addressIndex, person.address(), person);
        removeFromIndex(phoneIndex, person.phoneNumber(), person);
    }

    @Override
    public List<Person> findByName(String name) {
        return getFromIndex(nameIndex, name);
    }

    @Override
    public List<Person> findByAddress(String address) {
        return getFromIndex(addressIndex, address);
    }

    @Override
    public List<Person> findByPhone(String phone) {
        return getFromIndex(phoneIndex, phone);
    }
    private synchronized void addToIndex(Map<String, List<Person>> index, String key, Person person) {
        index.computeIfAbsent(key, k -> new ArrayList<>()).add(person);
    }

    private synchronized void removeFromIndex(Map<String, List<Person>> index, String key, Person person) {
        List<Person> list = index.get(key);
        if (list != null) {
            list.remove(person);
            if (list.isEmpty()) {
                index.remove(key);
            }
        }
    }

    private synchronized List<Person> getFromIndex(Map<String, List<Person>> index, String key) {
        List<Person> result = index.getOrDefault(key, Collections.emptyList());
        return new ArrayList<>(result);
    }
}