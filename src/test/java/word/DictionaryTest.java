package word;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class DictionaryTest {

    private Dictionary dic;

    @Before
    public void setUp() throws IOException {
        dic = new Dictionary();
    }

    @Test
    public void isEnglishWordTestWhenParamIsValid() throws IOException {
        Assert.assertTrue(dic.isEnglishWord("word"));
    }

    @Test
    public void isEnglishWordTestWhenParamIsInvalid() throws IOException {
        Assert.assertFalse(dic.isEnglishWord("word1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getResourceFileTestException() {
        dic.getResourceFile("dic1.txt");
    }
}
