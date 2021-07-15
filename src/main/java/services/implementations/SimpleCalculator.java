package services.implementations;

import exceptions.InvalidExpressionException;
import exceptions.InvalidTokenException;
import models.Expression;
import models.Token;
import services.Calculator;
import services.Parser;
import services.Tokenizer;

import java.util.List;

public class SimpleCalculator implements Calculator {
    private final Tokenizer tokenizer;
    private final Parser parser;

    public SimpleCalculator(Tokenizer tokenizer, Parser parser) {
        this.tokenizer = tokenizer;
        this.parser = parser;
    }

    @Override
    public Double calculate(String input) {
        try {
            List<Token> tokens = tokenizer.tokenize(input);
            Expression expression = parser.parse(tokens);
            return expression.getValue();
        } catch (InvalidTokenException | InvalidExpressionException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
