package Task8;
import java.io.IOException;
import java.nio.file.*;

public class FileCloner {
    public static void cloneFile(Path path) throws IOException {
        if (!Files.exists(path)) {
            throw new IOException("Файл не существует: " + path);
        }

        String fileName = path.getFileName().toString();
        String baseName = fileName.substring(0, fileName.lastIndexOf('.'));
        String extension = fileName.substring(fileName.lastIndexOf('.'));
        int copyNumber = 1;
        Path newPath;

        do {
            String newFileName = baseName + " – копия" + (copyNumber > 1 ? " (" + copyNumber + ")" : "") + extension;
            newPath = path.getParent().resolve(newFileName);
            copyNumber++;
        } while (Files.exists(newPath));

        Files.copy(path, newPath);
    }

    public static void main(String[] args) {
        try {
            Path path = Paths.get("Tinkoff Bank Biggest Secret.txt");
            cloneFile(path);
            System.out.println("Файл успешно скопирован.");
        } catch (IOException e) {
            System.err.println("Ошибка при копировании файла: " + e.getMessage());
        }
    }
}