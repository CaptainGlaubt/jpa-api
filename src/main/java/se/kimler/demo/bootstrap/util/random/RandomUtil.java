package se.kimler.demo.bootstrap.util.random;

import java.security.SecureRandom;
import java.util.Random;

public final class RandomUtil {

    private static final Random RANDOM = new SecureRandom();

    private RandomUtil() {
        // Prevent instantiation.
    }

    public static int getRandomInteger(int lowerBound, int upperBound) {
        assert lowerBound < upperBound;
        return RANDOM.nextInt(upperBound - lowerBound) + lowerBound;
    }
}
