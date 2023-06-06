package infra.enums;

public enum TypesOfTherapists {
    internal(1),
    external(2);
    private final int value;

    private TypesOfTherapists(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}

