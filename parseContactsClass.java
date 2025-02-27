import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

public class parseContactsClass {
    public static List<String> parseContacts(List<String> contacts, String sortOrder) {
        if (contacts == null || contacts.isEmpty()) {
            return List.of();
        }

        Comparator<String> comparator = (first_person, second_person) -> {
            String[] first_person_name_and_surname = first_person.split(" ");
            String[] second_person_name_and_surname = second_person.split(" ");
            String first_lastname = (first_person_name_and_surname.length > 1) ? first_person_name_and_surname[1] : first_person_name_and_surname[0];
            String second_lastname = (second_person_name_and_surname.length > 1) ? second_person_name_and_surname[1] : second_person_name_and_surname[0];

            return first_lastname.compareTo(second_lastname);
        };

        if (sortOrder.equals("DESC")) {
            comparator = comparator.reversed();
        }

        return contacts.stream().sorted(comparator).collect(Collectors.toList());
    }

}
