package cn.pasteme.backend.util;

import java.util.Random;

/**
 * @author Moyu
 * @version 1.0.0
 */
public class TempKeyGenerator {

    public static final int CURRENT_LENGTH = 8;

    private static char[] param = new char[] { 'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
            't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
            'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
            'W', 'X', 'Y', 'Z' };

    private TempKeyGenerator() {}

    public static String generator(int length) {
        char[] sequence = new char[length];
        for (int i = 0; i < length; i++) {
            sequence[i] = param[randomIndex(param.length) - 1];
        }
        return new String(sequence);
    }

    private static int randomIndex(int max) {
        Random random = new Random();
        return random.nextInt(max) % (max + 1);
    }
}
