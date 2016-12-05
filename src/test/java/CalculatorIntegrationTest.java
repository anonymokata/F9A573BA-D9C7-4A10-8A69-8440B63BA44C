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
        assertThat(calculator.add("MMMCMXCIX", "MMMCMXCIX")).isEqualTo("Calculation result is outside valid range");
    }

    @Test
    public void differenceOfCCLXXIXAndLXXIVReturnsCCV() {
        assertThat(calculator.subtract("CCLXXIX", "LXXIV")).isEqualTo("CCV");
    }

    @Test
    public void differenceOfMCXVAndDLVIReturnsDLIX() {
        assertThat(calculator.subtract("MCXV", "DLVI")).isEqualTo("DLIX");
    }

    @Test
    public void differenceOfMMLXVIIAndMCCCXCVIReturnsDCLXXI() {
        assertThat(calculator.subtract("MMLXVII", "MCCCXCVI")).isEqualTo("DCLXXI");
    }

    @Test
    public void differenceOfMCCCXLAndMXLVReturnsCCXCV() {
        assertThat(calculator.subtract("MCCCXL", "MXLV")).isEqualTo("CCXCV");
    }

    @Test
    public void differenceOfMMDCCCXXVIAndMMDXVReturnsCCCXI() {
        assertThat(calculator.subtract("MMDCCCXXVI", "MMDXV")).isEqualTo("CCCXI");
    }

    @Test
    public void differenceOfDCXLVAndIIReturnsDCXLIII() {
        assertThat(calculator.subtract("DCXLV", "II")).isEqualTo("DCXLIII");
    }

    @Test
    public void subtractingToZeroOrNegativeNumberReturnsErrorMessage() {
        assertThat(calculator.subtract("CCC", "CCC")).isEqualTo("Calculation result is outside valid range");
        assertThat(calculator.subtract("M", "MI")).isEqualTo("Calculation result is outside valid range");
    }

    @Test
    public void subtractingFromAJustTooLargeNumberStillReturnsAnErrorMessage() {
        assertThat(calculator.subtract("MMMM", "I")).isEqualTo("Left numeral is invalid");
    }
}