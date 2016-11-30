import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IntToRomanNumeralConverterTest {

    private IntToRomanNumeralConverter converter;

    @Before
    public void setup() throws Exception {
        converter = new IntToRomanNumeralConverter();
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