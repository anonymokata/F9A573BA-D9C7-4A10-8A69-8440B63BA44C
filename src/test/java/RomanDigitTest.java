import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RomanDigitTest {

    @Test
    public void oneMapsToIAnd1() {
        assertThat(RomanDigit.ONE.getNumeralValue()).isEqualTo("I");
        assertThat(RomanDigit.ONE.getIntValue()).isEqualTo(1);
    }

    @Test
    public void fiveMapsToVAnd5() {
        assertThat(RomanDigit.FIVE.getNumeralValue()).isEqualTo("V");
        assertThat(RomanDigit.FIVE.getIntValue()).isEqualTo(5);
    }

    @Test
    public void tenMapsToX() {
        assertThat(RomanDigit.TEN.getNumeralValue()).isEqualTo("X");
        assertThat(RomanDigit.TEN.getIntValue()).isEqualTo(10);
    }

    @Test
    public void fiftyMapsToL() {
        assertThat(RomanDigit.FIFTY.getNumeralValue()).isEqualTo("L");
        assertThat(RomanDigit.FIFTY.getIntValue()).isEqualTo(50);
    }

    @Test
    public void oneHundredMapsToC() {
        assertThat(RomanDigit.ONE_HUNDRED.getNumeralValue()).isEqualTo("C");
        assertThat(RomanDigit.ONE_HUNDRED.getIntValue()).isEqualTo(100);
    }

    @Test
    public void fiveHundredMapsToD() {
        assertThat(RomanDigit.FIVE_HUNDRED.getNumeralValue()).isEqualTo("D");
        assertThat(RomanDigit.FIVE_HUNDRED.getIntValue()).isEqualTo(500);
    }

    @Test
    public void oneThousandMapsToM() {
        assertThat(RomanDigit.ONE_THOUSAND.getNumeralValue()).isEqualTo("M");
        assertThat(RomanDigit.ONE_THOUSAND.getIntValue()).isEqualTo(1000);
    }

    @Test
    public void parseIReturns1() {
        assertThat(RomanDigit.parseNumeral("I")).isEqualTo(1);
    }

    @Test
    public void parseVReturns5() {
        assertThat(RomanDigit.parseNumeral("V")).isEqualTo(5);
    }

    @Test
    public void parseXReturns10() {
        assertThat(RomanDigit.parseNumeral("X")).isEqualTo(10);
    }

    @Test
    public void parseLReturns50() {
        assertThat(RomanDigit.parseNumeral("L")).isEqualTo(50);
    }

    @Test
    public void parseCReturns100() {
        assertThat(RomanDigit.parseNumeral("C")).isEqualTo(100);
    }

    @Test
    public void parseDReturns500() {
        assertThat(RomanDigit.parseNumeral("D")).isEqualTo(500);
    }

    @Test
    public void parseMReturns1000() {
        assertThat(RomanDigit.parseNumeral("M")).isEqualTo(1000);
    }

    @Test
    public void identifiesPowersOfTen() {
        assertThat(RomanDigit.numeralIsPowerOfTen("I")).isTrue();
        assertThat(RomanDigit.numeralIsPowerOfTen("X")).isTrue();
        assertThat(RomanDigit.numeralIsPowerOfTen("C")).isTrue();
        assertThat(RomanDigit.numeralIsPowerOfTen("M")).isTrue();
    }

    @Test
    public void identifiesPowersOfFive() {
        assertThat(RomanDigit.numeralIsPowerOfTen("V")).isFalse();
        assertThat(RomanDigit.numeralIsPowerOfTen("L")).isFalse();
        assertThat(RomanDigit.numeralIsPowerOfTen("D")).isFalse();
    }

    @Test
    public void descendingOrderReturnsValuesWithLargestFirst() {
        final RomanDigit[] romanDigits = RomanDigit.valuesByDescendingOrder();
        assertThat(romanDigits.length).isEqualTo(RomanDigit.values().length);
        assertThat(romanDigits[0]).isEqualTo(RomanDigit.ONE_THOUSAND);
        assertThat(romanDigits[1]).isEqualTo(RomanDigit.FIVE_HUNDRED);
        assertThat(romanDigits[2]).isEqualTo(RomanDigit.ONE_HUNDRED);
        assertThat(romanDigits[3]).isEqualTo(RomanDigit.FIFTY);
        assertThat(romanDigits[4]).isEqualTo(RomanDigit.TEN);
        assertThat(romanDigits[5]).isEqualTo(RomanDigit.FIVE);
        assertThat(romanDigits[6]).isEqualTo(RomanDigit.ONE);
    }
}