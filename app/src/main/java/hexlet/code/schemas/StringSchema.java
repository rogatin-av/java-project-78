package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        addValidation("required", text -> text != null && !text.isEmpty());
        return this;
    }

    public StringSchema minLength(int min) {
        addValidation("minLength", text -> text != null && text.length() >= min);
        return this;
    }

    public StringSchema contains(String test) {
        addValidation("contains", text -> text != null && text.contains(test));
        return this;
    }
}
