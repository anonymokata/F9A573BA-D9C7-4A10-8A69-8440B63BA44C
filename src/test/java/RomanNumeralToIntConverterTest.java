import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class RomanNumeralToIntConverterTest {

    private RomanNumeralToIntConverter converter;

    @Before
    public void setup() {
        converter = new RomanNumeralToIntConverter();
    }

    @Test
    public void convertsIto1() {
        final Optional<Integer> convertedNumber = converter.convertToInt("I");
        assertThat(convertedNumber).isPresent().contains(1);
    }

    @Test
    public void convertsIIto2() {
        final Optional<Integer> convertedNumber = converter.convertToInt("II");
        assertThat(convertedNumber).isPresent().contains(2);
    }

    @Test
    public void convertsIIIto3() {
        final Optional<Integer> convertedNumber = converter.convertToInt("III");
        assertThat(convertedNumber).isPresent().contains(3);
    }

    @Test
    public void convertsIVto4() {
        final Optional<Integer> convertedNumber = converter.convertToInt("IV");
        assertThat(convertedNumber).isPresent().contains(4);
    }

    @Test
    public void convertsVto5() {
        final Optional<Integer> convertedNumber = converter.convertToInt("V");
        assertThat(convertedNumber).isPresent().contains(5);
    }

    @Test
    public void convertsVIto6() {
        final Optional<Integer> convertedNumber = converter.convertToInt("VI");
        assertThat(convertedNumber).isPresent().contains(6);
    }

    @Test
    public void convertVIIto7() {
        final Optional<Integer> convertedNumber = converter.convertToInt("VII");
        assertThat(convertedNumber).isPresent().contains(7);
    }

    @Test
    public void convertVIIIto8() {
        final Optional<Integer> convertedNumber = converter.convertToInt("VIII");
        assertThat(convertedNumber).isPresent().contains(8);
    }

    @Test
    public void convertIXto9() {
        final Optional<Integer> convertedNumber = converter.convertToInt("IX");
        assertThat(convertedNumber).isPresent().contains(9);
    }

    @Test
    public void convertXto10() {
        final Optional<Integer> convertedNumber = converter.convertToInt("X");
        assertThat(convertedNumber).isPresent().contains(10);
    }

    @Test
    public void returnsAbsentOptionalIfCannotConvert() {
        final Optional<Integer> convertedNumber = converter.convertToInt("Z");
        assertThat(convertedNumber).isNotPresent();
    }
}