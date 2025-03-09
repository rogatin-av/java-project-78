package hexlet.code.schemas;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringSchemaTest {

    @Test
    void checkNull() {
        StringSchema schema = new StringSchema();

        boolean expected = true;
        boolean actual = schema.isValid(null);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = { "B", "hello" })
    void requiredTrue(String testValue) {
        StringSchema schema = new StringSchema();
        schema.required();

        boolean expected = true;
        boolean actual = schema.isValid(testValue);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {""})
    void requiredFalse(String testValue) {
        StringSchema schema = new StringSchema();
        schema.required();

        boolean expected = false;
        boolean actual = schema.isValid(testValue);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = { "text", "big text" })
    void minLengthTrue(String testValue) {
        StringSchema schema = new StringSchema();
        schema.minLength(4);

        boolean expected = true;
        boolean actual = schema.isValid(testValue);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {"one", ""})
    void minLengthFalse(String testValue) {
        StringSchema schema = new StringSchema();
        schema.minLength(4);

        boolean expected = false;
        boolean actual = schema.isValid(testValue);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = { "text", "untextured"})
    void containsTrue(String testValue) {
        StringSchema schema = new StringSchema();
        schema.contains("tex");

        boolean expected = true;
        boolean actual = schema.isValid(testValue);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {"hello world", ""})
    void containsFalse(String testValue) {
        StringSchema schema = new StringSchema();
        schema.contains("tex");

        boolean expected = false;
        boolean actual = schema.isValid(testValue);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = { "hello world", "collaboration"})
    void commonTrue(String testValue) {
        StringSchema schema = new StringSchema();
        schema.required().minLength(5).contains("ll").minLength(10);

        boolean expected = true;
        boolean actual = schema.isValid(testValue);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {"hello", "text", ""})
    void commonFalse(String testValue) {
        StringSchema schema = new StringSchema();
        schema.required().minLength(5).contains("lo").minLength(10);

        boolean expected = false;
        boolean actual = schema.isValid(testValue);

        assertEquals(expected, actual);
    }
}
