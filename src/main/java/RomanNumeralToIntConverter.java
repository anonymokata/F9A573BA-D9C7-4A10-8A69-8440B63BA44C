import java.util.*;

public class RomanNumeralToIntConverter {

    public Optional<Integer> convertToInt(final String romanNumeral) {
        final List<String> splitNumeral = Arrays.asList(romanNumeral.split(""));
        if (invalidNumeral(splitNumeral)) {
            return Optional.empty();
        }
        return getSum(splitNumeral);
    }

    private boolean invalidNumeral(final List<String> splitNumeral) {
        for(int index = 0; index < splitNumeral.size(); index++) {
            final String currentNumeral = splitNumeral.get(index);
            final int nextNumeralIndex = index + 1;
            final String nextNumeral = getNextNumeralIfPresent(splitNumeral, nextNumeralIndex);

            if(currentNumeralRepeatedTooMuch(currentNumeral, splitNumeral)) {
                return true;
            }

            final boolean subtractiveNumeral = nextNumeralIsGreaterThanCurrent(currentNumeral, nextNumeral);
            final boolean largerNumeralAppearsAgain = largerNumeralAppearsAgain(nextNumeral, nextNumeralIndex, splitNumeral);
            if(subtractiveNumeral && largerNumeralAppearsAgain) {
                return true;
            }
        }
        return false;
    }

    private boolean currentNumeralRepeatedTooMuch(final String currentNumeral, final List<String> splitNumeral) {
        final long count = splitNumeral.stream().filter(numeral -> numeral.equals(currentNumeral)).count();
        if(RomanDigit.numeralIsPowerOfTen(currentNumeral)) {
            return count > 3;
        } else {
            return count > 1;
        }
    }

    private boolean largerNumeralAppearsAgain(final String largerNumeral, final int originalIndex, final List<String> splitNumeral) {
        final int lastIndexOf = findLastIndexOf(largerNumeral, splitNumeral);
        return lastIndexOf > originalIndex;
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
            final String nextNumeral = getNextNumeralIfPresent(splitNumeral, index + 1);

            if (nextNumeralIsGreaterThanCurrent(currentNumeral, nextNumeral)) {
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
            if(numeral.equals(nextNumeral)) {
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

    private String getNextNumeralIfPresent(final List<String> splitNumeral, final int nextNumeralIndex) {
        String nextNumeral = null;
        if (nextNumeralExists(splitNumeral, nextNumeralIndex)) {
            nextNumeral = splitNumeral.get(nextNumeralIndex);
        }
        return nextNumeral;
    }

    private int skipNextNumeral(int index) {
        index++;
        return index;
    }

    private boolean nextNumeralExists(final List<String> splitNumeral, final int nextNumeralIndex) {
        return nextNumeralIndex < splitNumeral.size();
    }

    private Integer sum(final List<Integer> integers) {
        return integers.stream().mapToInt(Integer::intValue).sum();
    }
}