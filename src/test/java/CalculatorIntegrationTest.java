import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorIntegrationTest {

    private RomanNumeralCalculator calculator;

    @Before
    public void setup() {
        calculator = new RomanNumeralCalculator();
    }

    @Test
    public void sumOfMCDXLVIIAndIIReturnsMCDXLIX() {
        assertThat(calculator.add("MCDXLVII", "II")).isEqualTo("MCDXLIX");
    }

    @Test
    public void sumOfMCDXLVIIndCCCXXIIReturnsMDCCLXIX() {
        assertThat(calculator.add("MCDXLVII", "CCCXXII")).isEqualTo("MDCCLXIX");
    }

    @Test
    public void sumOfMMMCXCVAndDCCCIVReturnsMDCCLXIX() {
        assertThat(calculator.add("MMMCXCV", "DCCCIV")).isEqualTo("MMMCMXCIX");
    }

    @Test
    public void sumOfMDCCCLXXXIIAndMDCXXIReturnsMMMDIII() {
        assertThat(calculator.add("MDCCCLXXXII", "MDCXXI")).isEqualTo("MMMDIII");
    }

    @Test
    public void sumOfMMCDLXXXIXAndDCXLIReturnsMMMCXXX() {
        assertThat(calculator.add("MMCDLXXXIX", "DCXLI")).isEqualTo("MMMCXXX");
    }

    @Test
    public void sumOfVIIIAndXXVReturnsXXXIII() {
        assertThat(calculator.add("VIII", "XXV")).isEqualTo("XXXIII");
    }

    @Test
    public void sumOfTwoMaxNumbersReturnsError() {
        assertThat(calculator.add("MMMCMXCIX", "MMMCMXCIX")).isEqualTo("Invalid result");
    }
}
