package hexlet.code.schemas;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class NumberSchemaTest {

    @ParameterizedTest
    @ValueSource(ints = { 5, 0 })
    void requiredTrue(int testValue) {
        NumberSchema schema = new NumberSchema();
        assertTrue(schema.isValid(null));
        schema.required();

        assertTrue(schema.isValid(testValue));
    }

    @Test
    void requiredFalse() {
        NumberSchema schema = new NumberSchema();
        schema.required();

        assertFalse(schema.isValid(null));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(ints = { 5, 1 })
    void positiveTrue(Integer testValue) {
        NumberSchema schema = new NumberSchema();
        schema.positive();

        assertTrue(schema.isValid(testValue));
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, -5 })
    void positiveFalse(Integer testValue) {
        NumberSchema schema = new NumberSchema();
        schema.positive();

        assertFalse(schema.isValid(testValue));
    }

    @ParameterizedTest
    @ValueSource(ints = { 5, 2, 7 })
    void rangeTrue(int testValue) {
        NumberSchema schema = new NumberSchema();
        schema.range(2, 7);

        assertTrue(schema.isValid(testValue));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(ints = { 1, 8, -5 })
    void rangeFalse(Integer testValue) {
        NumberSchema schema = new NumberSchema();
        schema.range(2, 7);

        assertFalse(schema.isValid(testValue));
    }

    @ParameterizedTest
    @ValueSource(ints = { 3, 2, 5 })
    void commonTrue(int testValue) {
        NumberSchema schema = new NumberSchema();
        schema.range(7, 9).required().positive().range(2, 5);

        assertTrue(schema.isValid(testValue));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(ints = { 1, 8, -5 })
    void commonFalse(Integer testValue) {
        NumberSchema schema = new NumberSchema();
        schema.range(7, 9).required().positive().range(2, 5);

        assertFalse(schema.isValid(testValue));
    }
}
