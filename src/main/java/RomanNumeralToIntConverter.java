import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RomanNumeralToIntConverter {

    private static final Function<String, Integer> NUMERAL_TO_INT_MAPPER = value -> {
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

    private Optional<Integer> getSum(final List<String> splitNumeral) {
        try {
            final List<Integer> integers = convertNumeralsToIntegers(splitNumeral);
            final Integer sum = sum(integers);
            return Optional.of(sum);
        } catch(final IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    private List<Integer> convertNumeralsToIntegers(final List<String> splitNumeral) {
        return splitNumeral.stream().map(NUMERAL_TO_INT_MAPPER).collect(Collectors.toList());
    }

    private Integer sum(final List<Integer> integers) {
        Integer sum = 0;
        for(Integer number : integers) {
            sum += number;
        }
        return sum;
    }
}