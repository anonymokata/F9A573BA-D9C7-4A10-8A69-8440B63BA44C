package converter;

public class IntegerToNumeralConverter {

    private static final String NUMBER_OUTSIDE_ALLOWED_RANGE_MESSAGE = "Calculation result is outside valid range";

    String convert(final int number) {
        if(numberIsOutsideAllowedRange(number)) {
            return NUMBER_OUTSIDE_ALLOWED_RANGE_MESSAGE;
        }
        return convertToNumeral(number);
    }

    private String convertToNumeral(int number) {
        final StringBuilder numeralBuilder = new StringBuilder();

        for(final RomanDigit digit : RomanDigit.valuesByDescendingOrder()) {
            if(shouldSubtract(number, digit)) {
                appendSubtractiveNumerals(numeralBuilder, digit);
                number = updateNumberForSubtractiveNumerals(number, digit);
            } else {
                appendAdditiveNumerals(numeralBuilder, digit, number);
                number = updateNumberForAdditiveNumerals(number, digit);
            }
        }

        return numeralBuilder.toString();
    }

    private boolean numberIsOutsideAllowedRange(final int number) {
        return number > 3999 || number < 1;
    }

    private boolean shouldSubtract(final int number, final RomanDigit digit) {
        final int roundedNumber = roundNumberToIgnoreDigitsBeyondLargestPlace(number, digit);
        final int closestNumberWhichRequiresSubtraction = findClosestNumberWhichRequiresSubtraction(digit);
        return closestNumberWhichRequiresSubtraction == roundedNumber;
    }

    private boolean isPowerOfTen(final RomanDigit digit) {
        return digit.getPowerOfType().equals(PowerOfType.TEN);
    }

    private int roundNumberToIgnoreDigitsBeyondLargestPlace(final int number, final RomanDigit currentDigit) {
        if(isPowerOfTen(currentDigit)) {
            return roundNumberUsing(currentDigit, number);
        } else {
            return roundNumberUsing(currentDigit.getNextLowestPowerOfTen(), number);
        }
    }

    private int roundNumberUsing(final RomanDigit digit, final int number) {
        return (number / digit.getIntValue()) * digit.getIntValue();
    }

    private int findClosestNumberWhichRequiresSubtraction(final RomanDigit digit) {
        if(isPowerOfTen(digit)) {
            return calculateValueToRepresentMultipleOf4(digit);
        } else {
            return calculateValueToRepresentMultipleOf9(digit);
        }
    }

    private int calculateValueToRepresentMultipleOf4(final RomanDigit digit) {
        return digit.getIntValue() * 4;
    }

    private int calculateValueToRepresentMultipleOf9(final RomanDigit digit) {
        final RomanDigit nextLowestPowerOfTen = digit.getNextLowestPowerOfTen();
        final int multipleOf4Value = calculateValueToRepresentMultipleOf4(nextLowestPowerOfTen);
        return multipleOf4Value + digit.getIntValue();
    }

    private void appendSubtractiveNumerals(final StringBuilder numeralBuilder, final RomanDigit digit) {
        final RomanDigit nextHighestDigit = digit.getNextHighestDigit();
        final RomanDigit subtractingDigit = getSubtractingDigit(digit);
        numeralBuilder.append(subtractingDigit.getNumeralValue());
        numeralBuilder.append(nextHighestDigit.getNumeralValue());
    }

    private int updateNumberForSubtractiveNumerals(final int number, final RomanDigit digit) {
        final RomanDigit nextHighestDigit = digit.getNextHighestDigit();
        final RomanDigit subtractingDigit = getSubtractingDigit(digit);
        final int difference = nextHighestDigit.getIntValue() - subtractingDigit.getIntValue();
        return number - difference;
    }

    private RomanDigit getSubtractingDigit(final RomanDigit currentDigit) {
        if(isPowerOfTen(currentDigit)) {
            return currentDigit;
        } else {
            return currentDigit.getNextLowestPowerOfTen();
        }
    }

    private void appendAdditiveNumerals(final StringBuilder numeralBuilder, final RomanDigit digit, final int number) {
        int numberOfTimesToAdd = calculateNumberOfTimesToAdd(number, digit);

        while(numberOfTimesToAdd > 0) {
            numeralBuilder.append(digit.getNumeralValue());
            numberOfTimesToAdd--;
        }
    }

    private int calculateNumberOfTimesToAdd(final int number, final RomanDigit digit) {
        return number / digit.getIntValue();
    }

    private int updateNumberForAdditiveNumerals(final int number, final RomanDigit digit) {
        final int numberOfLetters = calculateNumberOfTimesToAdd(number, digit);
        return number - (numberOfLetters * digit.getIntValue());
    }
}