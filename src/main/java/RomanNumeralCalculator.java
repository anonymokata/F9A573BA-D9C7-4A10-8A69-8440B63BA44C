import java.util.Optional;

public class RomanNumeralCalculator {
    public String add(final String leftNumeral, final String rightNumeral) {
        final RomanNumeralToIntConverter converter = new RomanNumeralToIntConverter();

        final Optional<Integer> firstNumber = converter.convertToInt(leftNumeral);
        if(!firstNumber.isPresent()) {
            return "Left numeral is invalid";
        }

        final Optional<Integer> secondNumber = converter.convertToInt(rightNumeral);
        if(!secondNumber.isPresent()) {
            return "Right numeral is invalid";
        }
        final int sum = firstNumber.get() + secondNumber.get();

        return new IntToRomanNumeralConverter().convertToRomanNumeral(sum);
    }
}