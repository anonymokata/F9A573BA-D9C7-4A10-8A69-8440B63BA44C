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
        final List<Integer> integers = convertNumeralsToIntegers(splitNumeral);
        final Integer sum = sum(integers);
        if (sum == 0) {
            return Optional.empty();
        }
        return Optional.of(sum);
    }

    private List<Integer> convertNumeralsToIntegers(final List<String> splitNumeral) {
        final List<Integer> integers = new ArrayList<>();

        for (int index = 0; index < splitNumeral.size(); index++) {
            final String currentNumeral = splitNumeral.get(index);
            final String nextNumeral = getNextNumeralIfPresent(splitNumeral, index);

            if (nextNumeralIsGreaterThanCurrent(currentNumeral, nextNumeral)) {
                final int smallerNumber = parseNumeral(currentNumeral);
                final int largerNumber = parseNumeral(nextNumeral);
                integers.add(largerNumber - smallerNumber);
                index = skipNextNumeral(index);
            } else {
                final Integer integer = parseNumeral(currentNumeral);
                integers.add(integer);
            }
        }

        return integers;
    }

    private boolean nextNumeralIsGreaterThanCurrent(final String currentNumeral, final String nextNumeral) {
        final int difference = parseNumeral(nextNumeral) - parseNumeral(currentNumeral);
        return difference > 0;
    }

    private String getNextNumeralIfPresent(final List<String> splitNumeral, final int index) {
        String nextNumeral = null;
        if (nextNumeralExists(splitNumeral, index)) {
            nextNumeral = splitNumeral.get(index + 1);
        }
        return nextNumeral;
    }

    private int skipNextNumeral(int index) {
        index++;
        return index;
    }

    private boolean nextNumeralExists(final List<String> splitNumeral, final int index) {
        return index + 1 < splitNumeral.size();
    }

    private Integer parseNumeral(final String value) {
        if (RomanDigit.ONE.getNumeralValue().equals(value)) {
            return 1;
        } else if (RomanDigit.FIVE.getNumeralValue().equals(value)) {
            return 5;
        } else if (RomanDigit.TEN.getNumeralValue().equals(value)) {
            return 10;
        } else {
            return 0;
        }
    }

    private Integer sum(final List<Integer> integers) {
        return integers.stream().mapToInt(Integer::intValue).sum();
    }
}