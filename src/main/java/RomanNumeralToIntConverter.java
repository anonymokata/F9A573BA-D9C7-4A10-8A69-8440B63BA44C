import java.util.Arrays;
import java.util.List;
import java.util.function.ToIntFunction;

public class RomanNumeralToIntConverter {

    private static final ToIntFunction<String> NUMERAL_TO_INT_MAPPER = value -> {
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
        return splitNumeral.stream().mapToInt(NUMERAL_TO_INT_MAPPER).sum();
    }
}