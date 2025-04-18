package Task3_5;

import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CachingPersonDatabaseWithReadWriteLock implements PersonDatabase {
    private final Map<Integer, Person> idIndex = new HashMap<>();
    private final Map<String, List<Person>> nameIndex = new HashMap<>();
    private final Map<String, List<Person>> addressIndex = new HashMap<>();
    private final Map<String, List<Person>> phoneIndex = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void add(Person person) {
        lock.writeLock().lock();
        try {
            if (idIndex.containsKey(person.id())) return;
            idIndex.put(person.id(), person);
            addToIndex(nameIndex, person.name(), person);
            addToIndex(addressIndex, person.address(), person);
            addToIndex(phoneIndex, person.phoneNumber(), person);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        lock.writeLock().lock();
        try {
            Person person = idIndex.remove(id);
            if (person == null) return;

            removeFromIndex(nameIndex, person.name(), person);
            removeFromIndex(addressIndex, person.address(), person);
            removeFromIndex(phoneIndex, person.phoneNumber(), person);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        lock.readLock().lock();
        try {
            return new ArrayList<>(getFromIndex(nameIndex, name));
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        lock.readLock().lock();
        try {
            return new ArrayList<>(getFromIndex(addressIndex, address));
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        lock.readLock().lock();
        try {
            return new ArrayList<>(getFromIndex(phoneIndex, phone));
        } finally {
            lock.readLock().unlock();
        }
    }



    private void addToIndex(Map<String, List<Person>> index, String key, Person person) {
        index.computeIfAbsent(key, k -> new ArrayList<>()).add(person);
    }

    private void removeFromIndex(Map<String, List<Person>> index, String key, Person person) {
        List<Person> list = index.get(key);
        if (list != null) {
            list.remove(person);
            if (list.isEmpty()) index.remove(key);
        }
    }

    private List<Person> getFromIndex(Map<String, List<Person>> index, String key) {
        return index.getOrDefault(key, Collections.emptyList());
    }

}