package Task3;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class CachingPersonDatabaseTest {
    @Test
    public void testAddAndFind() {
        PersonDatabase db = new CachingPersonDatabase();
        Person person = new Person(1, "Alice", "Paris", "12345");
        db.add(person);
        assertEquals(List.of(person), db.findByName("Alice"));
        assertEquals(List.of(person), db.findByAddress("Paris"));
        assertEquals(List.of(person), db.findByPhone("12345"));
    }

    @Test
    public void testDelete() {
        PersonDatabase db = new CachingPersonDatabase();
        Person person = new Person(1, "Bob", "London", "67890");
        db.add(person);
        db.delete(1);
        assertTrue(db.findByName("Bob").isEmpty());
        assertTrue(db.findByAddress("London").isEmpty());
        assertTrue(db.findByPhone("67890").isEmpty());
    }
}