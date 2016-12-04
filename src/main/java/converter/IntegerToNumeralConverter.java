package converter;

public class IntegerToNumeralConverter {

    private static final String NUMBER_OUTSIDE_LEGAL_RANGE_MESSAGE = "Invalid result";

    String convert(int number) {
        if(number > 3999 || number < 1) {
            return NUMBER_OUTSIDE_LEGAL_RANGE_MESSAGE;
        }

        final StringBuilder numeralBuilder = new StringBuilder();

        for(final RomanDigit digit : RomanDigit.valuesByDescendingOrder()) {
            if(shouldSubtract(number, digit)) {
                number = appendSubtractiveNumeralsAndUpdateNumber(number, numeralBuilder, digit);
            } else {
                number = appendAdditiveNumeralsAndUpdateNumber(number, numeralBuilder, digit);
            }
        }

        return numeralBuilder.toString();
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

    private int appendSubtractiveNumeralsAndUpdateNumber(int number, final StringBuilder numeralBuilder, final RomanDigit digit) {
        final RomanDigit nextHighestDigit = digit.getNextHighestDigit();
        final RomanDigit subtrahend = getSubtrahend(digit, nextHighestDigit);

        numeralBuilder.append(subtrahend.getNumeralValue());
        numeralBuilder.append(nextHighestDigit.getNumeralValue());

        final int difference = nextHighestDigit.getIntValue() - subtrahend.getIntValue();
        number = number - difference;
        return number;
    }

    private RomanDigit getSubtrahend(final RomanDigit currentDigit, final RomanDigit nextHighestDigit) {
        if(isPowerOfTen(currentDigit)) {
            return nextHighestDigit.getNextLowestPowerOfTen();
        } else {
            return currentDigit.getNextLowestPowerOfTen();
        }
    }

    private int appendAdditiveNumeralsAndUpdateNumber(int number, final StringBuilder numeralBuilder, final RomanDigit digit) {
        int numberOfLetters = number / digit.getIntValue();
        number = number - (numberOfLetters * digit.getIntValue());

        while(numberOfLetters > 0) {
            numeralBuilder.append(digit.getNumeralValue());
            numberOfLetters--;
        }
        return number;
    }
}