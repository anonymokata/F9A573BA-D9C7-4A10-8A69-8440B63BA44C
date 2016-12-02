import java.util.*;

public class RomanNumeralToIntConverter {

    public Optional<Integer> convertToInt(final String romanNumeral) {
        final List<String> splitNumeral = Arrays.asList(romanNumeral.split(""));
        final List<String> blockedNumeral = combineNumeralIntoBlocks(splitNumeral);

        if(individualBlocksAreInvalid(blockedNumeral)) {
            return Optional.empty();
        }

        return getSum(splitNumeral);
    }

    private boolean individualBlocksAreInvalid(final List<String> blockedNumeral) {
        boolean isValid = true;
        for(final String block : blockedNumeral) {
            if(block.length() == 0) {
                isValid = false;
            } else if(blockContainsOnlyOneTypeOfNumeral(block)) {
                isValid = checkIfSingleTypeValid(block);
            } else {
                isValid = block.length() == 2 && secondDigitGreaterThanFirst(block);
            }

            if(!isValid) {
                break;
            }
        }
        return !isValid;
    }

    private boolean secondDigitGreaterThanFirst(final String block) {
        final String firstDigit = block.substring(0, 1);
        final String secondDigit = block.substring(1);
        return RomanDigit.parseNumeral(secondDigit) > RomanDigit.parseNumeral(firstDigit);
    }

    private boolean checkIfSingleTypeValid(final String block) {
        final String firstDigit = getFirstDigit(block);
        if(RomanDigit.parseNumeral(firstDigit) == 0) {
            return false;
        } else if(RomanDigit.numeralIsPowerOfTen(firstDigit)) {
            return block.length() < 4;
        } else {
            return block.length() == 1;
        }
    }

    private boolean blockContainsOnlyOneTypeOfNumeral(final String block) {
        return block.length() == 1 || checkIfAllDigitsMatch(block);
    }

    private boolean checkIfAllDigitsMatch(final String block) {
        final String firstDigit = getFirstDigit(block);
        for(final String numeral : block.split("")) {
            if(!numeral.equals(firstDigit)) {
                return false;
            }
        }
        return true;
    }

    private String getFirstDigit(final String block) {
        return block.substring(0, 1);
    }

    private List<String> combineNumeralIntoBlocks(final List<String> splitNumeral) {
        final List<String> blockedNumeral = new ArrayList<>();

        String currentBlock = "";

        for(final String numeral : splitNumeral) {
            if(currentBlock.equals("")) {
                currentBlock = numeral;
            } else if(currentBlock.contains(numeral)) {
                currentBlock = currentBlock.concat(numeral);
            } else if(currentNumeralGreaterThanPrevious(numeral, currentBlock)) {
                currentBlock = currentBlock.concat(numeral);
            } else {
                blockedNumeral.add(currentBlock);
                currentBlock = numeral;
            }
        }
        blockedNumeral.add(currentBlock);
        return blockedNumeral;
    }

    private boolean currentNumeralGreaterThanPrevious(final String numeral, final String currentBlock) {
        final String lastNumeral = currentBlock.substring(currentBlock.length() - 1);
        final Integer parsedLastNumeral = RomanDigit.parseNumeral(lastNumeral);
        final Integer parsedNumeral = RomanDigit.parseNumeral(numeral);
        return parsedNumeral > parsedLastNumeral;
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