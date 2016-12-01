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

    static Integer parseNumeral(final String value) {
        if (ONE.getNumeralValue().equals(value)) {
            return ONE.intValue;
        } else if (FIVE.getNumeralValue().equals(value)) {
            return FIVE.intValue;
        } else if (TEN.getNumeralValue().equals(value)) {
            return TEN.intValue;
        } else {
            return 0;
        }
    }
}