package converter;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IntegerToNumeralConverterTest {

    private IntegerToNumeralConverter converter;

    @Before
    public void setup() throws Exception {
        converter = new IntegerToNumeralConverter();
    }

    @Test
    public void converts1toI() {
        assertThat(converter.convert(1)).isEqualTo("I");
    }

    @Test
    public void converts2toII() {
        assertThat(converter.convert(2)).isEqualTo("II");
    }

    @Test
    public void converts3toIII() {
        assertThat(converter.convert(3)).isEqualTo("III");
    }

    @Test
    public void converts4ToIV() {
        assertThat(converter.convert(4)).isEqualTo("IV");
    }

    @Test
    public void converts5ToV() {
        assertThat(converter.convert(5)).isEqualTo("V");
    }

    @Test
    public void converts6ToVI() {
        assertThat(converter.convert(6)).isEqualTo("VI");
    }

    @Test
    public void converts10ToX() {
        assertThat(converter.convert(10)).isEqualTo("X");
    }

    @Test
    public void converts900toCM() {
        assertThat(converter.convert(900)).isEqualTo("CM");
    }

    @Test
    public void converts3888ToMMMDCCCLXXXVIII() {
        assertThat(converter.convert(3888)).isEqualTo("MMMDCCCLXXXVIII");
    }

    @Test
    public void converts3999ToMMMCMXCIX() {
        assertThat(converter.convert(3999)).isEqualTo("MMMCMXCIX");
    }

    @Test
    public void converts444ToCDXLIV() {
        assertThat(converter.convert(444)).isEqualTo("CDXLIV");
    }

    @Test
    public void converts372ToMMMDCCCLXXXVIII() {
        assertThat(converter.convert(372)).isEqualTo("CCCLXXII");
    }

    @Test
    public void converts555ToDLV() {
        assertThat(converter.convert(555)).isEqualTo("DLV");
    }

    @Test
    public void rejectsNumbersGreaterThan3999() {
        assertThat(converter.convert(4000)).isEqualTo("Calculation result is outside valid range");
        assertThat(converter.convert(9999)).isEqualTo("Calculation result is outside valid range");
    }

    @Test
    public void rejectsNumbersLessThan1() {
        assertThat(converter.convert(0)).isEqualTo("Calculation result is outside valid range");
        assertThat(converter.convert(-1)).isEqualTo("Calculation result is outside valid range");
    }
}