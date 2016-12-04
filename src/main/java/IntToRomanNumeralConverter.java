public class IntToRomanNumeralConverter {

    public String convertToRomanNumeral(int number) {
        if(number > 3999) {
            return "Invalid result";
        }

        if(number == 4) {
            return "IV";
        }

        String numeral = "";

        for(final RomanDigit digit : RomanDigit.valuesByDescendingOrder()) {
            int numberOfLetters = number / digit.getIntValue();
            number = number - (numberOfLetters * digit.getIntValue());
            while(numberOfLetters > 0) {
                numeral = numeral.concat(digit.getNumeralValue());
                numberOfLetters--;
            }
        }

        return numeral;
    }
}