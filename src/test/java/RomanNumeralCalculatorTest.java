import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RomanNumeralCalculatorTest {

    @Test
    public void adding1and1Returns2() {
        final RomanNumeralCalculator calculator = new RomanNumeralCalculator();
        final String sum = calculator.add("I", "I");
        assertThat(sum).isEqualTo("II");
    }

    @Test
    public void adding2and1Returns3() {
        final RomanNumeralCalculator calculator = new RomanNumeralCalculator();
        final String sum = calculator.add("II", "I");
        assertThat(sum).isEqualTo("III");
    }
}