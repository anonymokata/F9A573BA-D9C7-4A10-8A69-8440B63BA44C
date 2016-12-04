import converter.RomanNumeralConverter;

import java.util.Optional;
import java.util.function.BiFunction;

public class RomanNumeralCalculator {

    private static final String LEFT_NUMERAL_INVALID_MESSAGE = "Left numeral is invalid";
    private static final String RIGHT_NUMERAL_INVALID_MESSAGE = "Right numeral is invalid";
    private static final String BOTH_NUMERALS_INVALID_MESSAGE = "Both numerals are invalid";

    private static final BiFunction<Integer, Integer, Integer> ADD = (leftInput, rightInput) -> leftInput + rightInput;
    private static final BiFunction<Integer, Integer, Integer> SUBTRACT = (leftInput, rightInput) -> leftInput - rightInput;

    private final RomanNumeralConverter converter;

    public RomanNumeralCalculator() {
        converter = new RomanNumeralConverter();
    }

    public String add(final String leftNumeral, final String rightNumeral) {
        return calculate(leftNumeral, rightNumeral, ADD);
    }

    public String subtract(final String minuend, final String subtrahend) {
        return calculate(minuend, subtrahend, SUBTRACT);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private String calculate(final String leftNumeral, final String rightNumeral, final BiFunction<Integer, Integer, Integer> operation) {
        final Optional<Integer> firstNumber = converter.toInteger(leftNumeral);
        final Optional<Integer> secondNumber = converter.toInteger(rightNumeral);

        if(isAbsent(firstNumber) || isAbsent(secondNumber)) {
            return chooseErrorMessage(firstNumber, secondNumber);
        } else {
            final int result = operation.apply(firstNumber.get(), secondNumber.get());
            return converter.toNumeral(result);
        }
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private String chooseErrorMessage(final Optional<Integer> leftInput, final Optional<Integer> rightInput) {
        if(isAbsent(leftInput) && isAbsent(rightInput)) {
            return BOTH_NUMERALS_INVALID_MESSAGE;
        } else if(isAbsent(leftInput)) {
            return LEFT_NUMERAL_INVALID_MESSAGE;
        } else {
            return RIGHT_NUMERAL_INVALID_MESSAGE;
        }
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private boolean isAbsent(final Optional<Integer> optionalNumber) {
        return !optionalNumber.isPresent();
    }
}