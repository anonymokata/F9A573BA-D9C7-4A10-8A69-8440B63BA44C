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
    }

    @Test
    public void fiveMapsToCorrectValues() {
        assertThat(FIVE.getNumeralValue()).isEqualTo("V");
        assertThat(FIVE.getIntValue()).isEqualTo(5);
        assertThat(FIVE.getPowerOfType()).isEqualTo(PowerOfType.FIVE);
    }

    @Test
    public void tenMapsToCorrectValues() {
        assertThat(TEN.getNumeralValue()).isEqualTo("X");
        assertThat(TEN.getIntValue()).isEqualTo(10);
        assertThat(TEN.getPowerOfType()).isEqualTo(PowerOfType.TEN);
    }

    @Test
    public void fiftyMapsToCorrectValues() {
        assertThat(FIFTY.getNumeralValue()).isEqualTo("L");
        assertThat(FIFTY.getIntValue()).isEqualTo(50);
        assertThat(FIFTY.getPowerOfType()).isEqualTo(PowerOfType.FIVE);
    }

    @Test
    public void oneHundredMapsToCorrectValues() {
        assertThat(ONE_HUNDRED.getNumeralValue()).isEqualTo("C");
        assertThat(ONE_HUNDRED.getIntValue()).isEqualTo(100);
        assertThat(ONE_HUNDRED.getPowerOfType()).isEqualTo(PowerOfType.TEN);
    }

    @Test
    public void fiveHundredMapsToCorrectValues() {
        assertThat(FIVE_HUNDRED.getNumeralValue()).isEqualTo("D");
        assertThat(FIVE_HUNDRED.getIntValue()).isEqualTo(500);
        assertThat(FIVE_HUNDRED.getPowerOfType()).isEqualTo(PowerOfType.FIVE);
    }

    @Test
    public void oneThousandMapsToCorrectValues() {
        assertThat(ONE_THOUSAND.getNumeralValue()).isEqualTo("M");
        assertThat(ONE_THOUSAND.getIntValue()).isEqualTo(1000);
        assertThat(ONE_THOUSAND.getPowerOfType()).isEqualTo(PowerOfType.TEN);
    }

    @Test
    public void parseIReturns1() {
        assertThat(parseNumeral("I")).isEqualTo(1);
    }

    @Test
    public void parseVReturns5() {
        assertThat(parseNumeral("V")).isEqualTo(5);
    }

    @Test
    public void parseXReturns10() {
        assertThat(parseNumeral("X")).isEqualTo(10);
    }

    @Test
    public void parseLReturns50() {
        assertThat(parseNumeral("L")).isEqualTo(50);
    }

    @Test
    public void parseCReturns100() {
        assertThat(parseNumeral("C")).isEqualTo(100);
    }

    @Test
    public void parseDReturns500() {
        assertThat(parseNumeral("D")).isEqualTo(500);
    }

    @Test
    public void parseMReturns1000() {
        assertThat(parseNumeral("M")).isEqualTo(1000);
    }

    @Test
    public void identifiesPowersOfTen() {
        assertThat(numeralIsPowerOfTen("I")).isTrue();
        assertThat(numeralIsPowerOfTen("X")).isTrue();
        assertThat(numeralIsPowerOfTen("C")).isTrue();
        assertThat(numeralIsPowerOfTen("M")).isTrue();
    }

    @Test
    public void identifiesPowersOfFive() {
        assertThat(numeralIsPowerOfTen("V")).isFalse();
        assertThat(numeralIsPowerOfTen("L")).isFalse();
        assertThat(numeralIsPowerOfTen("D")).isFalse();
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
    public void getNextLowestPowerOfTenReturnsCorrectValues() {
        assertThat(getNextLowestPowerOfTen(ONE_THOUSAND)).isEqualTo(ONE_HUNDRED);
        assertThat(getNextLowestPowerOfTen(FIVE_HUNDRED)).isEqualTo(ONE_HUNDRED);
        assertThat(getNextLowestPowerOfTen(ONE_HUNDRED)).isEqualTo(TEN);
        assertThat(getNextLowestPowerOfTen(FIFTY)).isEqualTo(TEN);
        assertThat(getNextLowestPowerOfTen(TEN)).isEqualTo(ONE);
        assertThat(getNextLowestPowerOfTen(FIVE)).isEqualTo(ONE);
        assertThat(getNextLowestPowerOfTen(ONE)).isEqualTo(ONE);
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