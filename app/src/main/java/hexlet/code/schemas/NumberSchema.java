package hexlet.code.schemas;

import java.util.Objects;

public class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        addValidation("required", Objects::nonNull);
        return this;
    }

    public NumberSchema positive() {
        addValidation("positive", num -> num == null || num > 0);
        return this;
    }

    public NumberSchema range(int from, int to) {
        addValidation("range", num -> num != null && num >= from && num <= to);
        return this;
    }
}
