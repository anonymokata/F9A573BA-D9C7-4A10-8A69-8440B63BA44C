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
        } else if(RomanDigit.TEN.getValue().equals(value)){
            return 10;
        } else {
            throw new IllegalArgumentException();
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
        return getSum(splitNumeral);
    }

    private Optional<Integer> getSum(List<String> splitNumeral) {
        try {
            final int sum = splitNumeral.stream().mapToInt(NUMERAL_TO_INT_MAPPER).sum();
            return Optional.of(sum);
        } catch(final IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}