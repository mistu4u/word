package word;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordGenerator {
   private static final Logger log = LoggerFactory.getLogger(WordGenerator.class);

    public static void main(String[] args) throws IOException {

        Dictionary dic = new Dictionary();
        WordGenerator wordGenerator = new WordGenerator();
        Set<String> matched = new HashSet<>();
        String str = "abc";
        //Load the dictionary
        List<String> dictionary =
                Files.readAllLines(new File(String.valueOf(dic.getResourceFile("dictionary.txt"))).toPath(), Charset.defaultCharset());
        matched = wordGenerator.getPossibleWords(matched, str, dictionary);

        //See if the words returned are valid or not
        matched.forEach(word -> {
            try {
                log.info("{} is a valid word {}", word, dic.isEnglishWord(word));
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        });
    }

    public Set<String> getPossibleWords(Set<String> matched, String str, List<String> dictionary) {
        //Prepare the array
        int[] avail = new int[26];
        for (char ch : str.toCharArray()) {
            int index = ch - 'a';
            avail[index]++;
        }

        //Traverse the dictionary
        dictionary.forEach(
                word -> {
                    int[] count = new int[26];
                    boolean isMatched = true;
                    for (char ch : word.toCharArray()) {
                        int index = ch - 'a';
                        count[index]++;
                        if (count[index] != avail[index]) {
                            isMatched = false;
                            break;
                        }
                    }
                    if (isMatched) {
                        matched.add(word);
                    }
                }

        );
        return matched;
    }

}
