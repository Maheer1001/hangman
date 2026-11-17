import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileWordProvider implements WordProvider {
    private final String filename;

    public FileWordProvider(String filename) {
        this.filename = filename;
    }

    @Override
    public List<String> getWords(String difficulty) {
        List<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2 && parts[1].equalsIgnoreCase(difficulty)) {
                    words.add(parts[0].toLowerCase());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return words;
    }
}
