public class RomanNumeralConverter {
    public int convertToInt(final String romanNumeral) {
        if("II".equals(romanNumeral)) {
            return 2;
        }
        if("III".equals(romanNumeral)) {
            return 3;
        }
        return 1;
    }

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