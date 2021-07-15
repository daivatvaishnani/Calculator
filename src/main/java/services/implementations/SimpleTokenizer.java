package services.implementations;

import config.ExceptionMessages;
import config.Grammar;
import exceptions.InvalidTokenException;
import models.Token;
import services.Tokenizer;

import java.util.ArrayList;
import java.util.List;

public class SimpleTokenizer implements Tokenizer {
    @Override
    public List<Token> tokenize(String expression) throws InvalidTokenException {
        List<Token> tokens = new ArrayList<>();

        StringBuilder operandToken = new StringBuilder();

        String lastToken = null;
        for (int i = 0; i < expression.length(); ++i) {
            String c = String.valueOf(expression.charAt(i));
            if (isOperandToken(c)) {
                operandToken.append(c);
            } else {
                if (operandToken.length() > 0) {
                    tokens.add(buildOperandToken(operandToken));
                    operandToken = new StringBuilder();
                }
                if (isWhitespace(c)) {
                    continue;
                }
                tokens.add(buildNonOperandToken(c, lastToken));
            }
            lastToken = c;
        }

        if (operandToken.length() > 0) {
            tokens.add(buildOperandToken(operandToken));
        }

        return tokens;
    }

    private Boolean isWhitespace(String c) {
        return c.matches("\\s+");
    }

    private Boolean isOperandToken(String c) {
        return !isWhitespace(c)
                && !Grammar.BRACKET_STARTS.contains(c)
                && !Grammar.BRACKET_ENDS.contains(c)
                && !Grammar.OPERATORS.contains(c);
    }

    private Token buildOperandToken(StringBuilder operandToken) throws InvalidTokenException {
        try {
            return Token.builder()
                    .type(Token.TokenType.OPERAND)
                    .data(operandToken.toString())
                    .value(Double.parseDouble(operandToken.toString()))
                    .build();
        } catch (NumberFormatException e) {
            throw new InvalidTokenException(ExceptionMessages.INVALID_TOKEN_OPERAND_MESSAGE);
        }
    }

    private Token buildNonOperandToken(String c, String lastToken) throws InvalidTokenException {
        Token.TokenType tokenType = getTokenType(c, lastToken);
        return Token.builder()
                .type(tokenType)
                .data(c)
                .build();
    }

    private Token.TokenType getTokenType(String c, String lastToken) throws InvalidTokenException {
        if (Grammar.BRACKET_STARTS.contains(c)) {
            return Token.TokenType.BRACKET_START;
        } else if (Grammar.BRACKET_ENDS.contains(c)) {
            return Token.TokenType.BRACKET_END;
        } else if (Grammar.OPERATORS.contains(c)) {
            if (lastToken == null || Grammar.OPERATORS.contains(lastToken)) {
                // unary operator
                return Token.TokenType.UNARY_OPERATOR;
            }
            return Token.TokenType.OPERATOR;
        } else {
            throw new InvalidTokenException(ExceptionMessages.INVALID_TOKEN_UNEXPECTED_TOKEN_MESSAGE);
        }
    }
}
