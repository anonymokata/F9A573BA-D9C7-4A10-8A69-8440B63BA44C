public class IntToRomanNumeralConverter {

    public String convertToRomanNumeral(int number) {
        if(number > 3999 || number < 1) {
            return "Invalid result";
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

    private int appendSubtractiveNumeralsAndUpdateNumber(int number, final StringBuilder numeralBuilder, final RomanDigit digit) {
        final RomanDigit nextHighestDigit = RomanDigit.getNextHighestDigit(digit);
        final RomanDigit subtrahend = getSubtrahend(digit, nextHighestDigit);

        numeralBuilder.append(subtrahend.getNumeralValue());
        numeralBuilder.append(nextHighestDigit.getNumeralValue());

        final int difference = nextHighestDigit.getIntValue() - subtrahend.getIntValue();
        number = number - difference;
        return number;
    }

    private RomanDigit getSubtrahend(final RomanDigit currentDigit, final RomanDigit nextHighestDigit) {
        if(RomanDigit.numeralIsPowerOfTen(currentDigit.getNumeralValue())) {
            return RomanDigit.getNextLowestPowerOfTen(nextHighestDigit);
        } else {
            return RomanDigit.getNextLowestPowerOfTen(currentDigit);
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

    private boolean shouldSubtract(final int number, final RomanDigit digit) {
        return number > digit.getIntValue() && requiresSubtractionToRepresent(number, digit);
    }

    private boolean requiresSubtractionToRepresent(final int number, final RomanDigit digit) {
        final int roundedNumber = roundNumber(number, digit);
        final int numberToCompare = getNumberToCompareAgainst(digit);
        return numberToCompare == roundedNumber;
    }

    private int roundNumber(final int number, final RomanDigit digit) {
        if(RomanDigit.numeralIsPowerOfTen(digit.getNumeralValue())) {
            return (number / digit.getIntValue()) * digit.getIntValue();
        } else {
            final RomanDigit nextLowestPowerOfTen = RomanDigit.getNextLowestPowerOfTen(digit);
            return (number / nextLowestPowerOfTen.getIntValue()) * nextLowestPowerOfTen.getIntValue();
        }
    }

    private int getNumberToCompareAgainst(final RomanDigit digit) {
        if(RomanDigit.numeralIsPowerOfTen(digit.getNumeralValue())) {
            return digit.getIntValue() * 4;
        } else {
            final RomanDigit potentialSubtractingDigit = RomanDigit.getNextLowestPowerOfTen(digit);
            final int numberToCompare = potentialSubtractingDigit.getIntValue() * 4;
            return numberToCompare + digit.getIntValue();
        }
    }
}