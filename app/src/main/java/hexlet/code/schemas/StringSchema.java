package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        addValidation("required", text -> text != null && !text.isEmpty());
        return this;
    }

    public StringSchema minLength(int min) {
        addValidation("minLength", text -> text.length() >= min);
        return this;
    }

    public StringSchema contains(String test) {
        addValidation("contains", text -> text.contains(test));
        return this;
    }
}
