package converter;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class NumeralToIntegerConverterTest {

    private NumeralToIntegerConverter converter;

    @Before
    public void setup() {
        converter = new NumeralToIntegerConverter();
    }

    @Test
    public void convertsIto1() {
        Assertions.assertThat(converter.convert("I")).isPresent().contains(1);
    }

    @Test
    public void convertsIIto2() {
        Assertions.assertThat(converter.convert("II")).isPresent().contains(2);
    }

    @Test
    public void convertsIIIto3() {
        Assertions.assertThat(converter.convert("III")).isPresent().contains(3);
    }

    @Test
    public void convertsIVto4() {
        Assertions.assertThat(converter.convert("IV")).isPresent().contains(4);
    }

    @Test
    public void convertsVto5() {
        Assertions.assertThat(converter.convert("V")).isPresent().contains(5);
    }

    @Test
    public void convertsVIto6() {
        Assertions.assertThat(converter.convert("VI")).isPresent().contains(6);
    }

    @Test
    public void convertVIIto7() {
        Assertions.assertThat(converter.convert("VII")).isPresent().contains(7);
    }

    @Test
    public void convertVIIIto8() {
        Assertions.assertThat(converter.convert("VIII")).isPresent().contains(8);
    }

    @Test
    public void convertIXto9() {
        Assertions.assertThat(converter.convert("IX")).isPresent().contains(9);
    }

    @Test
    public void convertXto10() {
        Assertions.assertThat(converter.convert("X")).isPresent().contains(10);
    }

    @Test
    public void convertXLIXto49() {
        Assertions.assertThat(converter.convert("XLIX")).isPresent().contains(49);
    }

    @Test
    public void convertCCCLXXVIIIto378() {
        Assertions.assertThat(converter.convert("CCCLXXVIII")).isPresent().contains(378);
    }

    @Test
    public void convertDCIXTo609() {
        Assertions.assertThat(converter.convert("DCIX")).isPresent().contains(609);
    }

    @Test
    public void convertMMCDLIVTo2454() {
        Assertions.assertThat(converter.convert("MMCDLIV")).isPresent().contains(2454);
    }

    @Test
    public void convertXIXTo19() {
        Assertions.assertThat(converter.convert("XIX")).isPresent().contains(19);
    }

    @Test
    public void convertXXTo20() {
        Assertions.assertThat(converter.convert("XX")).isPresent().contains(20);
    }

    @Test
    public void convertMDCXVIITo1617() {
        Assertions.assertThat(converter.convert("MDCXVII")).isPresent().contains(1617);
    }

    @Test
    public void convertDXLVITo546() {
        Assertions.assertThat(converter.convert("DXLVI")).isPresent().contains(546);
    }

    @Test
    public void convertDLXIXTo569() {
        Assertions.assertThat(converter.convert("DLXIX")).isPresent().contains(569);
    }

    @Test
    public void convertLargestValidNumber() {
        Assertions.assertThat(converter.convert("MMMCMXCIX")).isPresent().contains(3999);
    }

    @Test
    public void returnsAbsentOptionalForDuplicateSubtractiveDigits() {
        Assertions.assertThat(converter.convert("IXIX")).isNotPresent();
    }

    @Test
    public void returnsAbsentOptionalForInvalidSubtractiveDigits() {
        Assertions.assertThat(converter.convert("IL")).isNotPresent();
        Assertions.assertThat(converter.convert("IC")).isNotPresent();
        Assertions.assertThat(converter.convert("XM")).isNotPresent();
        Assertions.assertThat(converter.convert("VX")).isNotPresent();
    }

    @Test
    public void returnsAbsentOptionalWhenInvalidRepeatingDigits() {
        Assertions.assertThat(converter.convert("CCM")).isNotPresent();
        Assertions.assertThat(converter.convert("CMMMI")).isNotPresent();
        Assertions.assertThat(converter.convert("MMMM")).isNotPresent();
        Assertions.assertThat(converter.convert("LL")).isNotPresent();
        Assertions.assertThat(converter.convert("CCCC")).isNotPresent();
    }

    @Test
    public void returnsAbsentOptionalIfCannotConvert() {
        Assertions.assertThat(converter.convert("Z")).isNotPresent();
        Assertions.assertThat(converter.convert("")).isNotPresent();
    }

    @Test
    public void returnsAbsentOptionalForLowerCaseNumerals() {
        Assertions.assertThat(converter.convert("i")).isNotPresent();
        Assertions.assertThat(converter.convert("v")).isNotPresent();
        Assertions.assertThat(converter.convert("x")).isNotPresent();
    }
}