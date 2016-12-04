package converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class NumeralToIntegerConverter {

    private static final String EMPTY_STRING = "";

    Optional<Integer> convert(final String numeral) {
        final List<String> splitNumeral = Arrays.asList(numeral.split(EMPTY_STRING));
        final List<String> blockedNumeral = combineNumeralIntoBlocks(splitNumeral);

        if (individualBlocksAreInvalid(blockedNumeral)) {
            return Optional.empty();
        }

        return getSum(blockedNumeral);
    }

    private List<String> combineNumeralIntoBlocks(final List<String> splitNumeral) {
        final List<String> blockedNumeral = new ArrayList<>();

        String currentBlock = EMPTY_STRING;

        for (final String numeral : splitNumeral) {
            if (currentBlock.equals(EMPTY_STRING)) {
                currentBlock = numeral;
            } else if (shouldAddCurrentNumeralToBlock(currentBlock, numeral)) {
                currentBlock = currentBlock.concat(numeral);
            } else {
                blockedNumeral.add(currentBlock);
                currentBlock = numeral;
            }
        }
        blockedNumeral.add(currentBlock);
        return blockedNumeral;
    }

    private boolean shouldAddCurrentNumeralToBlock(final String currentBlock, final String numeral) {
        return currentBlock.contains(numeral) || currentNumeralGreaterThanPrevious(numeral, currentBlock);
    }

    private boolean currentNumeralGreaterThanPrevious(final String numeral, final String currentBlock) {
        final String previousNumeral = currentBlock.substring(currentBlock.length() - 1);
        final Integer parsedPreviousNumeral = RomanDigit.parseNumeral(previousNumeral);
        final Integer parsedCurrentNumeral = RomanDigit.parseNumeral(numeral);
        return parsedCurrentNumeral > parsedPreviousNumeral;
    }

    private boolean individualBlocksAreInvalid(final List<String> blockedNumeral) {
        boolean isValid = true;
        for (final String block : blockedNumeral) {
            if (block.length() == 0) {
                isValid = false;
            } else if (blockContainsOnlyOneTypeOfNumeral(block)) {
                isValid = checkIfSingleTypeValid(block);
            } else {
                isValid = block.length() == 2 && secondDigitIsValid(block);
            }

            if (!isValid) {
                break;
            }
        }
        return !isValid;
    }

    private boolean blockContainsOnlyOneTypeOfNumeral(final String block) {
        return block.length() == 1 || checkIfAllDigitsMatch(block);
    }

    private boolean checkIfAllDigitsMatch(final String block) {
        final String firstDigit = getFirstDigit(block);
        for (final String numeral : block.split(EMPTY_STRING)) {
            if (!numeral.equals(firstDigit)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkIfSingleTypeValid(final String block) {
        final String firstDigit = getFirstDigit(block);
        if (RomanDigit.parseNumeral(firstDigit) == 0) {
            return false;
        } else if (RomanDigit.numeralIsPowerOfTen(firstDigit)) {
            return block.length() < 4;
        } else {
            return block.length() == 1;
        }
    }

    private String getFirstDigit(final String block) {
        return block.substring(0, 1);
    }

    private boolean secondDigitIsValid(final String block) {
        final String firstDigit = block.substring(0, 1);
        final String secondDigit = block.substring(1);
        final Integer firstInteger = RomanDigit.parseNumeral(firstDigit);
        final Integer secondInteger = RomanDigit.parseNumeral(secondDigit);
        return secondInteger > firstInteger && secondDigitIsValidFollower(firstInteger, secondInteger);
    }

    private boolean secondDigitIsValidFollower(final Integer firstInteger, final Integer secondInteger) {
        final int quotient = secondInteger / firstInteger;
        return quotient == 5 || quotient == 10;
    }

    private Optional<Integer> getSum(final List<String> blockedNumeral) {
        final List<Integer> integers = convertNumeralsToIntegers(blockedNumeral);
        final Integer sum = sum(integers);
        return Optional.of(sum);
    }

    private List<Integer> convertNumeralsToIntegers(final List<String> blockedNumeral) {
        final List<Integer> integers = new ArrayList<>();

        for (final String block : blockedNumeral) {
            final List<String> splitBlock = Arrays.asList(block.split(EMPTY_STRING));
            final int blockSum = getBlockSum(splitBlock);
            integers.add(blockSum);
        }

        return integers;
    }

    private int getBlockSum(final List<String> splitBlock) {
        int blockSum = 0;

        for (int index = 0; index < splitBlock.size(); index++) {
            final String currentNumeral = splitBlock.get(index);
            final String nextNumeral = getNextNumeralIfPresent(splitBlock, index + 1);

            if (nextNumeralIsGreaterThanCurrent(currentNumeral, nextNumeral)) {
                final Integer result = subtract(nextNumeral, currentNumeral);
                blockSum += result;
                break;
            } else {
                blockSum += RomanDigit.parseNumeral(currentNumeral);
            }
        }
        return blockSum;
    }

    private String getNextNumeralIfPresent(final List<String> splitNumeral, final int nextNumeralIndex) {
        String nextNumeral = null;
        if (nextNumeralExists(splitNumeral, nextNumeralIndex)) {
            nextNumeral = splitNumeral.get(nextNumeralIndex);
        }
        return nextNumeral;
    }

    private boolean nextNumeralExists(final List<String> splitNumeral, final int nextNumeralIndex) {
        return nextNumeralIndex < splitNumeral.size();
    }

    private boolean nextNumeralIsGreaterThanCurrent(final String currentNumeral, final String nextNumeral) {
        final int difference = RomanDigit.parseNumeral(nextNumeral) - RomanDigit.parseNumeral(currentNumeral);
        return difference > 0;
    }

    private Integer subtract(final String nextNumeral, final String currentNumeral) {
        final int smallerNumber = RomanDigit.parseNumeral(currentNumeral);
        final int largerNumber = RomanDigit.parseNumeral(nextNumeral);
        return largerNumber - smallerNumber;
    }

    private Integer sum(final List<Integer> integers) {
        return integers.stream().mapToInt(Integer::intValue).sum();
    }
}