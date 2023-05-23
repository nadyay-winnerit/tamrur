package infra.enums;

public enum Gender {

    male(1),
    female(2);
    private final int value;

    private Gender(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
