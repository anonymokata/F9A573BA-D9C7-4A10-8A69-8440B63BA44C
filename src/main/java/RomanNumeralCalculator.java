import java.util.Optional;

public class RomanNumeralCalculator {
    public String add(final String leftNumeral, final String rightNumeral) {
        final RomanNumeralToIntConverter converter = new RomanNumeralToIntConverter();

        final Optional<Integer> firstNumber = converter.convertToInt(leftNumeral);
        if(!firstNumber.isPresent()) {
            return "Left numeral is invalid";
        }
        final int secondNumber = converter.convertToInt(rightNumeral).get();
        final int sum = firstNumber.get() + secondNumber;

        return new IntToRomanNumeralConverter().convertToRomanNumeral(sum);
    }
}