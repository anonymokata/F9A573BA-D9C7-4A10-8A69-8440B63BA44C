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
    public void convertsIVto4() {
        final int convertedNumber = converter.convertToInt("IV");
        assertThat(convertedNumber).isEqualTo(4);
    }

    @Test
    public void convertsVto5() {
        final int convertedNumber = converter.convertToInt("V");
        assertThat(convertedNumber).isEqualTo(5);
    }

    @Test
    public void convertsVIto6() {
        final int convertedNumber = converter.convertToInt("VI");
        assertThat(convertedNumber).isEqualTo(6);
    }

    @Test
    public void convertVIIto7() {
        final int convertedNumber = converter.convertToInt("VII");
        assertThat(convertedNumber).isEqualTo(7);
    }

    @Test
    public void convertVIIIto8() {
        final int convertedNumber = converter.convertToInt("VIII");
        assertThat(convertedNumber).isEqualTo(8);
    }

    @Test
    public void convertIXto9() {
        final int convertedNumber = converter.convertToInt("IX");
        assertThat(convertedNumber).isEqualTo(9);
    }

    @Test
    public void convertXto10() {
        final int convertedNumber = converter.convertToInt("X");
        assertThat(convertedNumber).isEqualTo(10);
    }
}