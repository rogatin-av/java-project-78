package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public final class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema required() {
        addValidation("required", Objects::nonNull);
        return this;
    }

    public MapSchema sizeof(int size) {
        addValidation("sizeof", map -> map != null && map.size() == size);
        return this;
    }

    public <T> MapSchema shape(Map<String, BaseSchema<T>> shemas) {

        addValidation("shape", map -> {
            return shemas.entrySet().stream().allMatch(entry -> {
                String key = entry.getKey();
                BaseSchema<T> schema = entry.getValue();

                return !map.containsKey(key) || schema.isValid((T) map.get(key));
            });
        });

        return this;
    }
}
