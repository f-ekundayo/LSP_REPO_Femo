package org.howard.edu.lsp.finale.question1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit tests for PasswordGeneratorService.
 */
public class PasswordGeneratorServiceTest {

    private PasswordGeneratorService service;

    @BeforeEach
    public void setup() {
        service = PasswordGeneratorService.getInstance();
    }

    @Test
    public void checkInstanceNotNull() {
        // verify that 'service' is not null
        assertNotNull(service, "getInstance() should never return null.");
    }

    @Test
    public void checkSingleInstanceBehavior() {
        PasswordGeneratorService second = PasswordGeneratorService.getInstance();

        // Verify that both 'service' (created in @BeforeEach)
        // and 'second' refer to the EXACT same object in memory.
        assertSame(service, second,
                "getInstance() must return the same singleton instance each time.");
    }

    @Test
    public void generateWithoutSettingAlgorithmThrowsException() {
        PasswordGeneratorService s = PasswordGeneratorService.getInstance();

        // We expect an IllegalStateException if no algorithm has been selected
        assertThrows(IllegalStateException.class,
                () -> s.generatePassword(8),
                "Calling generatePassword() without selecting an algorithm "
                        + "should throw IllegalStateException.");
    }

    @Test
    public void basicAlgorithmGeneratesCorrectLengthAndDigitsOnly() {
        service.setAlgorithm("basic");
        String p = service.generatePassword(10);

        assertEquals(10, p.length(),
                "Basic algorithm should generate a password of the requested length.");

        // Verify that all characters are digits
        assertTrue(
                p.chars().allMatch(Character::isDigit),
                "Basic algorithm should generate digits only (0–9).");
    }

    @Test
    public void enhancedAlgorithmGeneratesCorrectCharactersAndLength() {
        service.setAlgorithm("enhanced");
        String p = service.generatePassword(12);

        assertEquals(12, p.length(),
                "Enhanced algorithm should generate a password of the requested length.");

        // Verify that all characters are alphanumeric (A–Z, a–z, 0–9)
        assertTrue(
                p.chars().allMatch(ch -> Character.isLetterOrDigit((char) ch)),
                "Enhanced algorithm should use only letters and digits (A–Z, a–z, 0–9).");
    }

    @Test
    public void lettersAlgorithmGeneratesLettersOnly() {
        service.setAlgorithm("letters");
        String p = service.generatePassword(8);

        assertEquals(8, p.length(),
                "Letters algorithm should generate a password of the requested length.");

        // Verify that all characters are letters
        assertTrue(
                p.chars().allMatch(ch -> Character.isLetter((char) ch)),
                "Letters algorithm should generate letters only (A–Z, a–z).");
    }

    @Test
    public void switchingAlgorithmsChangesBehavior() {
        service.setAlgorithm("basic");
        String p1 = service.generatePassword(10);

        service.setAlgorithm("letters");
        String p2 = service.generatePassword(10);

        service.setAlgorithm("enhanced");
        String p3 = service.generatePassword(10);

        // basic: digits only
        assertEquals(10, p1.length());
        assertTrue(
                p1.chars().allMatch(Character::isDigit),
                "Basic algorithm should produce digits only.");

        // letters: letters only
        assertEquals(10, p2.length());
        assertTrue(
                p2.chars().allMatch(ch -> Character.isLetter((char) ch)),
                "Letters algorithm should produce letters only.");

        // enhanced: alphanumeric
        assertEquals(10, p3.length());
        assertTrue(
                p3.chars().allMatch(ch -> Character.isLetterOrDigit((char) ch)),
                "Enhanced algorithm should produce alphanumeric characters only.");
    }
}
