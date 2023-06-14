package data;

public class UiValue {

    private String value;
    private Enum enumType;
    public boolean validated;
    public String label;

    public String value() {
        return value;
    }

    public void value(String value) {
        this.value = value;
    }

    public Enum enumType() {
        return enumType;
    }

    public void enumType(Enum enumType) {
        this.enumType = enumType;
    }

    public UiValue label(String label) {
        this.label = label;
        return this;
    }
}
