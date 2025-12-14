package Task3;

public class FaultyConnection implements Connection {
    @Override
    public void execute(String command) {
        if (Math.random() < 0.5) {
            throw new ConnectionException("FaultyConnection failed to execute command.");
        }
        System.out.println("Executing command: " + command);
    }

    @Override
    public void close() {
        System.out.println("FaultyConnection closed.");
    }
}