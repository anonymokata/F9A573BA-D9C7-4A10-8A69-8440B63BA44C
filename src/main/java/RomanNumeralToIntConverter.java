import java.util.*;

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
        List<Integer> integers = new ArrayList<>();

        for (int index = 0; index < splitNumeral.size(); index++) {
            final String currentNumeral = splitNumeral.get(index);
            final String nextNumeral = getNextNumeralIfPresent(splitNumeral, index);

            if (nextNumeralIsGreaterThanCurrent(currentNumeral, nextNumeral)) {
                final int lastIndex = findLastIndexOf(nextNumeral, splitNumeral);
                if(lastIndex > index + 1) {
                    integers = Collections.emptyList();
                    break;
                }
                addDifferenceOfNumeralsToList(integers, currentNumeral, nextNumeral);
                index = skipNextNumeral(index);
            } else {
                final Integer integer = RomanDigit.parseNumeral(currentNumeral);
                integers.add(integer);
            }
        }

        return integers;
    }

    private int findLastIndexOf(final String nextNumeral, final List<String> splitNumeral) {
        int lastIndex = 0;
        for(int index = 0; index < splitNumeral.size(); index++) {
            final String numeral = splitNumeral.get(index);
            if(nextNumeral.equals(numeral)) {
                lastIndex = index;
            }
        }
        return lastIndex;
    }

    private void addDifferenceOfNumeralsToList(final List<Integer> integers, final String currentNumeral, final String nextNumeral) {
        final int smallerNumber = RomanDigit.parseNumeral(currentNumeral);
        final int largerNumber = RomanDigit.parseNumeral(nextNumeral);
        integers.add(largerNumber - smallerNumber);
    }

    private boolean nextNumeralIsGreaterThanCurrent(final String currentNumeral, final String nextNumeral) {
        final int difference = RomanDigit.parseNumeral(nextNumeral) - RomanDigit.parseNumeral(currentNumeral);
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

    private Integer sum(final List<Integer> integers) {
        return integers.stream().mapToInt(Integer::intValue).sum();
    }
}