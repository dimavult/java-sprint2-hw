import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReader {
    public static String readFileContentsOrNull(String path) // Теперь статический
    {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, " + path + " не находится в нужной директории.");
            return null;
        }
    }
}
