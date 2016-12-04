import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class RomanNumeralConverterTest {

    @Test
    public void converterHandlesNumeralToInteger() {
        final RomanNumeralConverter converter = new RomanNumeralConverter();
        final Optional<Integer> convertedNumeral = converter.toInteger("CDI");
        assertThat(convertedNumeral).isPresent().contains(401);
    }
}
