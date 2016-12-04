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
        assertThat(calculator.add("I", "I")).isEqualTo("II");
    }

    @Test
    public void adding2and1Returns3() {
        assertThat(calculator.add("II", "I")).isEqualTo("III");
    }

    @Test
    public void addingWithEmptyStringForLeftInputReturnsErrorMessage() {
        assertThat(calculator.add("", "I")).isEqualTo("Left numeral is invalid");
    }

    @Test
    public void addingWithEmptyStringForRightInputReturnsErrorMessage() {
        assertThat(calculator.add("I", "")).isEqualTo("Right numeral is invalid");
    }

    @Test
    public void addingWithTwoEmptyStringsReturnsErrorMessage() {
        assertThat(calculator.add("", "")).isEqualTo("Both numerals are invalid");
    }

    @Test
    public void addingWithInvalidLetterForLeftInputReturnsErrorMessage() {
        assertThat(calculator.add("Z", "I")).isEqualTo("Left numeral is invalid");
    }

    @Test
    public void addingWithInvalidLetterForRightInputReturnsErrorMessage() {
        assertThat(calculator.add("I", "O")).isEqualTo("Right numeral is invalid");
    }

    @Test
    public void addingWithTwoInvalidLettersReturnsErrorMessage() {
        assertThat(calculator.add("K", "F")).isEqualTo("Both numerals are invalid");
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

    @Test
    public void subtractingWithEmptyStringForLeftInputReturnsErrorMessage() {
        assertThat(calculator.subtract("", "I")).isEqualTo("Left numeral is invalid");
    }

    @Test
    public void subtractingWithInvalidLetterForLeftInputReturnsErrorMessage() {
        assertThat(calculator.subtract("Z", "I")).isEqualTo("Left numeral is invalid");
    }

    @Test
    public void subtractingWithEmptyStringForRightInputReturnsErrorMessage() {
        assertThat(calculator.subtract("II", "")).isEqualTo("Right numeral is invalid");
    }

    @Test
    public void subtractingWithInvalidLetterForRightInputReturnsErrorMessage() {
        assertThat(calculator.subtract("II", "O")).isEqualTo("Right numeral is invalid");
    }

    @Test
    public void subtractingWithTwoEmptyStringsReturnsErrorMessage() {
        assertThat(calculator.subtract("", "")).isEqualTo("Both numerals are invalid");
    }

    @Test
    public void subtractingWithTwoInvalidLettersReturnsErrorMessage() {
        assertThat(calculator.subtract("IC", "DM")).isEqualTo("Both numerals are invalid");
    }

    @Test
    public void subtractingToZeroReturnsErrorMessage() {
        assertThat(calculator.subtract("I", "I")).isEqualTo("Invalid result");
    }
}