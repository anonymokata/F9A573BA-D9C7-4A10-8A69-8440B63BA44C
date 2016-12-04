public class IntToRomanNumeralConverter {

    public String convertToRomanNumeral(int number) {
        if(number > 3999) {
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
        final RomanDigit nextLowestPowerOfTen = RomanDigit.getNextLowestPowerOfTen(digit);
        final RomanDigit nextHighestPowerOfTen = RomanDigit.getNextHighestDigit(digit);

        numeralBuilder.append(nextLowestPowerOfTen.getNumeralValue());
        numeralBuilder.append(nextHighestPowerOfTen.getNumeralValue());

        final int difference = nextHighestPowerOfTen.getIntValue() - nextLowestPowerOfTen.getIntValue();
        number = number - difference;
        return number;
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
        final RomanDigit potentialSubtractingDigit = RomanDigit.getNextLowestPowerOfTen(digit);
        int numberToCompare = potentialSubtractingDigit.getIntValue() * 4;
        numberToCompare = addCurrentDigitValueIfPowerOfFive(digit, numberToCompare);
        return numberToCompare == number;
    }

    private int addCurrentDigitValueIfPowerOfFive(final RomanDigit digit, int numberToCompare) {
        if(!RomanDigit.numeralIsPowerOfTen(digit.getNumeralValue())) {
            numberToCompare += digit.getIntValue();
        }
        return numberToCompare;
    }
}