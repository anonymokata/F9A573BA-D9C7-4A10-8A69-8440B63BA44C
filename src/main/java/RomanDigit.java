public enum RomanDigit {
    ONE("I"), FIVE("V"), TEN("X");

    private final String value;

    RomanDigit(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}