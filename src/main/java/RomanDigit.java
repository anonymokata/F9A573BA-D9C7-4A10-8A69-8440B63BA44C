public enum RomanDigit {
    ONE("I", 1), FIVE("V", 5), TEN("X", 10);

    private final String value;
    private final int intValue;

    RomanDigit(final String numeralValue, final int intValue) {
        this.value = numeralValue;
        this.intValue = intValue;
    }

    public String getNumeralValue() {
        return value;
    }

    public int getIntValue() {
        return intValue;
    }
}