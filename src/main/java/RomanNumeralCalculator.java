public class RomanNumeralCalculator {
    public String add(final String firstNumeral, final String secondNumeral) {
        final RomanNumeralToIntConverter converter = new RomanNumeralToIntConverter();

        final int firstNumber = converter.convertToInt(firstNumeral).get();
        final int secondNumber = converter.convertToInt(secondNumeral).get();
        final int sum = firstNumber + secondNumber;

        return new IntToRomanNumeralConverter().convertToRomanNumeral(sum);
    }
}