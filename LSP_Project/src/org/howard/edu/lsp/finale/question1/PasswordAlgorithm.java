package org.howard.edu.lsp.finale.question1;

/**
 * Strategy interface for password generation algorithms.
 */
public interface PasswordAlgorithm {
    /**
     * Generate a password using this algorithm.
     *
     * @param length the desired length of the password
     * @return the generated password string
     */
    String generate(int length);
}
