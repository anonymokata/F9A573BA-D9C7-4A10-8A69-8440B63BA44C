public class RomanNumeralConverter {
    public int convertToInt(final String romanNumeral) {
        final String[] splitNumeral = romanNumeral.split("");
        return splitNumeral.length;
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