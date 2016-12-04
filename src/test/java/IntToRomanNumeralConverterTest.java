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
    public void converts4ToIV() {
        assertThat(converter.convertToRomanNumeral(4)).isEqualTo("IV");
    }

    @Test
    public void converts5ToV() {
        assertThat(converter.convertToRomanNumeral(5)).isEqualTo("V");
    }

    @Test
    public void converts6ToVI() {
        assertThat(converter.convertToRomanNumeral(6)).isEqualTo("VI");
    }

    @Test
    public void converts10ToX() {
        assertThat(converter.convertToRomanNumeral(10)).isEqualTo("X");
    }

    @Test
    public void converts900toCM() {
        assertThat(converter.convertToRomanNumeral(900)).isEqualTo("CM");
    }

    @Test
    public void converts3888ToMMMDCCCLXXXVIII() {
        assertThat(converter.convertToRomanNumeral(3888)).isEqualTo("MMMDCCCLXXXVIII");
    }

    @Test
    public void converts372ToMMMDCCCLXXXVIII() {
        assertThat(converter.convertToRomanNumeral(372)).isEqualTo("CCCLXXII");
    }

    @Test
    public void converts555ToDLV() {
        assertThat(converter.convertToRomanNumeral(555)).isEqualTo("DLV");
    }

    @Test
    public void rejectsNumbersGreaterThan4000() {
        assertThat(converter.convertToRomanNumeral(4000)).isEqualTo("Invalid result");
        assertThat(converter.convertToRomanNumeral(9999)).isEqualTo("Invalid result");
    }
}