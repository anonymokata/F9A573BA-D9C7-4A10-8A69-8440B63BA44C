package converter;

import java.util.Arrays;
import java.util.function.Function;

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
        final RomanDigit nextLowestPowerOfTen = findDigitMatchingIntValue(intValueOfNextTenPower);
        return nextLowestPowerOfTen != null ? nextLowestPowerOfTen : ONE;
    }

    private int calculateIntValueOfNextLowestPowerOfTen() {
        return powerOfType.equals(PowerOfType.TEN) ? intValue / 10 : intValue / 5;
    }

    RomanDigit getNextHighestDigit() {
        final int intValueOfNextHighest = calculateIntValueOfNextHighestDigit();
        final RomanDigit nextHighest = findDigitMatchingIntValue(intValueOfNextHighest);
        return nextHighest != null ? nextHighest : ONE_THOUSAND;
    }

    private int calculateIntValueOfNextHighestDigit() {
        return powerOfType.equals(PowerOfType.TEN) ? intValue * 5 : intValue * 2;
    }

    private RomanDigit findDigitMatchingIntValue(final int intValue) {
        return findDigit(digit -> digit.getIntValue() == intValue);
    }

    static Integer parseStringToInt(final String value) {
        final RomanDigit romanDigit = parseString(value);
        return romanDigit != null ? romanDigit.getIntValue() : 0;
    }

    static RomanDigit parseString(final String value) {
        return findDigit(digit -> digit.getNumeralValue().equals(value));
    }

    private static RomanDigit findDigit(final Function<RomanDigit, Boolean> criteria) {
        for(final RomanDigit digit : values()) {
            if(criteria.apply(digit)) {
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