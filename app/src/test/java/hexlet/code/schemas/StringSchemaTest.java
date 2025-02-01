package hexlet.code.schemas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class StringSchemaTest {

    @Test
    void required() {
        var schema = new StringSchema();

        assertTrue(schema.isValid(null));

        schema.required();

        assertTrue(schema.isValid("text"));

        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
    }

    @Test
    void minLength() {
        var schema = new StringSchema();
        schema.minLength(4);

        assertTrue(schema.isValid("text"));
        assertTrue(schema.isValid("big text"));

        assertFalse(schema.isValid("one"));
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
    }

    @Test
    void contains() {
        var schema = new StringSchema();
        schema.contains("tex");

        assertTrue(schema.isValid("text"));
        assertTrue(schema.isValid("untextured"));

        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid("hello world"));
        assertFalse(schema.isValid(null));
    }

    @Test
    void common() {
        var schema = new StringSchema();
        schema.required().minLength(5).contains("lo").minLength(10);

        assertTrue(schema.isValid("hello world"));

        assertFalse(schema.isValid("hello"));
        assertFalse(schema.isValid("text"));
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
    }
}
