import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Task1 {
    public void processFile(String fileName) {
        File file = new File(fileName);

        if (!file.exists()) {
            throw new RuntimeException("Файл " + fileName + " не найден");
        }

        try (BufferedReader buffer = new BufferedReader(new FileReader(fileName))) {
            String line = buffer.readLine();

            while (line != null){

                if (line.matches("\\(\\d{3}\\)\\s\\d{3}-\\d{4}") || line.matches("\\d{3}-\\d{3}-\\d{4}")) {
                    System.out.println(line);
                }
                line = buffer.readLine();
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}