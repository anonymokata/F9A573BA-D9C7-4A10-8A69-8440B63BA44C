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

    @Test
    public void usingTwoEmptyStringsReturnsErrorMessage() {
        final String result = calculator.add("", "");
        assertThat(result).isEqualTo("Both numerals are invalid");
    }

    @Test
    public void usingInvalidLetterForLeftInputReturnsErrorMessage() {
        final String result = calculator.add("Z", "I");
        assertThat(result).isEqualTo("Left numeral is invalid");
    }

    @Test
    public void usingInvalidLetterForRightInputReturnsErrorMessage() {
        final String result = calculator.add("I", "O");
        assertThat(result).isEqualTo("Right numeral is invalid");
    }

    @Test
    public void usingTwoInvalidLettersReturnsErrorMessage() {
        final String result = calculator.add("K", "F");
        assertThat(result).isEqualTo("Both numerals are invalid");
    }

    @Test
    public void addingNumbersWhichWouldExceed3999ReturnsError() {
        assertThat(calculator.add("MMMCMXCIX", "I")).isEqualTo("Invalid result");
    }

    @Test
    public void subtracting2And1Returns1() {
        assertThat(calculator.subtract("II", "I")).isEqualTo("I");
    }

    @Test
    public void subtracting3999And111Returns3888() {
        assertThat(calculator.subtract("MMMCMXCIX", "CXI")).isEqualTo("MMMDCCCLXXXVIII");
    }
}