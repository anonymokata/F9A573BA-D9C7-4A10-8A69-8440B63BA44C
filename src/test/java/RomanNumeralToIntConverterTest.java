import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RomanNumeralToIntConverterTest {

    private RomanNumeralToIntConverter converter;

    @Before
    public void setup() {
        converter = new RomanNumeralToIntConverter();
    }

    @Test
    public void convertsIto1() {
        assertThat(converter.convertToInt("I")).isPresent().contains(1);
    }

    @Test
    public void convertsIIto2() {
        assertThat(converter.convertToInt("II")).isPresent().contains(2);
    }

    @Test
    public void convertsIIIto3() {
        assertThat(converter.convertToInt("III")).isPresent().contains(3);
    }

    @Test
    public void convertsIVto4() {
        assertThat(converter.convertToInt("IV")).isPresent().contains(4);
    }

    @Test
    public void convertsVto5() {
        assertThat(converter.convertToInt("V")).isPresent().contains(5);
    }

    @Test
    public void convertsVIto6() {
        assertThat(converter.convertToInt("VI")).isPresent().contains(6);
    }

    @Test
    public void convertVIIto7() {
        assertThat(converter.convertToInt("VII")).isPresent().contains(7);
    }

    @Test
    public void convertVIIIto8() {
        assertThat(converter.convertToInt("VIII")).isPresent().contains(8);
    }

    @Test
    public void convertIXto9() {
        assertThat(converter.convertToInt("IX")).isPresent().contains(9);
    }

    @Test
    public void convertXto10() {
        assertThat(converter.convertToInt("X")).isPresent().contains(10);
    }

    @Test
    public void returnsAbsentOptionalIfCannotConvert() {
        assertThat(converter.convertToInt("Z")).isNotPresent();
        assertThat(converter.convertToInt("")).isNotPresent();
    }

    @Test
    public void returnsAbsentOptionalForLowerCaseNumerals() {
        assertThat(converter.convertToInt("i")).isNotPresent();
        assertThat(converter.convertToInt("v")).isNotPresent();
        assertThat(converter.convertToInt("x")).isNotPresent();
    }
}