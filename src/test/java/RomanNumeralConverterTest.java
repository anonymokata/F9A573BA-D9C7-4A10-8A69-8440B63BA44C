import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RomanNumeralConverterTest {

    @Test
    public void convertsIto1() {
        final RomanNumeralConverter converter = new RomanNumeralConverter();
        final int convertedNumber = converter.convertToInt("I");
        assertThat(convertedNumber).isEqualTo(1);
    }

    @Test
    public void convertsIIto2() {
        final RomanNumeralConverter converter = new RomanNumeralConverter();
        final int convertedNumber = converter.convertToInt("II");
        assertThat(convertedNumber).isEqualTo(2);
    }

    @Test
    public void converts1toI() {
        final RomanNumeralConverter converter = new RomanNumeralConverter();
        final String convertedNumeral = converter.convertToRomanNumeral(1);
        assertThat(convertedNumeral).isEqualTo("I");
    }

    @Test
    public void converts2toII() {
        final RomanNumeralConverter converter = new RomanNumeralConverter();
        final String convertedNumeral = converter.convertToRomanNumeral(2);
        assertThat(convertedNumeral).isEqualTo("II");
    }
}