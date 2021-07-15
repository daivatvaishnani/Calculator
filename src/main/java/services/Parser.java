package services;

import exceptions.InvalidExpressionException;
import models.Expression;
import models.Token;

import java.util.List;

public interface Parser {
    Expression parse(List<Token> tokens) throws InvalidExpressionException;
}
