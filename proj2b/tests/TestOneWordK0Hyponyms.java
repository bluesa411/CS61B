import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import browser.NgordnetQueryType;
import org.junit.jupiter.api.Test;
import main.AutograderBuddy;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/** Tests the most basic case for Hyponyms where the list of words is one word long, and k = 0.*/
public class TestOneWordK0Hyponyms {
    // this case doesn't use the NGrams dataset at all, so the choice of files is irrelevant
    public static final String WORDS_FILE = "data/ngrams/very_short.csv";
    public static final String TOTAL_COUNTS_FILE = "data/ngrams/total_counts.csv";
    public static final String SMALL_SYNSET_FILE = "data/wordnet/synsets16.txt";
    public static final String SMALL_HYPONYM_FILE = "data/wordnet/hyponyms16.txt";
    private static final String HYPONYMS_FILE_SUBSET = "data/wordnet/hyponyms1000-subgraph.txt";
    private static final String SYNSETS_FILE_SUBSET = "data/wordnet/synsets1000-subgraph.txt";
    @Test
    public void testActK0() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                WORDS_FILE, TOTAL_COUNTS_FILE, SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        List<String> words = List.of("act");

        NgordnetQuery nq = new NgordnetQuery(words, 0, 0, 0, NgordnetQueryType.HYPONYMS);
        String actual = studentHandler.handle(nq);
        String expected = "[act, action, change, demotion, human_action, human_activity, variation]";
        assertThat(actual).isEqualTo(expected);
    }

    // TODO: Add more unit tests (including edge case tests) here.
    @Test
    public void testOil(){
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                WORDS_FILE, TOTAL_COUNTS_FILE, SYNSETS_FILE_SUBSET, HYPONYMS_FILE_SUBSET);
        List<String> words = List.of("oil");
        NgordnetQuery nq = new NgordnetQuery(words, 0, 0, 0, NgordnetQueryType.HYPONYMS);
        String actual = studentHandler.handle(nq);
        String expected = "[Chinese_wood_oil, Dippel's_oil, absinthe_oil, acylglycerol, almond_oil, animal_oil, atar, athar, attar, attar_of_roses, axle_grease, babacu_oil, babassu_oil, beef_tallow, bitter_almond_oil, blubber, bone_oil, calamus_oil, camphor_oil, canola, canola_oil, chaulmoogra_oil, clove_oil, coconut_oil, cod-liver_oil, cod_liver_oil, cod_oil, cohune-nut_oil, cohune_fat, cohune_oil, colza_oil, cooking_oil, copra_oil, corn_oil, costus_oil, cottonseed_oil, croton_oil, crude, crude_oil, dolphin_oil, drying_oil, dubbin, essential_oil, eucalyptus_oil, expressed_almond_oil, fatty_oil, fish-liver_oil, fish_oil, fixed_oil, flaxseed_oil, fossil_oil, fuel_oil, fusel_oil, gas_oil, glyceride, goose_grease, grease, groundnut_oil, halibut-liver_oil, heating_oil, hedeoma_oil, hydnocarpus_oil, hyssop_oil, ilang-ilang, lanolin, lard_oil, lemon_grass, lemongrass, lemongrass_oil, linalool, linseed_oil, lubricating_oil, madia_oil, menhaden_oil, mineral_oil, motor_oil, mustard_oil, mutton_tallow, neat's-foot_oil, neroli_oil, oil, oil_of_cloves, oil_of_turpentine, oleo_oil, olive_oil, ottar, palm_oil, peanut_oil, pennyroyal_oil, petroleum, porpoise_oil, rape_oil, rapeseed_oil, resid, residual_oil, rock_oil, rose_oil, safflower_oil, salad_oil, salmon_oil, sardine_oil, sassafras_oil, seal_oil, sesame_oil, shale_oil, shark-liver_oil, shark_oil, soyabean_oil, soybean_oil, sperm_oil, spike_lavender_oil, spike_oil, spirit_of_turpentine, stand_oil, sunflower-seed_oil, sunflower_oil, sweet_almond_oil, sweet_oil, tall_oil, tallow, tallow_oil, train_oil, triglyceride, tuna_oil, tung_oil, turpentine, turps, vegetable_oil, volatile_oil, walnut_oil, whale_oil, wool_fat, wool_grease, wool_oil, wormwood_oil]";
        assertThat(actual).isEqualTo(expected);
    }
}
