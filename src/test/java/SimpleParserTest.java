import org.junit.jupiter.api.BeforeAll;
import services.Parser;
import services.implementations.SimpleParser;

public class SimpleParserTest {
    static Parser parser;

    // TODO: write parser tests

    @BeforeAll
    static void setup() {
        parser = new SimpleParser();
    }
}
