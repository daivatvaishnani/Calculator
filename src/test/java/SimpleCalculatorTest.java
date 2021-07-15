import exceptions.InvalidExpressionException;
import exceptions.InvalidTokenException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import services.Calculator;
import services.Parser;
import services.Tokenizer;
import services.implementations.SimpleCalculator;
import services.implementations.SimpleParser;
import services.implementations.SimpleTokenizer;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleCalculatorTest {
    static Calculator calculator;

    static Tokenizer tokenizer;

    static Parser parser;

    @BeforeAll
    static void setup() {
        // TODO: Mock these if get time
        tokenizer = new SimpleTokenizer();
        parser = new SimpleParser();
        calculator = new SimpleCalculator(tokenizer, parser);
    }

    @Test
    @DisplayName("Simple Addition test")
    void additionTest() {
        String input = "3 + 5";
        double expectedValue = 3.0 + 5.0;

        assertEquals(expectedValue, calculator.calculate(input));
    }

    @Test
    @DisplayName("Addition test with decimals")
    void additionTest_WithDecimals() {
        String input = "3.6678 + 5.881272";
        double expectedValue = 3.6678 + 5.881272;

        assertEquals(expectedValue, calculator.calculate(input));
    }

    @Test
    @DisplayName("Simple Subtraction test")
    void subtractionTest() {
        String input = "3 - 5";
        double expectedValue = 3.0 - 5.0;

        assertEquals(expectedValue, calculator.calculate(input));
    }

    @Test
    @DisplayName("Subtraction test with decimals")
    void subtractionTest_WithDecimals() {
        String input = "3.67233 - 5.127233";
        double expectedValue = 3.67233 - 5.127233;

        assertEquals(expectedValue, calculator.calculate(input));
    }

    @Test
    @DisplayName("Simple Multiplication test")
    void multiplicationTest() {
        String input = "3 * 5";
        double expectedValue = 3.0 * 5.0;

        assertEquals(expectedValue, calculator.calculate(input));
    }

    @Test
    @DisplayName("Multiplication test with decimals")
    void multiplicationTest_WithDecimals() {
        String input = "3.6678 * 5.881272";
        double expectedValue = 3.6678 * 5.881272;

        assertEquals(expectedValue, calculator.calculate(input));
    }

    @Test
    @DisplayName("Simple Division test")
    void divisionTest() {
        String input = "3 / 5";
        double expectedValue = 3.0 / 5.0;

        assertEquals(expectedValue, calculator.calculate(input));
    }

    @Test
    @DisplayName("Division test with decimals")
    void divisionTest_WithDecimals() {
        String input = "3.6678 / 5.881272";
        double expectedValue = 3.6678 / 5.881272;

        assertEquals(expectedValue, calculator.calculate(input));
    }

    @Test
    @DisplayName("Division by 0 test")
    void divisionByZeroTest() {
        String input = "3.6678 / 0";
        assertNull(calculator.calculate(input));
    }

    @Test
    @DisplayName("Single digit test")
    void singleDigitTest() {
        String input = "-3.67233";
        double expectedValue = -3.67233;

        assertEquals(expectedValue, calculator.calculate(input));
    }

    @Test
    @DisplayName("Complex Expression Test #1")
    void complexExpressionTest1() {
        String input = "    3.67233 + 12.0 - 5.0 * 2.0 / 2";
        double expectedValue = 3.67233 + 12.0 - 5.0 * 2.0 / 2;

        assertEquals(expectedValue, calculator.calculate(input));
    }

    @Test
    @DisplayName("Complex Expression Test #2")
    void complexExpressionTest2() {
        String input = "   12 / 2 - 3.67233 + 12.0 - 5.0 * 2.0 / 2";
        double expectedValue = 12.0 / 2.0 - 3.67233 + 12.0 - 5.0 * 2.0 / 2;

        assertEquals(expectedValue, calculator.calculate(input));
    }

    @Test
    @DisplayName("Complex Expression Test #3")
    void complexExpressionTest3() {
        String input = "    12 / 3.67233 * 43 + 12.0 - 5.0 * 2.0 / 2";
        double expectedValue = 12.0 / 3.67233 * 43.0 + 12.0 - 5.0 * 2.0 / 2.0;

        assertEquals(expectedValue, calculator.calculate(input));
    }

    @Test
    @DisplayName("Complex Expression Test #4")
    void complexExpressionTest4() {
        String input = "12.0 + 1 / 3.67233 * 4.0 - 11.0 / 3.0 * 2 - 5.0 * 2.0 / 2.0";
        double expectedValue = 12.0 + 1 / 3.67233 * 4.0 - 11.0 / 3.0 * 2 - 5.0 * 2.0 / 2.0;

        assertEquals(expectedValue, calculator.calculate(input));
    }
}
