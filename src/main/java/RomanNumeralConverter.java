import java.util.Optional;

public class RomanNumeralConverter {

    private final RomanNumeralToIntConverter romanNumeralToIntConverter;
    private final IntToRomanNumeralConverter integerToNumeralConverter;

    public RomanNumeralConverter() {
        this.romanNumeralToIntConverter = new RomanNumeralToIntConverter();
        this.integerToNumeralConverter = new IntToRomanNumeralConverter();
    }

    public Optional<Integer> toInteger(final String numeral) {
        return romanNumeralToIntConverter.convertToInt(numeral);
    }

    public String toNumeral(final Integer integer) {
        return integerToNumeralConverter.convertToRomanNumeral(integer);
    }
}