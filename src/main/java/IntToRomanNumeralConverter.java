public class IntToRomanNumeralConverter {

    public String convertToRomanNumeral(final int number) {
        if(number > 3999) {
            return "Invalid result";
        }

        int numberOfFives = number > 4 ? 1 : 0;
        int numberOfOnes = number - (numberOfFives * 5);

        String numeral = "";

        while(numberOfFives > 0) {
            numeral = numeral.concat(RomanDigit.FIVE.getNumeralValue());
            numberOfFives--;
        }

        while(numberOfOnes > 0) {
            numeral = numeral.concat(RomanDigit.ONE.getNumeralValue());
            numberOfOnes--;
        }

        return numeral;
    }
}