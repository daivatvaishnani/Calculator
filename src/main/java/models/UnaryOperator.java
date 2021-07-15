package models;

import config.ExceptionMessages;
import exceptions.InvalidExpressionException;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class UnaryOperator extends Operator {
    @Override
    Double evaluate() throws InvalidExpressionException {
        if (right == null) {
            throw new InvalidExpressionException(ExceptionMessages.INVALID_EXPRESSION_MISSING_RVALUE_MESSAGE);
        }
        return operate(right.getValue());
    }

    private Double operate(Double valueRight) throws InvalidExpressionException {
        switch (operator) {
            case "+":
                return 1.0 * valueRight;
            case "-":
                return -1.0 * valueRight;
            default:
                throw new InvalidExpressionException(ExceptionMessages.INVALID_EXPRESSION_UNEXPECTED_UNARY_OPERATOR_MESSAGE);
        }
    }
}
