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
    public void tenMapsToXAnd10() {
        assertThat(RomanDigit.TEN.getNumeralValue()).isEqualTo("X");
        assertThat(RomanDigit.TEN.getIntValue()).isEqualTo(10);
    }
}