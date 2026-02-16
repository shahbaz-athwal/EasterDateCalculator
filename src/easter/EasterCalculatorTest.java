package easter;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Equivalence Classes:
 *   EC1 (Valid):   1900 <= year <= 2100
 *   EC2 (Invalid): year < 1900 -> returns null
 *   EC3 (Invalid): year > 2100 -> returns null
 *   EC4 (Valid - Special): year in {1954, 1981, 2049, 2076} -> dateValue reduced by 7
 *   EC5 (Valid - Normal):  valid year NOT in the special set
 *   EC6 (Valid - March):   dateValue <= 31, Easter in March
 *   EC7 (Valid - April):   dateValue > 31, Easter in April
 *
 * Boundary Values:
 *   BV1 = 1899,
 *   BV2 = 1900, 
 *   BV3 = 1901,
 *   BV4 = 2099,
 *   BV5 = 2100,
 *   BV6 = 2101,
 */
public class EasterCalculatorTest {

    // Test Case 1: year = 1899 (invalid, below range)
    // Covers: EC2, BV1
    // Expected: null
    @Test
    public void testYearBelowRange_1899() {
        assertNull(EasterCalculator.easterDate(1899),
                "Year 1899 is below valid range; expected null");
    }

    // Test Case 2: year = 1900 (lower boundary, normal year, April result)
    // Covers: EC1, EC5, EC7, BV2
    // Expected: April 15
    @Test
    public void testLowerBoundary_1900() {
        MyDate result = EasterCalculator.easterDate(1900);
        assertNotNull(result, "Year 1900 is valid; should not return null");
        assertEquals("April 15", result.toString());
    }

    // Test Case 3: year = 1901 (just above lower boundary, normal year, April)
    // Covers: EC1, EC5, EC7, BV3
    // Expected: April 7
    @Test
    public void testJustAboveLowerBoundary_1901() {
        MyDate result = EasterCalculator.easterDate(1901);
        assertNotNull(result, "Year 1901 is valid; should not return null");
        assertEquals("April 7", result.toString());
    }

    // Test Case 4: year = 2099 (just below upper boundary, normal year, April)
    // Covers: EC1, EC5, EC7, BV4
    // Expected: April 12
    @Test
    public void testJustBelowUpperBoundary_2099() {
        MyDate result = EasterCalculator.easterDate(2099);
        assertNotNull(result, "Year 2099 is valid; should not return null");
        assertEquals("April 12", result.toString());
    }

    // Test Case 5: year = 2100 (upper boundary, normal year, March result)
    // Covers: EC1, EC5, EC6, BV5
    // Expected: March 27
    @Test
    public void testUpperBoundary_2100() {
        MyDate result = EasterCalculator.easterDate(2100);
        assertNotNull(result, "Year 2100 is valid; should not return null");
        assertEquals("March 27", result.toString());
    }

    // Test Case 6: year = 2101 (invalid, above range)
    // Covers: EC3, BV6
    // Expected: null
    @Test
    public void testYearAboveRange_2101() {
        assertNull(EasterCalculator.easterDate(2101),
                "Year 2101 is above valid range; expected null");
    }

    // Test Case 7: year = 1954 (special correction year)
    // Covers: EC1, EC4, EC7
    // Expected: April 18
    @Test
    public void testSpecialCorrectionYear_1954() {
        MyDate result = EasterCalculator.easterDate(1954);
        assertNotNull(result, "Year 1954 is valid; should not return null");
        assertEquals("April 18", result.toString());
    }

    // Test Case 8: year = 1981 (special correction year)
    // Covers: EC1, EC4, EC7
    // Expected: April 19
    @Test
    public void testSpecialCorrectionYear_1981() {
        MyDate result = EasterCalculator.easterDate(1981);
        assertNotNull(result, "Year 1981 is valid; should not return null");
        assertEquals("April 19", result.toString());
    }

    // Test Case 9: year = 2049 (special correction year)
    // Covers: EC1, EC4, EC7
    // Expected: April 18
    @Test
    public void testSpecialCorrectionYear_2049() {
        MyDate result = EasterCalculator.easterDate(2049);
        assertNotNull(result, "Year 2049 is valid; should not return null");
        assertEquals("April 18", result.toString());
    }

    // Test Case 10: year = 2076 (special correction year)
    // Covers: EC1, EC4, EC7
    // Expected: April 19
    @Test
    public void testSpecialCorrectionYear_2076() {
        MyDate result = EasterCalculator.easterDate(2076);
        assertNotNull(result, "Year 2076 is valid; should not return null");
        assertEquals("April 19", result.toString());
    }

    // Test Case 11: year = 2008 (normal year, March result)
    // Covers: EC1, EC5, EC6
    // Expected: March 23
    @Test
    public void testNormalYear_March_2008() {
        MyDate result = EasterCalculator.easterDate(2008);
        assertNotNull(result, "Year 2008 is valid; should not return null");
        assertEquals("March 23", result.toString());
    }

    // Test Case 12: year = 2000 (normal year, April result)
    // Covers: EC1, EC5, EC7
    // Expected: April 23
    @Test
    public void testNormalYear_April_2000() {
        MyDate result = EasterCalculator.easterDate(2000);
        assertNotNull(result, "Year 2000 is valid; should not return null");
        assertEquals("April 23", result.toString());
    }
}
