package word;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordGeneratorTest {
    private Dictionary dic;
    private WordGenerator wordGenerator;
    List<String> dictionary = new ArrayList<>();
    Set<String> matched = new HashSet<>();

    @Before
    public void setUp() throws IOException {
        dic = new Dictionary();
        wordGenerator = new WordGenerator();
        dictionary =
                Files.readAllLines(new File(String.valueOf(dic.getResourceFile("dictionary.txt"))).toPath(), Charset.defaultCharset());
    }

    @Test
    public void getPossibleWordsTest() throws Exception {
        Assert.assertEquals(3,wordGenerator.getPossibleWords(matched,"abc",dictionary).size());
        Assert.assertTrue(wordGenerator.getPossibleWords(matched,"abc",dictionary).contains("cab"));
    }
}
