import java.util.Optional;

public class RomanNumeralCalculator {

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
            return "Left numeral is invalid";
        } else if(!secondNumber.isPresent()) {
            return "Right numeral is invalid";
        } else {
            final int sum = firstNumber.get() + secondNumber.get();
            return toRomanNumeralConverter.convertToRomanNumeral(sum);
        }
    }

    public String subtract(final String minuend, final String subtrahend) {
        return "I";
    }
}