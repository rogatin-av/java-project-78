package hexlet.code.schemas;

import java.util.Objects;

public class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        addValidation("required", Objects::nonNull);
        return this;
    }

    public NumberSchema positive() {
        addValidation("positive", num -> num > 0);
        return this;
    }

    public NumberSchema range(int from, int to) {
        addValidation("range", num -> num >= from && num <= to);
        return this;
    }
}
