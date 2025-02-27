import java.util.ArrayList;
import java.util.List;

public class ClusteringBrackets {
    public static List<String> clusterize(String input) {
        List<String> clusters = new ArrayList<>();
        int balance = 0;
        StringBuilder cluster = new StringBuilder();

        for (char ch : input.toCharArray()) {
            cluster.append(ch);
            if (ch == '(') {
                balance++;
            } else if (ch == ')') {
                balance--;
            }
            if (balance == 0) {
                clusters.add(cluster.toString());
                cluster.setLength(0); //= new StringBuilder();
            }
        }
        return clusters;
    }
}
