package converter;

import java.util.Arrays;

public enum RomanDigit {
    ONE("I", 1, PowerOfType.TEN),
    FIVE("V", 5, PowerOfType.FIVE),
    TEN("X", 10, PowerOfType.TEN),
    FIFTY("L", 50, PowerOfType.FIVE),
    ONE_HUNDRED("C", 100, PowerOfType.TEN),
    FIVE_HUNDRED("D", 500, PowerOfType.FIVE),
    ONE_THOUSAND("M", 1000, PowerOfType.TEN);

    private final String value;
    private final int intValue;
    private final PowerOfType powerOfType;

    RomanDigit(final String numeralValue, final int intValue, final PowerOfType powerOfType) {
        this.value = numeralValue;
        this.intValue = intValue;
        this.powerOfType = powerOfType;
    }

    String getNumeralValue() {
        return value;
    }

    int getIntValue() {
        return intValue;
    }

    PowerOfType getPowerOfType() {
        return powerOfType;
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

    static boolean numeralIsPowerOfTen(final String currentNumeral) {
        return ONE.getNumeralValue().equals(currentNumeral) ||
                TEN.getNumeralValue().equals(currentNumeral) ||
                ONE_HUNDRED.getNumeralValue().equals(currentNumeral) ||
                ONE_THOUSAND.getNumeralValue().equals(currentNumeral);
    }

    static RomanDigit[] valuesByDescendingOrder() {
        final RomanDigit[] descendingOrderValues = values();
        Arrays.sort(descendingOrderValues, (digit1, digit2) -> digit2.intValue - digit1.intValue);
        return descendingOrderValues;
    }

    static RomanDigit getNextLowestPowerOfTen(final RomanDigit digit) {
        if(digit.equals(ONE_THOUSAND) || digit.equals(FIVE_HUNDRED)) {
            return ONE_HUNDRED;
        } else if(digit.equals(ONE_HUNDRED) || digit.equals(FIFTY)) {
            return TEN;
        } else {
            return ONE;
        }
    }

    static RomanDigit getNextHighestDigit(final RomanDigit digit) {
        if(digit.equals(ONE)) {
            return FIVE;
        } else if(digit.equals(FIVE)) {
            return TEN;
        } else if(digit.equals(TEN)) {
            return FIFTY;
        } else if(digit.equals(FIFTY)) {
            return ONE_HUNDRED;
        } else if(digit.equals(ONE_HUNDRED)) {
            return FIVE_HUNDRED;
        } else {
            return ONE_THOUSAND;
        }
    }

    static RomanDigit parseString(final String value) {
        for(final RomanDigit digit : values()) {
            if(digit.getNumeralValue().equals(value)) {
                return digit;
            }
        }
        return null;
    }
}