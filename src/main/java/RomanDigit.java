public enum RomanDigit {
    ONE("I"), FIVE("V"), TEN("X");

    private final String value;

    RomanDigit(final String numeralValue) {
        this.value = numeralValue;
    }

    public String getNumeralValue() {
        return value;
    }
}