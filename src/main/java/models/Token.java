package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Token {
    public enum TokenType {
        OPERATOR,
        UNARY_OPERATOR,
        OPERAND,
        BRACKET_START,
        BRACKET_END
    }

    private TokenType type;
    private String data;
    private Double value;
    private Integer bracketMatch;
}
