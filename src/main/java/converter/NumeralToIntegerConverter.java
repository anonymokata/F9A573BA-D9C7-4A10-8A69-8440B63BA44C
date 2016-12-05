package converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class NumeralToIntegerConverter {

    private static final String EMPTY_STRING = "";
    private static final int MAX_LENGTH_FOR_POWER_OF_TEN_BLOCK = 3;
    private static final int MAX_LENGTH_FOR_POWER_OF_FIVE_BLOCK = 1;

    Optional<Integer> convert(final String numeral) {
        final List<String> blockedNumeral = createNumeralBlocks(numeral);

        if (isValid(blockedNumeral)) {
            return Optional.of(sum(blockedNumeral));
        }

        return Optional.empty();
    }

    private List<String> createNumeralBlocks(final String numeral) {
        final List<String> blockedNumeral = new ArrayList<>();
        StringBuilder blockBuilder = new StringBuilder();

        for (final String digit : numeral.split(EMPTY_STRING)) {
            if (blockBuilder.length() == 0) {
                blockBuilder = startNewBlock(digit);
            } else if (shouldAddNumeralToCurrentBlock(blockBuilder.toString(), digit)) {
                blockBuilder.append(digit);
            } else {
                addCurrentBlockToList(blockedNumeral, blockBuilder);
                blockBuilder = startNewBlock(digit);
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
        return firstDigit != null && hasValidBlockLength(firstDigit, block);
    }

    private RomanDigit getFirstDigit(final String block) {
        final String firstDigitString = block.substring(0, 1);
        return RomanDigit.parseString(firstDigitString);
    }

    private boolean hasValidBlockLength(final RomanDigit firstDigit, final String block) {
        if (firstDigit.getPowerOfType().equals(PowerOfType.TEN)) {
            return block.length() <= MAX_LENGTH_FOR_POWER_OF_TEN_BLOCK;
        } else {
            return block.length() == MAX_LENGTH_FOR_POWER_OF_FIVE_BLOCK;
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

        if(blockContainsOnlyOneTypeOfNumeral(block)) {
            return splitBlock.stream().mapToInt(RomanDigit::parseStringToInt).sum();
        } else {
            final int subtractingValue = RomanDigit.parseStringToInt(splitBlock.get(0));
            final int largerValue = RomanDigit.parseStringToInt(splitBlock.get(1));
            return largerValue - subtractingValue;
        }
    }
}