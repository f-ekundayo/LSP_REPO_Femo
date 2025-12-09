package org.howard.edu.lsp.finale.question1;

/**
 * Service responsible for generating password strings using different
 * pluggable algorithms. This class is implemented as a Singleton and
 * uses the Strategy pattern to support multiple password-generation
 * behaviors.
 */
public class PasswordGeneratorService {

    /**
     * Design Pattern Documentation
     * -----------------------------
     *
     * 1. Patterns used:
     *    - Singleton
     *    - Strategy
     *
     * 2. Why these patterns are appropriate:
     *    - Singleton:
     *      The requirements specify that only one instance of the
     *      service may exist and that there should be a single shared
     *      access point. Implementing this class as a Singleton ensures
     *      that all clients use the same instance via getInstance(),
     *      enforcing a single, shared object that manages password
     *      generation for the entire application.
     *
     *    - Strategy:
     *      The requirements also state that the system must support
     *      multiple password-generation approaches, allow the caller
     *      to select an approach at run time, and support future
     *      expansion of these approaches while keeping the behavior
     *      swappable. Defining a PasswordAlgorithm interface and
     *      multiple concrete implementations (basic, enhanced,
     *      letters) encapsulates each algorithm as a separate Strategy.
     *      The service maintains a reference to a PasswordAlgorithm
     *      and delegates generation to it. Client code only calls
     *      setAlgorithm(String) and generatePassword(int), so new
     *      algorithms can be added later without modifying existing
     *      client code — they simply plug in an additional Strategy.
     */

    /** The single, shared instance of the service. */
    private static final PasswordGeneratorService INSTANCE =
            new PasswordGeneratorService();

    /** The currently selected password-generation algorithm (Strategy). */
    private PasswordAlgorithm algorithm;

    /**
     * Private constructor to prevent external instantiation.
     */
    private PasswordGeneratorService() {
        // Intentionally empty — use getInstance() to obtain the service.
    }

    /**
     * Returns the single instance of the PasswordGeneratorService.
     *
     * @return the singleton instance of this service
     */
    public static PasswordGeneratorService getInstance() {
        return INSTANCE;
    }

    /**
     * Selects the password-generation algorithm to use.
     *
     * @param name the name of the algorithm ("basic", "enhanced", or "letters")
     * @throws IllegalArgumentException if the algorithm name is null or unsupported
     */
    public synchronized void setAlgorithm(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Algorithm name must not be null.");
        }

        String normalized = name.toLowerCase();
        switch (normalized) {
            case "basic":
                this.algorithm = new BasicPasswordAlgorithm();
                break;
            case "enhanced":
                this.algorithm = new EnhancedPasswordAlgorithm();
                break;
            case "letters":
                this.algorithm = new LettersPasswordAlgorithm();
                break;
            default:
                throw new IllegalArgumentException("Unsupported algorithm: " + name);
        }
    }

    /**
     * Generates a password of the specified length using the currently
     * selected algorithm.
     *
     * @param length the desired password length (must be non-negative)
     * @return the generated password string
     * @throws IllegalStateException    if no algorithm has been selected
     * @throws IllegalArgumentException if length is negative
     */
    public synchronized String generatePassword(int length) {
        if (algorithm == null) {
            throw new IllegalStateException(
                    "No password-generation algorithm has been selected.");
        }
        if (length < 0) {
            throw new IllegalArgumentException("Password length must be non-negative.");
        }
        return algorithm.generate(length);
    }
}
