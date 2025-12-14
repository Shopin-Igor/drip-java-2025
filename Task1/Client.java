package Task1;
import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;


public class Client {
    private static final String HOST = "localhost";
    private static final int PORT = 55535;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("Ваня: ");
                String input = scanner.nextLine().trim();
                if (input.equalsIgnoreCase("exit")) break;

                try (Socket socket = new Socket(HOST, PORT);
                     ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                     ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
                    out.writeObject(input);
                    out.flush();
                    String response = (String) in.readObject();
                    System.out.println("Сервер: " + response);
                } catch (ConnectException e) {
                    System.out.println("Сервер перегружен. Ожидайте...");
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}