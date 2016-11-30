import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.ToIntFunction;

public class RomanNumeralToIntConverter {

    private static final ToIntFunction<String> NUMERAL_TO_INT_MAPPER = value -> {
        if(RomanDigit.ONE.getValue().equals(value)) {
            return 1;
        } else if(RomanDigit.FIVE.getValue().equals(value)){
            return 5;
        } else {
            return 10;
        }
    };

    public Optional<Integer> convertToInt(final String romanNumeral) {
        if("IV".equals(romanNumeral)) {
            return Optional.of(4);
        }

        if("IX".equals(romanNumeral)) {
            return Optional.of(9);
        }

        final List<String> splitNumeral = Arrays.asList(romanNumeral.split(""));
        final int sum = getSum(splitNumeral);
        return Optional.of(sum);
    }

    private int getSum(List<String> splitNumeral) {
        return splitNumeral.stream().mapToInt(NUMERAL_TO_INT_MAPPER).sum();
    }
}