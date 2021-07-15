package models;

import config.ExceptionMessages;
import exceptions.InvalidExpressionException;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class BinaryOperator extends Operator {
    private Expression left;

    @Override
    Double evaluate() throws InvalidExpressionException {
        if (left == null) {
            throw new InvalidExpressionException(ExceptionMessages.INVALID_EXPRESSION_MISSING_LVALUE_MESSAGE);
        }
        if (right == null) {
            throw new InvalidExpressionException(ExceptionMessages.INVALID_EXPRESSION_MISSING_RVALUE_MESSAGE);
        }
        return operate(left.getValue(), right.getValue());
    }

    private Double operate(Double valueLeft, Double valueRight) throws InvalidExpressionException {
        switch (operator) {
            case "*":
                return valueLeft * valueRight;
            case "/":
                if (valueRight == 0.0) {
                    throw new InvalidExpressionException(ExceptionMessages.INVALID_EXPRESSION_DIVISION_BY_ZERO_MESSAGE);
                }
                return valueLeft / valueRight;
            case "+":
                return valueLeft + valueRight;
            case "-":
                return valueLeft - valueRight;
            default:
                throw new InvalidExpressionException(ExceptionMessages.INVALID_EXPRESSION_UNEXPECTED_BINARY_OPERATOR_MESSAGE);
        }
    }
}
