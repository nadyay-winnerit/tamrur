package infra.enums;

public enum TypePhone {
    landline(1),
    mobile(2),
    fax(3);
    private final int value;

    TypePhone(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
