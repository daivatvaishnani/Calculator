package services.implementations;

import config.ExceptionMessages;
import config.Grammar;
import exceptions.InvalidExpressionException;
import models.*;
import services.Parser;

import java.util.List;
import java.util.Stack;

public class SimpleParser implements Parser {
    @Override
    public Expression parse(List<Token> tokens) throws InvalidExpressionException {
        checkForBalancedParenthesis(tokens);
        return buildExpression(tokens);
    }

    private Expression buildExpression(List<Token> tokens) throws InvalidExpressionException {
        Stack<Expression> st = new Stack<>();
        Token lastToken = null;
        for (Token token : tokens) {
            switch (token.getType()) {
                case BRACKET_START:
                    // TODO: implement bracket start
                    break;
                case BRACKET_END:
                    // TODO: implement bracket end
                    break;
                case OPERATOR:
                    addBinaryOperatorToExpression(st, token);
                    break;
                case UNARY_OPERATOR:
                    addUnaryOperatorToExpression(st, token);
                    break;
                case OPERAND:
                    if (lastToken != null && lastToken.getType().equals(Token.TokenType.OPERAND)) {
                        throw new InvalidExpressionException(ExceptionMessages.INVALID_EXPRESSION_OPERAND_AFTER_OPERAND_MESSAGE);
                    }
                    addTermToExpression(st, token);
                    break;
                default:
                    break;
            }
            lastToken = token;
        }
        return st.peek();
    }

    private void addUnaryOperatorToExpression(Stack<Expression> st, Token token) {
        UnaryOperator unaryOperator = UnaryOperator.builder()
                .operator(token.getData())
                .build();

        if (st.empty()) {
            st.push(unaryOperator);
            return;
        }
        Expression prevExpression = st.pop();
        if (prevExpression instanceof Operator) {
            // set according to precedence
            // 2 * 4 - -3 + 5
        } else {
            unaryOperator.setRight(prevExpression);
            st.push(unaryOperator);
        }
    }

    private void addBinaryOperatorToExpression(Stack<Expression> st, Token token) throws InvalidExpressionException {
        BinaryOperator binaryOperator = BinaryOperator.builder()
                .operator(token.getData())
                .build();

        if (st.empty()) {
            throw new IllegalStateException("Error in parsing: An expression should not start with binary operator");
        }
        Expression prevExpression = st.pop();
        if (prevExpression instanceof Operator) {
            // set according to precedence
            if (((Operator) prevExpression).hasPrecedenceOver(binaryOperator)) {
                binaryOperator.setLeft(prevExpression);
                st.push(binaryOperator);
            } else {
                Expression temp = prevExpression;
                while (((Operator) temp).getRight() != null
                        && ((Operator) temp).getRight() instanceof Operator
                        && binaryOperator.hasPrecedenceOver((Operator) temp)) {
                    temp = ((Operator) temp).getRight();
                }
                binaryOperator.setLeft(((Operator) temp).getRight());
                ((Operator) temp).setRight(binaryOperator);
                st.push(prevExpression);
            }
        } else {
            binaryOperator.setLeft(prevExpression);
            st.push(binaryOperator);
        }
    }

    private void addTermToExpression(Stack<Expression> st, Token token) throws InvalidExpressionException {
        Term term = Term.builder()
                .value(token.getValue())
                .build();

        if (st.empty()) {
            st.push(term);
            return;
        }
        Expression prevExpression = st.pop();
        if (prevExpression instanceof Operator) {
            // fill the right most rvalue
            Expression temp = prevExpression;
            while (((Operator) temp).getRight() != null) {
                if (((Operator) temp).getRight() instanceof Operator) {
                    temp = ((Operator) temp).getRight();
                } else {
                    throw new InvalidExpressionException(ExceptionMessages.INVALID_EXPRESSION_OPERAND_AFTER_OPERAND_MESSAGE);
                }
            }
            ((Operator) temp).setRight(term);
            st.push(prevExpression);
        } else {
            throw new InvalidExpressionException(ExceptionMessages.INVALID_EXPRESSION_OPERAND_AFTER_OPERAND_MESSAGE);
        }
    }

    private void checkForBalancedParenthesis(List<Token> tokens) throws InvalidExpressionException {
        if (!areParenthesisBalanced(tokens)) {
            throw new InvalidExpressionException(ExceptionMessages.INVALID_EXPRESSION_UNBALANCED_PARENTHESIS_MESSAGE);
        }
    }

    private boolean areParenthesisBalanced(List<Token> tokens) {
        Stack<Token> st = new Stack<>();
        for (Token token : tokens) {
            if (!token.getType().equals(Token.TokenType.BRACKET_START) && !token.getType().equals(Token.TokenType.BRACKET_END)) {
                continue;
            }
            if (token.getType().equals(Token.TokenType.BRACKET_START)) {
                st.push(token);
                continue;
            }
            if (st.empty() || Grammar.BRACKET_STARTS.indexOf(st.peek().getData()) != Grammar.BRACKET_ENDS.indexOf(token.getData())) {
                return false;
            }
            st.pop();
        }
        return st.empty();
    }
}

