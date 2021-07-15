package models;

import config.Grammar;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
abstract public class Operator extends Expression {
    @NonNull
    String operator;
    Expression right;

    public Boolean hasPrecedenceOver(Operator operator) {
        if (this instanceof UnaryOperator) {
            if (operator instanceof UnaryOperator) {
                return this.binaryPrecedence() >= operator.binaryPrecedence();
            }
            return true;
        }
        // this is binary
        if (operator instanceof UnaryOperator) {
            return false;
        }
        // both are binary
        return this.binaryPrecedence() >= operator.binaryPrecedence();
    }

    public Integer binaryPrecedence() {
        return Grammar.OPERATORS.indexOf(operator);
    }
}
