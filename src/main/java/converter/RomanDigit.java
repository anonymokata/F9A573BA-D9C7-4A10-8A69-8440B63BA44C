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

    RomanDigit getNextLowestPowerOfTen() {
        final int intValueOfNextTenPower = calculateIntValueOfNextLowestPowerOfTen();
        for(final RomanDigit digit : values()) {
            if(digit.getIntValue() == intValueOfNextTenPower) {
                return digit;
            }
        }
        return ONE;
    }

    private int calculateIntValueOfNextLowestPowerOfTen() {
        return powerOfType.equals(PowerOfType.TEN) ? intValue / 10 : intValue / 5;
    }

    RomanDigit getNextHighestDigit() {
        final int intValueOfNextHighest = calculateIntValueOfNextHighestDigit();
        for(final RomanDigit digit : values()) {
            if(digit.getIntValue() == intValueOfNextHighest) {
                return digit;
            }
        }
        return ONE_THOUSAND;
    }

    private int calculateIntValueOfNextHighestDigit() {
        return powerOfType.equals(PowerOfType.TEN) ? intValue * 5 : intValue * 2;
    }

    static Integer parseStringToInt(final String value) {
        final RomanDigit romanDigit = parseString(value);
        return romanDigit != null ? romanDigit.getIntValue() : 0;
    }

    static RomanDigit parseString(final String value) {
        for(final RomanDigit digit : values()) {
            if(digit.getNumeralValue().equals(value)) {
                return digit;
            }
        }
        return null;
    }

    static RomanDigit[] valuesByDescendingOrder() {
        final RomanDigit[] descendingOrderValues = values();
        Arrays.sort(descendingOrderValues, (digit1, digit2) -> digit2.intValue - digit1.intValue);
        return descendingOrderValues;
    }
}