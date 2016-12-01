import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class RomanNumeralToIntConverter {

    public Optional<Integer> convertToInt(final String romanNumeral) {
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
        final List<Integer> integers = new ArrayList<>();

        for (int index = 0; index < splitNumeral.size(); index++) {
            final String currentNumeral = splitNumeral.get(index);
            String nextNumeral = getNextNumeralIfPresent(splitNumeral, index);

            if(nextDigitsAreFour(currentNumeral, nextNumeral)) {
                integers.add(4);
                index = skipNextNumeral(index);
            } else if(nextDigitsAreNine(currentNumeral, nextNumeral)) {
                integers.add(9);
                index = skipNextNumeral(index);
            } else {
                final Integer integer = convertToInteger(currentNumeral);
                integers.add(integer);
            }
        }

        return integers;
    }

    private String getNextNumeralIfPresent(final List<String> splitNumeral, final int index) {
        String nextNumeral = null;
        if(nextNumeralExists(splitNumeral, index)) {
            nextNumeral = splitNumeral.get(index + 1);
        }
        return nextNumeral;
    }

    private boolean nextDigitsAreFour(final String currentNumeral, final String nextNumeral) {
        return RomanDigit.ONE.getNumeralValue().equals(currentNumeral) && RomanDigit.FIVE.getNumeralValue().equals(nextNumeral);
    }

    private boolean nextDigitsAreNine(final String currentNumeral, final String nextNumeral) {
        return RomanDigit.ONE.getNumeralValue().equals(currentNumeral) && RomanDigit.TEN.getNumeralValue().equals(nextNumeral);
    }

    private int skipNextNumeral(int index) {
        index++;
        return index;
    }

    private boolean nextNumeralExists(final List<String> splitNumeral, final int index) {
        return index + 1 < splitNumeral.size();
    }

    private Integer convertToInteger(final String value) {
        if(RomanDigit.ONE.getNumeralValue().equals(value)) {
            return 1;
        } else if(RomanDigit.FIVE.getNumeralValue().equals(value)){
            return 5;
        } else if(RomanDigit.TEN.getNumeralValue().equals(value)){
            return 10;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private Integer sum(final List<Integer> integers) {
        Integer sum = 0;
        for(Integer number : integers) {
            sum += number;
        }
        return sum;
    }
}