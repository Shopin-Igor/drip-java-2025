package Task2;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class FridayTheThirteenth {
    public static List<String> findFridayTheThirteenth(int year) {
        List<String> fridays = new ArrayList<>();
        for (int month = 1; month <= 12; month++) {
            LocalDate date = LocalDate.of(year, month, 13);
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                fridays.add(date.toString());
            }
        }
        return fridays;
    }

    public static LocalDate findNextFridayTheThirteenth(LocalDate date) {
        LocalDate nextDate = date.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        while (nextDate.getDayOfMonth() != 13) {
            nextDate = nextDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        }
        return nextDate;
    }
}
