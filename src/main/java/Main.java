import services.Calculator;
import services.Parser;
import services.Tokenizer;
import services.implementations.SimpleCalculator;
import services.implementations.SimpleParser;
import services.implementations.SimpleTokenizer;

public class Main {
    public static void main(String[] args) {
        String input = "   12 / 2 - 3.67233 + 12.0 - 5.0 * 2.0 / 2";
        Tokenizer tokenizer = new SimpleTokenizer();
        Parser parser = new SimpleParser();
        Calculator calculator = new SimpleCalculator(tokenizer, parser);
        System.out.println(calculator.calculate(input));
    }
}
