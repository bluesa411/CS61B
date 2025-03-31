import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import browser.NgordnetQueryType;
import main.AutograderBuddy;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class TestOneWordKNot0Hyponyms {
    public static final String WORDS_FILE = "data/ngrams/top_14377_words.csv";
    public static final String TOTAL_COUNTS_FILE = "data/ngrams/total_counts.csv";
    public static final String SMALL_SYNSET_FILE = "data/wordnet/synsets.txt";
    public static final String SMALL_HYPONYM_FILE = "data/wordnet/hyponyms.txt";
    public static final String MY_WORDS_FILE = "data/ngrams/my_words.csv";
    public static final String MY_COUNTS_FILE = "data/ngrams/my_count.csv";
    public static final String MY_SYNSET_FILE = "data/wordnet/mysynsets.txt";
    public static final String MY_HYPONYM_FILE = "data/wordnet/myhyponyms.txt";


    // TODO: Add more unit tests (including edge case tests) here.
    @Test
    public void myTestNot0K(){
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                WORDS_FILE, TOTAL_COUNTS_FILE, SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        List<String> words = List.of("food", "cake");
        NgordnetQuery nq = new NgordnetQuery(words, 1950, 1990, 5, NgordnetQueryType.HYPONYMS);
        String actual = studentHandler.handle(nq);
        String expected = "[cake, cookie, kiss, snap, wafer]";
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    public void test0Count(){
        NgordnetQueryHandler handler = AutograderBuddy.getHyponymsHandler(MY_WORDS_FILE, MY_COUNTS_FILE,
                MY_SYNSET_FILE, MY_HYPONYM_FILE);
        List<String> words = List.of("tiandou", "youyi");
        NgordnetQuery nq = new NgordnetQuery(words, 1950, 1990, 5, NgordnetQueryType.HYPONYMS);
        String actual = handler.handle(nq);
        String expected = "[]";
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    public void testNotGreaterK(){
        NgordnetQueryHandler handler = AutograderBuddy.getHyponymsHandler(MY_WORDS_FILE, MY_COUNTS_FILE,
                MY_SYNSET_FILE, MY_HYPONYM_FILE);
        List<String> words = List.of("wife");
        NgordnetQuery nq = new NgordnetQuery(words, 2002, 2025, 1, NgordnetQueryType.HYPONYMS);
        String actual = handler.handle(nq);
        String expected = "[tiandou]";
        assertThat(actual).isEqualTo(expected);
    }


}
