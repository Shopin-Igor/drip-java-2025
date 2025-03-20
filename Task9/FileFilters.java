package Task9;
import java.io.IOException;
import java.nio.file.*;

public class FileFilters {
    public static DirectoryStream.Filter<Path> regularFile = Files::isRegularFile;
    public static DirectoryStream.Filter<Path> readable = Files::isReadable;

    public static DirectoryStream.Filter<Path> largerThan(long size) {
        return path -> {
            try {
                return Files.size(path) > size;
            } catch (IOException e) {
                return false;
            }
        };
    }

    public static DirectoryStream.Filter<Path> magicNumber(int... magicBytes) {
        return path -> {
            try {
                byte[] fileBytes = Files.readAllBytes(path);
                for (int i = 0; i < magicBytes.length; i++) {
                    if (fileBytes[i] != (byte) magicBytes[i]) {
                        return false;
                    }
                }
                return true;
            } catch (IOException e) {
                return false;
            }
        };
    }

    public static DirectoryStream.Filter<Path> globMatches(String glob) {
        return path -> path.getFileSystem().getPathMatcher("glob:" + glob).matches(path.getFileName());
    }

    public static DirectoryStream.Filter<Path> regexContains(String regex) {
        return path -> path.getFileName().toString().matches(regex);
    }
}