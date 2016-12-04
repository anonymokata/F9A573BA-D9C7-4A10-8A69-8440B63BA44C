package converter;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class RomanNumeralConverterTest {

    @Test
    public void delegatesConversionDuties() {
        final NumeralToIntegerConverter mockNumeralToIntConverter = mock(NumeralToIntegerConverter.class);
        final IntegerToNumeralConverter mockIntToNumeralConverter = mock(IntegerToNumeralConverter.class);
        final RomanNumeralConverter converter = new RomanNumeralConverter(mockNumeralToIntConverter, mockIntToNumeralConverter);

        converter.toInteger("CDI");
        verify(mockNumeralToIntConverter).convert(eq("CDI"));

        converter.toNumeral(3243);
        verify(mockIntToNumeralConverter).convert(eq(3243));
    }
}