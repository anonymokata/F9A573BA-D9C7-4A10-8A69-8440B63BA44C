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
}