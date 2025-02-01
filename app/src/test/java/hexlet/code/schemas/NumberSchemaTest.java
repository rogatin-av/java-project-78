package hexlet.code.schemas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class NumberSchemaTest {

    @Test
    void required() {
        var schema = new NumberSchema();

        assertTrue(schema.isValid(null));

        schema.required();

        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(0));

        assertFalse(schema.isValid(null));
    }

    @Test
    void positive() {
        var schema = new NumberSchema();
        schema.positive();

        assertTrue(schema.isValid(5));

        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(-5));
        assertFalse(schema.isValid(null));
    }

    @Test
    void range() {
        var schema = new NumberSchema();
        schema.range(2, 7);

        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(2));
        assertTrue(schema.isValid(7));

        assertFalse(schema.isValid(1));
        assertFalse(schema.isValid(null));
    }

    @Test
    void common() {
        var schema = new NumberSchema();
        schema.range(7, 9).required().positive().range(2, 5);

        assertTrue(schema.isValid(3));
        assertTrue(schema.isValid(2));
        assertTrue(schema.isValid(5));

        assertFalse(schema.isValid(1));
        assertFalse(schema.isValid(8));
        assertFalse(schema.isValid(-5));
        assertFalse(schema.isValid(null));
    }
}
