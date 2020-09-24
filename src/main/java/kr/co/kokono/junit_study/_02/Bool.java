package kr.co.kokono.junit_study._02;

public enum Bool {

    False(0),
    True(1);

    public static final int FALSE = 0;
    public static final int TRUE = 1;

    private int value;

    Bool(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
