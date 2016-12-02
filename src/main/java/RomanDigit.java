import java.util.Arrays;

public enum RomanDigit {
    ONE("I", 1),
    FIVE("V", 5),
    TEN("X", 10),
    FIFTY("L", 50),
    ONE_HUNDRED("C", 100),
    FIVE_HUNDRED("D", 500),
    ONE_THOUSAND("M", 1000);

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

    static Integer parseNumeral(final String value) {
        if (ONE.getNumeralValue().equals(value)) {
            return ONE.intValue;
        } else if (FIVE.getNumeralValue().equals(value)) {
            return FIVE.intValue;
        } else if (TEN.getNumeralValue().equals(value)) {
            return TEN.intValue;
        } else if (FIFTY.getNumeralValue().equals(value)) {
            return FIFTY.intValue;
        } else if (ONE_HUNDRED.getNumeralValue().equals(value)) {
            return ONE_HUNDRED.intValue;
        } else if (FIVE_HUNDRED.getNumeralValue().equals(value)) {
            return FIVE_HUNDRED.intValue;
        } else if (ONE_THOUSAND.getNumeralValue().equals(value)) {
            return ONE_THOUSAND.intValue;
        } else {
            return 0;
        }
    }

    public static boolean numeralIsPowerOfTen(final String currentNumeral) {
        return ONE.getNumeralValue().equals(currentNumeral) ||
                TEN.getNumeralValue().equals(currentNumeral) ||
                ONE_HUNDRED.getNumeralValue().equals(currentNumeral) ||
                ONE_THOUSAND.getNumeralValue().equals(currentNumeral);
    }

    public static RomanDigit[] valuesByDescendingOrder() {
        final RomanDigit[] descendingOrderValues = values();
        Arrays.sort(descendingOrderValues, (digit1, digit2) -> digit2.intValue - digit1.intValue);
        return descendingOrderValues;
    }
}