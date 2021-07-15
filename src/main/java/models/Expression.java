package models;

import exceptions.InvalidExpressionException;
import lombok.experimental.SuperBuilder;

@SuperBuilder
abstract public class Expression {
    private Double evaluatedValue;

    public Double getValue() throws InvalidExpressionException {
        if (evaluatedValue != null) return evaluatedValue;
        evaluatedValue = evaluate();
        return evaluatedValue;
    }

    abstract Double evaluate() throws InvalidExpressionException;
}
