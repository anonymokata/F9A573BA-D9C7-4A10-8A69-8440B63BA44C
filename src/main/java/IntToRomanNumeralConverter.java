public class IntToRomanNumeralConverter {

    public String convertToRomanNumeral(int number) {
        if(number > 3999) {
            return "Invalid result";
        }

        String numeral = "";

        for(final RomanDigit digit : RomanDigit.valuesByDescendingOrder()) {
            if(shouldSubtract(number, digit)) {
                final RomanDigit nextLowestPowerOfTen = RomanDigit.getNextLowestPowerOfTen(digit);
                final RomanDigit nextHighestPowerOfTen = RomanDigit.getNextHighestDigit(digit);
                numeral = numeral.concat(nextLowestPowerOfTen.getNumeralValue() + nextHighestPowerOfTen.getNumeralValue());
                final int difference = nextHighestPowerOfTen.getIntValue() - nextLowestPowerOfTen.getIntValue();
                number = number - difference;
            } else {
                int numberOfLetters = number / digit.getIntValue();
                number = number - (numberOfLetters * digit.getIntValue());

                while(numberOfLetters > 0) {
                    numeral = numeral.concat(digit.getNumeralValue());
                    numberOfLetters--;
                }
            }
        }

        return numeral;
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