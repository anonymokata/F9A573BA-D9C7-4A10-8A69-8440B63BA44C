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
        assertThat(converter.convertToRomanNumeral(1)).isEqualTo("I");
    }

    @Test
    public void converts2toII() {
        assertThat(converter.convertToRomanNumeral(2)).isEqualTo("II");
    }

    @Test
    public void converts3toIII() {
        assertThat(converter.convertToRomanNumeral(3)).isEqualTo("III");
    }

    @Test
    public void rejectsNumbersGreaterThan4000() {
        assertThat(converter.convertToRomanNumeral(4000)).isEqualTo("Invalid result");
        assertThat(converter.convertToRomanNumeral(9999)).isEqualTo("Invalid result");
    }

}