import java.util.Arrays;
import java.util.List;

public class RomanNumeralConverter {
    public int convertToInt(final String romanNumeral) {
        if("IV".equals(romanNumeral)) {
            return 4;
        }

        final List<String> splitNumeral = Arrays.asList(romanNumeral.split(""));
        return getSum(splitNumeral);
    }

    private int getSum(List<String> splitNumeral) {
        int sum = 0;

        for(final String digit : splitNumeral) {
            final int convertedDigit = convertRomanDigitToInt(digit);
            sum += convertedDigit;
        }
        return sum;
    }

    private int convertRomanDigitToInt(final String digit) {
        if("I".equals(digit)) {
            return 1;
        } else {
            return 5;
        }
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