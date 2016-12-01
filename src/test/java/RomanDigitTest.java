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
    public void parseIReturns1() {
        assertThat(RomanDigit.parseNumeral("I")).isEqualTo(1);
    }

    @Test
    public void parseIReturns5() {
        assertThat(RomanDigit.parseNumeral("V")).isEqualTo(5);
    }

    @Test
    public void parseIReturns10() {
        assertThat(RomanDigit.parseNumeral("X")).isEqualTo(10);
    }
}