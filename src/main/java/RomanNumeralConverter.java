import java.util.Optional;

public class RomanNumeralConverter {

    private final RomanNumeralToIntConverter romanNumeralToIntConverter;

    public RomanNumeralConverter() {
        this.romanNumeralToIntConverter = new RomanNumeralToIntConverter();
    }

    public Optional<Integer> toInteger(final String numeral) {
        return romanNumeralToIntConverter.convertToInt(numeral);
    }
}