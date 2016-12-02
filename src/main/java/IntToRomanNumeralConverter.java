public class IntToRomanNumeralConverter {

    public String convertToRomanNumeral(int number) {
        if(number > 3999) {
            return "Invalid result";
        }

        String numeral = "";

        while(number > 0) {
            numeral = numeral.concat(RomanDigit.ONE.getNumeralValue());
            number--;
        }

        return numeral;
    }
}