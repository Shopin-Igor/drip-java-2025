package Task3;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    void tryExecute(String command) {
        ConnectionException lastException = null;

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try (Connection connection = manager.getConnection()) {
                connection.execute(command);
                return; // Успешное выполнение команды
            } catch (ConnectionException e) {
                lastException = e;
                System.out.println("Attempt " + attempt + " failed: " + e.getMessage());
            } catch (Exception e) {
                lastException = new ConnectionException("Unexpected error", e);
                System.out.println("Attempt " + attempt + " failed: " + e.getMessage());
            }
        }

        if (lastException != null) {
            throw new ConnectionException("Failed to execute command after " + maxAttempts + " attempts", lastException);
        }
    }
}