package converter;

import java.util.Optional;

public class RomanNumeralConverter {

    private final NumeralToIntegerConverter numeralToIntegerConverter;
    private final IntegerToNumeralConverter integerToNumeralConverter;

    RomanNumeralConverter(final NumeralToIntegerConverter numeralToIntegerConverter,
                          final IntegerToNumeralConverter integerToNumeralConverter) {
        this.numeralToIntegerConverter = numeralToIntegerConverter;
        this.integerToNumeralConverter = integerToNumeralConverter;
    }

    public Optional<Integer> toInteger(final String numeral) {
        return numeralToIntegerConverter.convert(numeral);
    }

    public String toNumeral(final Integer integer) {
        return integerToNumeralConverter.convert(integer);
    }

    public static RomanNumeralConverter getInstance() {
        return new RomanNumeralConverter(new NumeralToIntegerConverter(), new IntegerToNumeralConverter());
    }
}