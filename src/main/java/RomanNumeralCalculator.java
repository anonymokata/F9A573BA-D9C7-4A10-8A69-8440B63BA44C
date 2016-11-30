public class RomanNumeralCalculator {
    public String add(final String firstNumeral, final String secondNumeral) {
        final RomanNumeralConverter converter = new RomanNumeralConverter();

        final int firstNumber = converter.convertToInt(firstNumeral);
        final int secondNumber = converter.convertToInt(secondNumeral);
        final int sum = firstNumber + secondNumber;

        return converter.convertToRomanNumeral(sum);
    }
}