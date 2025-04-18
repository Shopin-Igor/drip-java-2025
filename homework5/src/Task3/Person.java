package Task3;

import java.util.List;

public record Person(int id, String name, String address, String phoneNumber) {}

interface PersonDatabase {
    void add(Person person);
    void delete(int id);

    List<Person> findByName(String name);
    List<Person> findByAddress(String address);
    List<Person> findByPhone(String phone);
}