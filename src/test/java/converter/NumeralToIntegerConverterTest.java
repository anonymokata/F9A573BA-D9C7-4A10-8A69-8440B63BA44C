package converter;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NumeralToIntegerConverterTest {

    private NumeralToIntegerConverter converter;

    @Before
    public void setup() {
        converter = new NumeralToIntegerConverter();
    }

    @Test
    public void convertsIto1() {
        assertThat(converter.convert("I")).isPresent().contains(1);
    }

    @Test
    public void convertsIIto2() {
        assertThat(converter.convert("II")).isPresent().contains(2);
    }

    @Test
    public void convertsIIIto3() {
        assertThat(converter.convert("III")).isPresent().contains(3);
    }

    @Test
    public void convertsIVto4() {
        assertThat(converter.convert("IV")).isPresent().contains(4);
    }

    @Test
    public void convertsVto5() {
        assertThat(converter.convert("V")).isPresent().contains(5);
    }

    @Test
    public void convertsVIto6() {
        assertThat(converter.convert("VI")).isPresent().contains(6);
    }

    @Test
    public void convertVIIto7() {
        assertThat(converter.convert("VII")).isPresent().contains(7);
    }

    @Test
    public void convertVIIIto8() {
        assertThat(converter.convert("VIII")).isPresent().contains(8);
    }

    @Test
    public void convertIXto9() {
        assertThat(converter.convert("IX")).isPresent().contains(9);
    }

    @Test
    public void convertXto10() {
        assertThat(converter.convert("X")).isPresent().contains(10);
    }

    @Test
    public void convertXLIXto49() {
        assertThat(converter.convert("XLIX")).isPresent().contains(49);
    }

    @Test
    public void convertCCCLXXVIIIto378() {
        assertThat(converter.convert("CCCLXXVIII")).isPresent().contains(378);
    }

    @Test
    public void convertDCIXTo609() {
        assertThat(converter.convert("DCIX")).isPresent().contains(609);
    }

    @Test
    public void convertMMCDLIVTo2454() {
        assertThat(converter.convert("MMCDLIV")).isPresent().contains(2454);
    }

    @Test
    public void convertXIXTo19() {
        assertThat(converter.convert("XIX")).isPresent().contains(19);
    }

    @Test
    public void convertXXTo20() {
        assertThat(converter.convert("XX")).isPresent().contains(20);
    }

    @Test
    public void convertMDCXVIITo1617() {
        assertThat(converter.convert("MDCXVII")).isPresent().contains(1617);
    }

    @Test
    public void convertDXLVITo546() {
        assertThat(converter.convert("DXLVI")).isPresent().contains(546);
    }

    @Test
    public void convertDLXIXTo569() {
        assertThat(converter.convert("DLXIX")).isPresent().contains(569);
    }

    @Test
    public void convertLargestValidNumber() {
        assertThat(converter.convert("MMMCMXCIX")).isPresent().contains(3999);
    }

    @Test
    public void returnsAbsentOptionalForDuplicateSubtractiveDigits() {
        assertThat(converter.convert("IXIX")).isNotPresent();
    }

    @Test
    public void returnsAbsentOptionalForInvalidSubtractiveDigits() {
        assertThat(converter.convert("IL")).isNotPresent();
        assertThat(converter.convert("IC")).isNotPresent();
        assertThat(converter.convert("XM")).isNotPresent();
        assertThat(converter.convert("VX")).isNotPresent();
    }

    @Test
    public void returnsAbsentOptionalWhenInvalidRepeatingDigits() {
        assertThat(converter.convert("CCM")).isNotPresent();
        assertThat(converter.convert("CMMMI")).isNotPresent();
        assertThat(converter.convert("MMMM")).isNotPresent();
        assertThat(converter.convert("LL")).isNotPresent();
        assertThat(converter.convert("CCCC")).isNotPresent();
    }

    @Test
    public void returnsAbsentOptionalIfCannotConvert() {
        assertThat(converter.convert("Z")).isNotPresent();
        assertThat(converter.convert("")).isNotPresent();
    }

    @Test
    public void returnsAbsentOptionalForLowerCaseNumerals() {
        assertThat(converter.convert("i")).isNotPresent();
        assertThat(converter.convert("v")).isNotPresent();
        assertThat(converter.convert("x")).isNotPresent();
    }

    @Test
    public void returnsAbsentOptionalForInvalidDigitInMiddleOfNumeral() {
        assertThat(converter.convert("MMZCCXII")).isNotPresent();
        assertThat(converter.convert("MZMCCXII")).isNotPresent();
    }

}