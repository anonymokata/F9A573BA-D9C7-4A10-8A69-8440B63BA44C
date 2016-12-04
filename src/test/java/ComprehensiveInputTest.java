import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ComprehensiveInputTest {

    private static final String FILE_NAME = "NumeralsWithIntValues.csv";
    private static final Map<String, Integer> NUMERALS_WITH_VALUES = new HashMap<>();

    @BeforeClass
    public static void setup() throws Exception {
        final URL systemResourceAsStream = ClassLoader.getSystemResource(FILE_NAME);
        final Path filePath = Paths.get(systemResourceAsStream.getPath());
        final List<String> fileAsLines = Files.readAllLines(filePath);
        for(final String line : fileAsLines) {
            final String[] splitLine = line.split(",");
            final String numeral = splitLine[0];
            final String arabicValue = splitLine[1];
            NUMERALS_WITH_VALUES.put(numeral, Integer.parseInt(arabicValue));
        }
    }

    @Test
    public void intToNumeralConverterHandlesAllValidNumbers() {
        final IntToRomanNumeralConverter converter = new IntToRomanNumeralConverter();

        for(final String numeral : NUMERALS_WITH_VALUES.keySet()) {
            final Integer integer = NUMERALS_WITH_VALUES.get(numeral);
            assertThat(converter.convertToRomanNumeral(integer)).isEqualTo(numeral);
        }
    }

    @Test
    public void numeralToIntConverterHandlesAllValidNumbers() {
        final RomanNumeralToIntConverter converter = new RomanNumeralToIntConverter();
        
        for(final String numeral : NUMERALS_WITH_VALUES.keySet()) {
            final Integer integer = NUMERALS_WITH_VALUES.get(numeral);
            assertThat(converter.convertToInt(numeral)).isPresent().contains(integer);
        }
    }
}