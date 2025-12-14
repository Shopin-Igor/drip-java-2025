package Task1;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ComputerClubAnalytics {
    public static String calculateAverageSessionTime(List<String> sessions) {
        if (sessions == null || sessions.isEmpty()) {
            return "0ч 0м";
        }

        Duration totalDuration = Duration.ZERO;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");

        for (String session : sessions) {
            String[] parts = session.split(" - ");
            if (parts.length != 2) continue;

            LocalDateTime start = LocalDateTime.parse(parts[0], formatter);
            LocalDateTime end = LocalDateTime.parse(parts[1], formatter);
            totalDuration = totalDuration.plus(Duration.between(start, end));
        }

        Duration averageDuration = totalDuration.dividedBy(sessions.size());
        long hours = averageDuration.toHours();
        long minutes = averageDuration.toMinutes() % 60;
        return hours + "ч " + minutes + "м";
    }
}