import org.junit.jupiter.api.BeforeAll;
import services.Tokenizer;
import services.implementations.SimpleTokenizer;

public class SimpleTokenizerTest {
    static Tokenizer tokenizer;

    // TODO: write tokenizer tests

    @BeforeAll
    static void setup() {
        tokenizer = new SimpleTokenizer();
    }
}
