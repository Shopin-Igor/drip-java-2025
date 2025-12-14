package Task10;
import java.io.*;
import java.nio.file.*;

public class OutputStreamComposition {
    public static void main(String[] args) {
        Path path = Paths.get("output.txt");
        try (OutputStream fileOutputStream = Files.newOutputStream(path);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(bufferedOutputStream, "UTF-8");
             PrintWriter printWriter = new PrintWriter(outputStreamWriter)) {
            printWriter.println("Programming is learned by writing programs. – Brian Kernighan");
        } catch (IOException e) {
            System.err.println("Произошла ошибка при записи в файл: " + e.getMessage());
            e.printStackTrace();
        }
    }
}