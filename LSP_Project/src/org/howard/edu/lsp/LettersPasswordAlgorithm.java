package org.howard.edu.lsp.finale.question1;

import java.security.SecureRandom;

/**
 * Password algorithm that generates passwords containing letters only
 * (A–Z, a–z).
 */
public class LettersPasswordAlgorithm implements PasswordAlgorithm {

    private static final String LETTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "abcdefghijklmnopqrstuvwxyz";

    private final SecureRandom secureRandom = new SecureRandom();

    @Override
    public String generate(int length) {
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(LETTERS.length());
            builder.append(LETTERS.charAt(index));
        }
        return builder.toString();
    }
}
