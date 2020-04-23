package word;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;

public class Dictionary {
    public boolean isEnglishWord(String word) throws IOException {
        //Connect to the mocked dictionary
        Set<String> dictionary =
                new HashSet<>(Files.readAllLines(new File(
                                String.valueOf(getResourceFile("dictionary.txt"))).toPath(),
                        Charset.defaultCharset()));
        return dictionary.contains(word);

    }

    public File getResourceFile(String fileName) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }
    }
}
