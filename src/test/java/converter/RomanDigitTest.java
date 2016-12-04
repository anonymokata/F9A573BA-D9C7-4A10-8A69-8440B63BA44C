package converter;

import org.junit.Test;

import static converter.RomanDigit.*;
import static org.assertj.core.api.Assertions.assertThat;

public class RomanDigitTest {

    @Test
    public void oneMapsToCorrectValues() {
        assertThat(ONE.getNumeralValue()).isEqualTo("I");
        assertThat(ONE.getIntValue()).isEqualTo(1);
        assertThat(ONE.getPowerOfType()).isEqualTo(PowerOfType.TEN);
        assertThat(ONE.getNextLowestPowerOfTen()).isEqualTo(ONE);
        assertThat(ONE.getNextHighestDigit()).isEqualTo(FIVE);
    }

    @Test
    public void fiveMapsToCorrectValues() {
        assertThat(FIVE.getNumeralValue()).isEqualTo("V");
        assertThat(FIVE.getIntValue()).isEqualTo(5);
        assertThat(FIVE.getPowerOfType()).isEqualTo(PowerOfType.FIVE);
        assertThat(FIVE.getNextLowestPowerOfTen()).isEqualTo(ONE);
        assertThat(FIVE.getNextHighestDigit()).isEqualTo(TEN);
    }

    @Test
    public void tenMapsToCorrectValues() {
        assertThat(TEN.getNumeralValue()).isEqualTo("X");
        assertThat(TEN.getIntValue()).isEqualTo(10);
        assertThat(TEN.getPowerOfType()).isEqualTo(PowerOfType.TEN);
        assertThat(TEN.getNextLowestPowerOfTen()).isEqualTo(ONE);
        assertThat(TEN.getNextHighestDigit()).isEqualTo(FIFTY);
    }

    @Test
    public void fiftyMapsToCorrectValues() {
        assertThat(FIFTY.getNumeralValue()).isEqualTo("L");
        assertThat(FIFTY.getIntValue()).isEqualTo(50);
        assertThat(FIFTY.getPowerOfType()).isEqualTo(PowerOfType.FIVE);
        assertThat(FIFTY.getNextLowestPowerOfTen()).isEqualTo(TEN);
        assertThat(FIFTY.getNextHighestDigit()).isEqualTo(ONE_HUNDRED);
    }

    @Test
    public void oneHundredMapsToCorrectValues() {
        assertThat(ONE_HUNDRED.getNumeralValue()).isEqualTo("C");
        assertThat(ONE_HUNDRED.getIntValue()).isEqualTo(100);
        assertThat(ONE_HUNDRED.getPowerOfType()).isEqualTo(PowerOfType.TEN);
        assertThat(ONE_HUNDRED.getNextLowestPowerOfTen()).isEqualTo(TEN);
        assertThat(ONE_HUNDRED.getNextHighestDigit()).isEqualTo(FIVE_HUNDRED);
    }

    @Test
    public void fiveHundredMapsToCorrectValues() {
        assertThat(FIVE_HUNDRED.getNumeralValue()).isEqualTo("D");
        assertThat(FIVE_HUNDRED.getIntValue()).isEqualTo(500);
        assertThat(FIVE_HUNDRED.getPowerOfType()).isEqualTo(PowerOfType.FIVE);
        assertThat(FIVE_HUNDRED.getNextLowestPowerOfTen()).isEqualTo(ONE_HUNDRED);
        assertThat(FIVE_HUNDRED.getNextHighestDigit()).isEqualTo(ONE_THOUSAND);
    }

    @Test
    public void oneThousandMapsToCorrectValues() {
        assertThat(ONE_THOUSAND.getNumeralValue()).isEqualTo("M");
        assertThat(ONE_THOUSAND.getIntValue()).isEqualTo(1000);
        assertThat(ONE_THOUSAND.getPowerOfType()).isEqualTo(PowerOfType.TEN);
        assertThat(ONE_THOUSAND.getNextLowestPowerOfTen()).isEqualTo(ONE_HUNDRED);
        assertThat(ONE_THOUSAND.getNextHighestDigit()).isEqualTo(null);
    }

    @Test
    public void parseStringReturnsCorrectDigit() {
        assertThat(RomanDigit.parseString("I")).isEqualTo(ONE);
        assertThat(RomanDigit.parseString("V")).isEqualTo(FIVE);
        assertThat(RomanDigit.parseString("X")).isEqualTo(TEN);
        assertThat(RomanDigit.parseString("L")).isEqualTo(FIFTY);
        assertThat(RomanDigit.parseString("C")).isEqualTo(ONE_HUNDRED);
        assertThat(RomanDigit.parseString("D")).isEqualTo(FIVE_HUNDRED);
        assertThat(RomanDigit.parseString("M")).isEqualTo(ONE_THOUSAND);
        assertThat(RomanDigit.parseString("Z")).isEqualTo(null);
    }

    @Test
    public void parseIReturns1() {
        assertThat(parseStringToInt("I")).isEqualTo(1);
    }

    @Test
    public void parseVReturns5() {
        assertThat(parseStringToInt("V")).isEqualTo(5);
    }

    @Test
    public void parseXReturns10() {
        assertThat(parseStringToInt("X")).isEqualTo(10);
    }

    @Test
    public void parseLReturns50() {
        assertThat(parseStringToInt("L")).isEqualTo(50);
    }

    @Test
    public void parseCReturns100() {
        assertThat(parseStringToInt("C")).isEqualTo(100);
    }

    @Test
    public void parseDReturns500() {
        assertThat(parseStringToInt("D")).isEqualTo(500);
    }

    @Test
    public void parseMReturns1000() {
        assertThat(parseStringToInt("M")).isEqualTo(1000);
    }

    @Test
    public void descendingOrderReturnsValuesWithLargestFirst() {
        final RomanDigit[] romanDigits = valuesByDescendingOrder();
        assertThat(romanDigits.length).isEqualTo(values().length);
        assertThat(romanDigits[0]).isEqualTo(ONE_THOUSAND);
        assertThat(romanDigits[1]).isEqualTo(FIVE_HUNDRED);
        assertThat(romanDigits[2]).isEqualTo(ONE_HUNDRED);
        assertThat(romanDigits[3]).isEqualTo(FIFTY);
        assertThat(romanDigits[4]).isEqualTo(TEN);
        assertThat(romanDigits[5]).isEqualTo(FIVE);
        assertThat(romanDigits[6]).isEqualTo(ONE);
    }

    @Test
    public void getNextHighestDigitReturnsCorrectValues() {
        assertThat(getNextHighestDigit(ONE_THOUSAND)).isEqualTo(ONE_THOUSAND);
        assertThat(getNextHighestDigit(FIVE_HUNDRED)).isEqualTo(ONE_THOUSAND);
        assertThat(getNextHighestDigit(ONE_HUNDRED)).isEqualTo(FIVE_HUNDRED);
        assertThat(getNextHighestDigit(FIFTY)).isEqualTo(ONE_HUNDRED);
        assertThat(getNextHighestDigit(TEN)).isEqualTo(FIFTY);
        assertThat(getNextHighestDigit(FIVE)).isEqualTo(TEN);
        assertThat(getNextHighestDigit(ONE)).isEqualTo(FIVE);
    }
}