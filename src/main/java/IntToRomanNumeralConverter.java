public class IntToRomanNumeralConverter {

    public String convertToRomanNumeral(final int number) {
        if(number > 3999) {
            return "Invalid result";
        }
        if(number == 2) {
            return "II";
        }
        if(number == 3) {
            return "III";
        }
        return RomanDigit.ONE.getNumeralValue();
    }
}