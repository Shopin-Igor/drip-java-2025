package Task3;

public class DefaultConnectionManager implements ConnectionManager {
    @Override
    public Connection getConnection() {
        if (Math.random() < 0.5) {
            return new StableConnection();
        } else {
            return new FaultyConnection();
        }
    }
}