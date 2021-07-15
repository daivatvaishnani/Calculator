package models;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class Term extends Expression {
    @NonNull
    private Double value;

    @Override
    Double evaluate() {
        return value;
    }
}
