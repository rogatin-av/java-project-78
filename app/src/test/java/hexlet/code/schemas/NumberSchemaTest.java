package hexlet.code.schemas;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberSchemaTest {

    @Test
    void checkNull() {
        NumberSchema schema = new NumberSchema();

        boolean expected = true;
        boolean actual = schema.isValid(null);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(ints = { 5, 0 })
    void requiredTrue(int testValue) {
        NumberSchema schema = new NumberSchema();
        schema.required();

        boolean expected = true;
        boolean actual = schema.isValid(testValue);

        assertEquals(expected, actual);
    }

    @Test
    void requiredFalse() {
        NumberSchema schema = new NumberSchema();
        schema.required();

        boolean expected = false;
        boolean actual = schema.isValid(null);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(ints = { 5, 1 })
    void positiveTrue(Integer testValue) {
        NumberSchema schema = new NumberSchema();
        schema.positive();

        boolean expected = true;
        boolean actual = schema.isValid(testValue);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, -5 })
    void positiveFalse(Integer testValue) {
        NumberSchema schema = new NumberSchema();
        schema.positive();

        boolean expected = false;
        boolean actual = schema.isValid(testValue);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(ints = { 5, 2, 7 })
    void rangeTrue(int testValue) {
        NumberSchema schema = new NumberSchema();
        schema.range(2, 7);

        boolean expected = true;
        boolean actual = schema.isValid(testValue);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(ints = { 1, 8, -5 })
    void rangeFalse(Integer testValue) {
        NumberSchema schema = new NumberSchema();
        schema.range(2, 7);

        boolean expected = false;
        boolean actual = schema.isValid(testValue);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(ints = { 3, 2, 5 })
    void commonTrue(int testValue) {
        NumberSchema schema = new NumberSchema();
        schema.range(7, 9).required().positive().range(2, 5);

        boolean expected = true;
        boolean actual = schema.isValid(testValue);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(ints = { 1, 8, -5 })
    void commonFalse(Integer testValue) {
        NumberSchema schema = new NumberSchema();
        schema.range(7, 9).required().positive().range(2, 5);

        boolean expected = false;
        boolean actual = schema.isValid(testValue);

        assertEquals(expected, actual);
    }
}
