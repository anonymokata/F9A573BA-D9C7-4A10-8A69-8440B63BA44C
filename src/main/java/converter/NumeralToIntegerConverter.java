package converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class NumeralToIntegerConverter {

    private static final String EMPTY_STRING = "";

    Optional<Integer> convert(final String numeral) {
        final List<String> splitNumeral = Arrays.asList(numeral.split(EMPTY_STRING));
        final List<String> blockedNumeral = createNumeralBlocks(splitNumeral);

        if (isValid(blockedNumeral)) {
            return getSum(blockedNumeral);
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
        if (block.length() == 0) {
            return false;
        } else if (blockContainsOnlyOneTypeOfNumeral(block)) {
            return checkIfSingleTypeValid(block);
        } else {
            return block.length() == 2 && secondDigitIsValid(block);
        }
    }

    private boolean blockContainsOnlyOneTypeOfNumeral(final String block) {
        return block.length() == 1 || checkIfAllDigitsMatch(block);
    }

    private boolean checkIfAllDigitsMatch(final String block) {
        final RomanDigit firstDigit = getFirstDigit(block);
        for (final String numeral : block.split(EMPTY_STRING)) {
            if (firstDigit != null && !numeral.equals(firstDigit.getNumeralValue())) {
                return false;
            }
        }
        return true;
    }

    private boolean checkIfSingleTypeValid(final String block) {
        final RomanDigit firstDigit = getFirstDigit(block);
        if (firstDigit == null) {
            return false;
        } else if (firstDigit.getPowerOfType().equals(PowerOfType.TEN)) {
            return block.length() < 4;
        } else {
            return block.length() == 1;
        }
    }

    private RomanDigit getFirstDigit(final String block) {
        final String firstDigitString = block.substring(0, 1);
        return RomanDigit.parseString(firstDigitString);
    }

    private boolean secondDigitIsValid(final String block) {
        final String firstDigit = block.substring(0, 1);
        final String secondDigit = block.substring(1);
        final Integer firstInteger = RomanDigit.parseStringToInt(firstDigit);
        final Integer secondInteger = RomanDigit.parseStringToInt(secondDigit);
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

    private Integer sum(final List<Integer> integers) {
        return integers.stream().mapToInt(Integer::intValue).sum();
    }
}