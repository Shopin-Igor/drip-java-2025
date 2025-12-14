package Task3;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class DateParser {
    public static Optional<LocalDate> parseDate(String string) {
        if (string == null || string.isEmpty()) {
            return Optional.empty();
        }

        DateTimeFormatter[] formatters = {
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("yyyy-M-d"),
                DateTimeFormatter.ofPattern("M/d/yyyy"),
                DateTimeFormatter.ofPattern("M/d/yy")
        };

        for (DateTimeFormatter formatter : formatters) {
            try {
                return Optional.of(LocalDate.parse(string, formatter));
            } catch (DateTimeParseException e) {
                continue;
            }
        }

        return Optional.empty();
    }
}