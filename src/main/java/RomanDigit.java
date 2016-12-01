public enum RomanDigit {
    ONE("I", 1), FIVE("V", 5), TEN("X", 10), FIFTY("L", 50), ONE_HUNDRED("C", 100), FIVE_HUNDRED("D", 500), ONE_THOUSAND("M", 1000);

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
        } else if(FIFTY.getNumeralValue().equals(value)) {
            return FIFTY.intValue;
        } else if(ONE_HUNDRED.getNumeralValue().equals(value)) {
            return ONE_HUNDRED.intValue;
        } else if(FIVE_HUNDRED.getNumeralValue().equals(value)) {
            return FIVE_HUNDRED.intValue;
        } else if(ONE_THOUSAND.getNumeralValue().equals(value)) {
            return 1000;
        } else {
            return 0;
        }
    }
}