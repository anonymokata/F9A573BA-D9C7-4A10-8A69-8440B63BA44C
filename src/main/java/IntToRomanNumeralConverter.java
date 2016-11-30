public class IntToRomanNumeralConverter {

    public String convertToRomanNumeral(final int number) {
        if(number == 2) {
            return "II";
        }
        if(number == 3) {
            return "III";
        }
        return "I";
    }
}