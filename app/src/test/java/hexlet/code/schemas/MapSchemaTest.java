package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
    void requiredTrue() {
        MapSchema schema = new MapSchema();
        assertTrue(schema.isValid(null));
        schema.required();

        assertTrue(schema.isValid(new HashMap<>()));
    }

    @Test
    void requiredFalse() {
        MapSchema schema = new MapSchema();
        schema.required();

        assertFalse(schema.isValid(null));
    }

    @Test
    void sizeofTrue() {
        MapSchema schema = new MapSchema();
        schema.sizeof(2);

        assertTrue(schema.isValid(Map.of("one", "1", "two", "2")));
    }

    @Test
    void sizeofFalse() {
        MapSchema schema = new MapSchema();
        schema.sizeof(2);

        assertFalse(schema.isValid(Map.of("one", "1")));
        assertFalse(schema.isValid(Map.of("one", "1", "two", "2", "three", "3")));
        assertFalse(schema.isValid(null));
    }

    @ParameterizedTest
    @ValueSource(strings = { "Smith", "Ho" })
    void shapeTrue(String lastName) {
        Map<String, String> human = new HashMap<>();
        human.put("firstName", "John");
        human.put("lastName", lastName);

        assertTrue(shapeSchema.isValid(human));
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = { "B", "" })
    void shapeFalse(String lastName) {
        Map<String, String> human = new HashMap<>();
        human.put("firstName", "John");
        human.put("lastName", lastName);

        assertFalse(shapeSchema.isValid(human));
    }
}
