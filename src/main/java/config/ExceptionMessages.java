package config;

public class ExceptionMessages {
    private ExceptionMessages() {
    }


    public static final String INVALID_TOKEN_UNEXPECTED_TOKEN_MESSAGE = "Invalid Token: Unexpected token found";

    public static final String INVALID_TOKEN_OPERAND_MESSAGE = "Invalid Token: Invalid operand found for the expression";


    public static final String INVALID_EXPRESSION_VALUE_MESSAGE = "Invalid Expression: A single value expression must have non-null value";

    public static final String INVALID_EXPRESSION_MISSING_OPERATOR_MESSAGE = "Invalid Expression: A non-single value expression must have an operator";

    public static final String INVALID_EXPRESSION_MISSING_RVALUE_MESSAGE = "Invalid Expression: A non-single value expression must have an r-value";

    public static final String INVALID_EXPRESSION_MISSING_LVALUE_MESSAGE = "Invalid Expression: A non-single value expression with non-sign operator must have an l-value";

    public static final String INVALID_EXPRESSION_UNEXPECTED_BINARY_OPERATOR_MESSAGE = "Invalid Expression: Unexpected binary operator found for the expression";

    public static final String INVALID_EXPRESSION_UNEXPECTED_UNARY_OPERATOR_MESSAGE = "Invalid Expression: Unexpected unary operator found for the expression";

    public static final String INVALID_EXPRESSION_DIVISION_BY_ZERO_MESSAGE = "Invalid Expression: Cannot divide by 0";

    public static final String INVALID_EXPRESSION_UNBALANCED_PARENTHESIS_MESSAGE = "Invalid Expression: Expression has un-balanced parenthesis";

    public static final String INVALID_EXPRESSION_OPERAND_AFTER_OPERAND_MESSAGE = "Invalid Expression: Expression has two consecutive operands without operator between them";

    public static final String INVALID_EXPRESSION_ILL_FORMED_OPERATORS_MESSAGE = "Invalid Expression: Expression has ill-formed operators";
}
