import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RomanNumeralConverterTest {

    @Test
    public void convertsIto1() {
        final RomanNumeralConverter converter = new RomanNumeralConverter();
        final int convertedNumber = converter.convert("I");
        assertThat(convertedNumber).isEqualTo(1);
    }
}