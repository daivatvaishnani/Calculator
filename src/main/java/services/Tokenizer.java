package services;

import exceptions.InvalidTokenException;
import models.Token;

import java.util.List;

public interface Tokenizer {
    List<Token> tokenize(String expression) throws InvalidTokenException;
}
