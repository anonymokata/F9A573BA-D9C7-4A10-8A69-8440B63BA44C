import java.util.Optional;

public class RomanNumeralCalculator {

    private static final String LEFT_NUMERAL_INVALID_MESSAGE = "Left numeral is invalid";
    private static final String RIGHT_NUMERAL_INVALID_MESSAGE = "Right numeral is invalid";

    private final RomanNumeralToIntConverter toIntConverter;
    private final IntToRomanNumeralConverter toRomanNumeralConverter;

    public RomanNumeralCalculator() {
        toIntConverter = new RomanNumeralToIntConverter();
        toRomanNumeralConverter = new IntToRomanNumeralConverter();
    }

    public String add(final String leftNumeral, final String rightNumeral) {
        final Optional<Integer> firstNumber = toIntConverter.convertToInt(leftNumeral);
        final Optional<Integer> secondNumber = toIntConverter.convertToInt(rightNumeral);

        if(!firstNumber.isPresent() && !secondNumber.isPresent()) {
            return "Both numerals are invalid";
        } else if(!firstNumber.isPresent()) {
            return LEFT_NUMERAL_INVALID_MESSAGE;
        } else if(!secondNumber.isPresent()) {
            return RIGHT_NUMERAL_INVALID_MESSAGE;
        } else {
            final int sum = firstNumber.get() + secondNumber.get();
            return toRomanNumeralConverter.convertToRomanNumeral(sum);
        }
    }

    public String subtract(final String minuend, final String subtrahend) {
        final Optional<Integer> minuendInt = toIntConverter.convertToInt(minuend);
        final Optional<Integer> subtrahendInt = toIntConverter.convertToInt(subtrahend);
        if(!minuendInt.isPresent()) {
            return LEFT_NUMERAL_INVALID_MESSAGE;
        } else if(!subtrahendInt.isPresent()) {
            return RIGHT_NUMERAL_INVALID_MESSAGE;
        }
        final int difference = minuendInt.get() - subtrahendInt.get();
        return toRomanNumeralConverter.convertToRomanNumeral(difference);
    }
}