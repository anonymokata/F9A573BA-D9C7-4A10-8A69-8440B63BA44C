import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RomanDigitTest {

    @Test
    public void oneMapsToI() {
        assertThat(RomanDigit.ONE.getNumeralValue()).isEqualTo("I");
    }

    @Test
    public void fiveMapsToV() {
        assertThat(RomanDigit.FIVE.getNumeralValue()).isEqualTo("V");
    }

    @Test
    public void tenMapsToX() {
        assertThat(RomanDigit.TEN.getNumeralValue()).isEqualTo("X");
    }

    @Test
    public void fiftyMapsToL() {
        assertThat(RomanDigit.FIFTY.getNumeralValue()).isEqualTo("L");
    }

    @Test
    public void oneHundredMapsToC() {
        assertThat(RomanDigit.ONE_HUNDRED.getNumeralValue()).isEqualTo("C");
    }

    @Test
    public void fiveHundredMapsToD() {
        assertThat(RomanDigit.FIVE_HUNDRED.getNumeralValue()).isEqualTo("D");
    }

    @Test
    public void oneThousandMapsToM() {
        assertThat(RomanDigit.ONE_THOUSAND.getNumeralValue()).isEqualTo("M");
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
}