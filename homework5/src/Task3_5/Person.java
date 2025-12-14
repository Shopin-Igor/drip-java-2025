package Task3_5;
import java.util.List;

public record Person(int id, String name, String address, String phoneNumber) {}

interface PersonDatabase {
    void add(Task3_5.Person person);
    void delete(int id);

    List<Task3_5.Person> findByName(String name);
    List<Task3_5.Person> findByAddress(String address);
    List<Task3_5.Person> findByPhone(String phone);
}