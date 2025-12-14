package Task1;
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT = 55535;
    private static final int MAX_CONNECTIONS = 3;
    private static final HashMap<String, String> quotes = new HashMap<>();

    static {
        quotes.put("ошибка", "Не совершает ошибки, тот кто ничего не делает, то есть ты)");
        quotes.put("ошибки", "Не совершает ошибки, тот кто ничего не делает, то есть ты)");
        quotes.put("работай", "Сам работай");
        quotes.put("личности", "Не переходи на личности там, где их нет");
        quotes.put("оскорбления", "Если твои противники перешли на личные оскорбления, будь уверена – твоя победа не за горами");
        quotes.put("глупый", "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма..");
        quotes.put("интеллект", "Чем ниже интеллект, тем громче оскорбления");
    }

    public static void main(String[] args) throws IOException {
        ExecutorService pool = Executors.newFixedThreadPool(MAX_CONNECTIONS);
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен на порту " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                pool.execute(new ClientHandler(clientSocket));
            }
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try (ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                 ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {
                String keyword = (String) in.readObject();
                String response = quotes.getOrDefault(keyword, "Цитата не найдена");
                out.writeObject(response);
                out.flush();

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}