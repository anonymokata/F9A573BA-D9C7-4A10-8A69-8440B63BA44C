package converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class NumeralToIntegerConverter {

    private static final String EMPTY_STRING = "";
    private static final int MAX_REPETITIONS_FOR_POWER_OF_TEN = 3;
    private static final int MAX_REPETITIONS_FOR_POWER_OF_FIVE = 1;

    Optional<Integer> convert(final String numeral) {
        final List<String> splitNumeral = Arrays.asList(numeral.split(EMPTY_STRING));
        final List<String> blockedNumeral = createNumeralBlocks(splitNumeral);

        if (isValid(blockedNumeral)) {
            return Optional.of(sum(blockedNumeral));
        }

        return Optional.empty();
    }

    private List<String> createNumeralBlocks(final List<String> splitNumeral) {
        final List<String> blockedNumeral = new ArrayList<>();
        StringBuilder blockBuilder = new StringBuilder();

        for (final String numeral : splitNumeral) {
            if (blockBuilder.length() == 0) {
                blockBuilder = startNewBlock(numeral);
            } else if (shouldAddNumeralToCurrentBlock(blockBuilder.toString(), numeral)) {
                blockBuilder.append(numeral);
            } else {
                addCurrentBlockToList(blockedNumeral, blockBuilder);
                blockBuilder = startNewBlock(numeral);
            }
        }

        addCurrentBlockToList(blockedNumeral, blockBuilder);
        return blockedNumeral;
    }

    private StringBuilder startNewBlock(final String numeral) {
        return new StringBuilder().append(numeral);
    }

    private boolean shouldAddNumeralToCurrentBlock(final String currentBlock, final String numeral) {
        return currentBlock.contains(numeral) || currentNumeralGreaterThanPrevious(numeral, currentBlock);
    }

    private boolean currentNumeralGreaterThanPrevious(final String numeral, final String currentBlock) {
        final String lastNumeralAdded = getLastNumeralAdded(currentBlock);
        return RomanDigit.parseStringToInt(numeral) > RomanDigit.parseStringToInt(lastNumeralAdded);
    }

    private String getLastNumeralAdded(final String currentBlock) {
        return currentBlock.substring(currentBlock.length() - 1);
    }

    private void addCurrentBlockToList(final List<String> blockedNumeral, final StringBuilder blockBuilder) {
        blockedNumeral.add(blockBuilder.toString());
    }

    private boolean isValid(final List<String> blockedNumeral) {
        return blockedNumeral
                .stream()
                .map(this::isValidBlock)
                .allMatch(isValid -> isValid);
    }

    private boolean isValidBlock(final String block) {
        return block.length() > 0 && isValidBlockForType(block);
    }

    private boolean isValidBlockForType(final String block) {
        if (blockContainsOnlyOneTypeOfNumeral(block)) {
            return isValidAdditiveBlock(block);
        } else {
            return isValidSubtractiveBlock(block);
        }
    }

    private boolean blockContainsOnlyOneTypeOfNumeral(final String block) {
        return block.length() == 1 || checkIfAllDigitsMatch(block);
    }

    private boolean checkIfAllDigitsMatch(final String block) {
        final RomanDigit firstDigit = getFirstDigit(block);
        for (final String numeral : block.split(EMPTY_STRING)) {
            if (doesNotMatchFirstDigit(numeral, firstDigit)) {
                return false;
            }
        }
        return true;
    }

    private boolean doesNotMatchFirstDigit(final String numeral, final RomanDigit firstDigit) {
        return firstDigit != null && !numeral.equals(firstDigit.getNumeralValue());
    }

    private boolean isValidAdditiveBlock(final String block) {
        final RomanDigit firstDigit = getFirstDigit(block);
        return firstDigit != null && hasValidNumberOfRepetitions(firstDigit, block);
    }

    private RomanDigit getFirstDigit(final String block) {
        final String firstDigitString = block.substring(0, 1);
        return RomanDigit.parseString(firstDigitString);
    }

    private boolean hasValidNumberOfRepetitions(final RomanDigit firstDigit, final String block) {
        if (firstDigit.getPowerOfType().equals(PowerOfType.TEN)) {
            return block.length() <= MAX_REPETITIONS_FOR_POWER_OF_TEN;
        } else {
            return block.length() == MAX_REPETITIONS_FOR_POWER_OF_FIVE;
        }
    }

    private boolean isValidSubtractiveBlock(final String block) {
        return block.length() == 2 && secondDigitIsValid(block);
    }

    private boolean secondDigitIsValid(final String block) {
        final Integer firstDigitAsInteger = RomanDigit.parseStringToInt(block.substring(0, 1));
        final Integer secondDigitAsInteger = RomanDigit.parseStringToInt(block.substring(1));
        return secondDigitIsValidFollower(firstDigitAsInteger, secondDigitAsInteger);
    }

    private boolean secondDigitIsValidFollower(final Integer firstInteger, final Integer secondInteger) {
        final int quotient = secondInteger / firstInteger;
        return quotient == 5 || quotient == 10;
    }

    private int sum(final List<String> blockedNumeral) {
        return blockedNumeral
                .stream()
                .mapToInt(this::getBlockSum)
                .sum();
    }

    private int getBlockSum(final String block) {
        final List<String> splitBlock = Arrays.asList(block.split(EMPTY_STRING));
        int blockSum = 0;

        for (int index = 0; index < splitBlock.size(); index++) {
            final String currentNumeral = splitBlock.get(index);
            final String nextNumeral = getNextNumeralIfPresent(splitBlock, index + 1);

            if (nextNumeralIsGreaterThanCurrent(currentNumeral, nextNumeral)) {
                final Integer result = subtract(nextNumeral, currentNumeral);
                blockSum += result;
                break;
            } else {
                blockSum += RomanDigit.parseStringToInt(currentNumeral);
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
        final int difference = RomanDigit.parseStringToInt(nextNumeral) - RomanDigit.parseStringToInt(currentNumeral);
        return difference > 0;
    }

    private Integer subtract(final String nextNumeral, final String currentNumeral) {
        final int smallerNumber = RomanDigit.parseStringToInt(currentNumeral);
        final int largerNumber = RomanDigit.parseStringToInt(nextNumeral);
        return largerNumber - smallerNumber;
    }

}