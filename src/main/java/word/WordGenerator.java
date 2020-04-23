package word;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class WordGenerator {
    public static void main(String[] args) throws IOException {
        Dictionary dic = new Dictionary();
        Set<String> matched = new HashSet<>();
        String str = "working";
        String prefix = "";
        //Load the dictionary
        List<String> dictionary =
                Files.readAllLines(new File(String.valueOf(dic.getResourceFile("dictionary.txt"))).toPath(), Charset.defaultCharset());

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

        //See if the words returned are valid or not
        matched.forEach(word-> {
            try {
                System.out.println(word + " is a valid word " + dic.isEnglishWord(word));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


}
