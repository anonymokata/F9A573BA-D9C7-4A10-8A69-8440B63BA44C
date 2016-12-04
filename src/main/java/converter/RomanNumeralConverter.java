package converter;

import java.util.Optional;

public class RomanNumeralConverter {

    private final NumeralToIntegerConverter numeralToIntegerConverter;
    private final IntegerToNumeralConverter integerToNumeralConverter;

    public RomanNumeralConverter() {
        this.numeralToIntegerConverter = new NumeralToIntegerConverter();
        this.integerToNumeralConverter = new IntegerToNumeralConverter();
    }

    public Optional<Integer> toInteger(final String numeral) {
        return numeralToIntegerConverter.convert(numeral);
    }

    public String toNumeral(final Integer integer) {
        return integerToNumeralConverter.convert(integer);
    }
}