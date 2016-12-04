package converter;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class IntegerToNumeralConverterTest {

    private IntegerToNumeralConverter converter;

    @Before
    public void setup() throws Exception {
        converter = new IntegerToNumeralConverter();
    }

    @Test
    public void converts1toI() {
        Assertions.assertThat(converter.convert(1)).isEqualTo("I");
    }

    @Test
    public void converts2toII() {
        Assertions.assertThat(converter.convert(2)).isEqualTo("II");
    }

    @Test
    public void converts3toIII() {
        Assertions.assertThat(converter.convert(3)).isEqualTo("III");
    }

    @Test
    public void converts4ToIV() {
        Assertions.assertThat(converter.convert(4)).isEqualTo("IV");
    }

    @Test
    public void converts5ToV() {
        Assertions.assertThat(converter.convert(5)).isEqualTo("V");
    }

    @Test
    public void converts6ToVI() {
        Assertions.assertThat(converter.convert(6)).isEqualTo("VI");
    }

    @Test
    public void converts10ToX() {
        Assertions.assertThat(converter.convert(10)).isEqualTo("X");
    }

    @Test
    public void converts900toCM() {
        Assertions.assertThat(converter.convert(900)).isEqualTo("CM");
    }

    @Test
    public void converts3888ToMMMDCCCLXXXVIII() {
        Assertions.assertThat(converter.convert(3888)).isEqualTo("MMMDCCCLXXXVIII");
    }

    @Test
    public void converts3999ToMMMCMXCIX() {
        Assertions.assertThat(converter.convert(3999)).isEqualTo("MMMCMXCIX");
    }

    @Test
    public void converts444ToCDXLIV() {
        Assertions.assertThat(converter.convert(444)).isEqualTo("CDXLIV");
    }

    @Test
    public void converts372ToMMMDCCCLXXXVIII() {
        Assertions.assertThat(converter.convert(372)).isEqualTo("CCCLXXII");
    }

    @Test
    public void converts555ToDLV() {
        Assertions.assertThat(converter.convert(555)).isEqualTo("DLV");
    }

    @Test
    public void rejectsNumbersGreaterThan3999() {
        Assertions.assertThat(converter.convert(4000)).isEqualTo("Invalid result");
        Assertions.assertThat(converter.convert(9999)).isEqualTo("Invalid result");
    }

    @Test
    public void rejectsNumbersLessThan1() {
        Assertions.assertThat(converter.convert(0)).isEqualTo("Invalid result");
        Assertions.assertThat(converter.convert(-1)).isEqualTo("Invalid result");
    }
}