package hexlet.code.schemas;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class StringSchemaTest {

    @ParameterizedTest
    @ValueSource(strings = { "B", "hello" })
    void requiredTrue(String testValue) {
        StringSchema schema = new StringSchema();
        assertTrue(schema.isValid(null));
        schema.required();

        assertTrue(schema.isValid(testValue));
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {""})
    void requiredFalse(String testValue) {
        StringSchema schema = new StringSchema();
        schema.required();

        assertFalse(schema.isValid(testValue));
    }

    @ParameterizedTest
    @ValueSource(strings = { "text", "big text" })
    void minLengthTrue(String testValue) {
        StringSchema schema = new StringSchema();
        schema.minLength(4);

        assertTrue(schema.isValid(testValue));
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {"one", ""})
    void minLengthFalse(String testValue) {
        StringSchema schema = new StringSchema();
        schema.minLength(4);

        assertFalse(schema.isValid(testValue));
    }

    @ParameterizedTest
    @ValueSource(strings = { "text", "untextured"})
    void containsTrue(String testValue) {
        StringSchema schema = new StringSchema();
        schema.contains("tex");

        assertTrue(schema.isValid(testValue));
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {"hello world", ""})
    void containsFalse(String testValue) {
        StringSchema schema = new StringSchema();
        schema.contains("tex");

        assertFalse(schema.isValid(testValue));
    }

    @ParameterizedTest
    @ValueSource(strings = { "hello world", "collaboration"})
    void commonTrue(String testValue) {
        StringSchema schema = new StringSchema();
        schema.required().minLength(5).contains("ll").minLength(10);

        assertTrue(schema.isValid(testValue));
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {"hello", "text", ""})
    void commonFalse(String testValue) {
        StringSchema schema = new StringSchema();
        schema.required().minLength(5).contains("lo").minLength(10);

        assertFalse(schema.isValid(testValue));
    }
}
