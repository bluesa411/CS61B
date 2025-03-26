import main.WordNet;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;


public class WordNetTest {
    public static final String SMALL_SYNSET_FILE = "data/wordnet/synsets16.txt";
    public static final String SMALL_HYPONYM_FILE = "data/wordnet/hyponyms16.txt";

    @Test
    public void testWordNet(){
        WordNet wordNet = new WordNet(SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
    }

    @Test
    public void testOneNodeOneWordHyponyms(){
        WordNet wordNet = new WordNet(SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        assertThat(wordNet.oneNodeHyponyms(2)).containsExactly("change", "alteration", "modification",
                "transition", "increase", "leap", "jump", "saltation");
    }
    @Test
    public void testOneWordHyponyms(){
        WordNet wordNet = new WordNet(SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        assertThat(wordNet.oneWordHyponyms("change")).containsExactly("change", "alteration", "modification",
                "transition", "increase", "leap", "jump", "saltation", "demotion", "variation");
    }
    @Test
    public void testListHyponyms(){
        WordNet wordNet = new WordNet(SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        List<String> wordList = new ArrayList<>();
        wordList.add("change");
        wordList.add("occurrence");
        assertThat(wordNet.hyponyms(wordList)).containsExactly("alteration", "change", "increase", "jump", "leap",
                "modification", "saltation", "transition").inOrder();
    }
}
