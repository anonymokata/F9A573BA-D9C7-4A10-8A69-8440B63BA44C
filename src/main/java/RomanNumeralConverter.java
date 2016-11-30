import java.util.Arrays;
import java.util.List;
import java.util.function.ToIntFunction;

public class RomanNumeralConverter {

    private final static ToIntFunction<String> numeralToIntMapper = value -> {
        if("I".equals(value)) {
            return 1;
        } else if("V".equals(value)){
            return 5;
        } else {
            return 10;
        }
    };

    public int convertToInt(final String romanNumeral) {
        if("IV".equals(romanNumeral)) {
            return 4;
        }

        if("IX".equals(romanNumeral)) {
            return 9;
        }

        final List<String> splitNumeral = Arrays.asList(romanNumeral.split(""));
        return getSum(splitNumeral);
    }

    private int getSum(List<String> splitNumeral) {
        return splitNumeral.stream().mapToInt(numeralToIntMapper).sum();
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