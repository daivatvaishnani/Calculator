package config;

import java.util.List;

public class Grammar {
    private Grammar() {
    }

    public static final List<String> OPERATORS = List.of("+", "-", "*", "/");

    public static final List<String> UNARY_OPERATORS = List.of("+", "-");

    public static final List<String> BRACKET_STARTS = List.of("(", "{", "[");

    public static final List<String> BRACKET_ENDS = List.of(")", "}", "]");
}
