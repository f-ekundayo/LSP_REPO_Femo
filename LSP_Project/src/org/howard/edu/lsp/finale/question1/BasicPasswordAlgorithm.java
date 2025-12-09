package org.howard.edu.lsp.finale.question1;

import java.util.Random;

/**
 * Basic password algorithm that generates passwords containing digits only (0–9)
 * using java.util.Random.
 */
public class BasicPasswordAlgorithm implements PasswordAlgorithm {

    private static final String DIGITS = "0123456789";
    private final Random random = new Random();

    @Override
    public String generate(int length) {
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(DIGITS.length());
            builder.append(DIGITS.charAt(index));
        }
        return builder.toString();
    }
}
