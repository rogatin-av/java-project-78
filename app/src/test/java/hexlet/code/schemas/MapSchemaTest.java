package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MapSchemaTest {

    private static MapSchema shapeSchema;

    @BeforeAll
    public static void prepareShapeSchema() {
        Validator v = new Validator();
        shapeSchema = v.map();
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));
        shapeSchema.shape(schemas);
    }

    @Test
    void checkNull() {
        MapSchema schema = new MapSchema();

        boolean expected = true;
        boolean actual = schema.isValid(null);

        assertEquals(expected, actual);
    }

    static Stream<Map<String, String>> requiredTrue() {
        return Stream.of(
                Map.of(),
                Map.of("one", "1", "two", "2")
        );
    }

    @ParameterizedTest
    @MethodSource
    void requiredTrue(Map<String, String> testMap) {
        MapSchema schema = new MapSchema();
        schema.required();

        boolean expected = true;
        boolean actual = schema.isValid(testMap);

        assertEquals(expected, actual);
    }

    @Test
    void requiredFalse() {
        MapSchema schema = new MapSchema();
        schema.required();

        boolean expected = false;
        boolean actual = schema.isValid(null);

        assertEquals(expected, actual);
    }

    @Test
    void sizeofTrue() {
        MapSchema schema = new MapSchema();
        schema.sizeof(2);
        Map<String, String> testMap = Map.of("one", "1", "two", "2");

        boolean expected = true;
        boolean actual = schema.isValid(testMap);

        assertEquals(expected, actual);
    }

    static Stream<Map<String, String>> sizeofFalse() {
        return Stream.of(
                Map.of("one", "1"),
                Map.of("one", "1", "two", "2", "three", "3")
        );
    }

    @ParameterizedTest
    @NullSource
    @MethodSource
    void sizeofFalse(Map<String, String> testMap) {
        MapSchema schema = new MapSchema();
        schema.sizeof(2);

        boolean expected = false;
        boolean actual = schema.isValid(testMap);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = { "Smith", "Ho" })
    void shapeTrue(String lastName) {
        Map<String, String> human = new HashMap<>();
        human.put("firstName", "John");
        human.put("lastName", lastName);

        boolean expected = true;
        boolean actual = shapeSchema.isValid(human);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = { "B", "" })
    void shapeFalse(String lastName) {
        Map<String, String> human = new HashMap<>();
        human.put("firstName", "John");
        human.put("lastName", lastName);

        boolean expected = false;
        boolean actual = shapeSchema.isValid(human);

        assertEquals(expected, actual);
    }
}
