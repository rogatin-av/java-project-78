package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {

    private final Map<String, Predicate<T>> validations = new HashMap<>();

    protected final void addValidation(String name, Predicate<T> fun) {
        validations.put(name, fun);
    }

    public final boolean isValid(T testValue) {

        return validations.entrySet().stream()
                .allMatch(entry -> entry.getValue().test(testValue));
    }
}
