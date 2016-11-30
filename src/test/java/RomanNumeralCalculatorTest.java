import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RomanNumeralCalculatorTest {

    private RomanNumeralCalculator calculator;

    @Before
    public void setup() {
        calculator = new RomanNumeralCalculator();
    }

    @Test
    public void adding1and1Returns2() {
        final String sum = calculator.add("I", "I");
        assertThat(sum).isEqualTo("II");
    }

    @Test
    public void adding2and1Returns3() {
        final String sum = calculator.add("II", "I");
        assertThat(sum).isEqualTo("III");
    }

    @Test
    public void usingEmptyStringForLeftInputReturnsErrorMessage() {
        final String result = calculator.add("", "I");
        assertThat(result).isEqualTo("Left numeral is invalid");
    }

    @Test
    public void usingEmptyStringForRightInputReturnsErrorMessage() {
        final String result = calculator.add("I", "");
        assertThat(result).isEqualTo("Right numeral is invalid");
    }
}