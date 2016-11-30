import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RomanNumeralConverterTest {

    private RomanNumeralConverter converter;

    @Before
    public void setup() {
        converter = new RomanNumeralConverter();
    }

    @Test
    public void convertsIto1() {
        final int convertedNumber = converter.convertToInt("I");
        assertThat(convertedNumber).isEqualTo(1);
    }

    @Test
    public void convertsIIto2() {
        final int convertedNumber = converter.convertToInt("II");
        assertThat(convertedNumber).isEqualTo(2);
    }

    @Test
    public void convertsIIIto3() {
        final int convertedNumber = converter.convertToInt("III");
        assertThat(convertedNumber).isEqualTo(3);
    }

    @Test
    public void converts1toI() {
        final String convertedNumeral = converter.convertToRomanNumeral(1);
        assertThat(convertedNumeral).isEqualTo("I");
    }

    @Test
    public void converts2toII() {
        final String convertedNumeral = converter.convertToRomanNumeral(2);
        assertThat(convertedNumeral).isEqualTo("II");
    }

    @Test
    public void converts3toIII() {
        final String convertedNumeral = converter.convertToRomanNumeral(3);
        assertThat(convertedNumeral).isEqualTo("III");
    }
}