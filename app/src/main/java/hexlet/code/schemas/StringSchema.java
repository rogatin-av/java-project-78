package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {

//    private boolean required = false;
//    private int minLength = -1;
//    private String contains = "";

//    public boolean isValid(String text) {
//
//        if (required && text == null || text.isEmpty()) {
//            return false;
//        }
//
//        if (minLength > -1 && text.length() < minLength) {
//            return false;
//        }
//
//        if (!contains.isEmpty() && !text.contains(contains)) {
//            return false;
//        }
//
//        return true;
//    }

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
