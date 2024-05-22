package org.example;

import org.apache.commons.codec.binary.Base64;
import java.math.BigInteger;

public class OutputFormatter {
    public static String toBase2(BigInteger number) {
        return number.toString(2);
    }

    public static String toBase10(BigInteger number) {
        return number.toString();
    }

    public static String toBase64(BigInteger number) {
        byte[] bytes = number.toByteArray();
        return Base64.encodeBase64String(bytes);
    }

    public static byte[] toByteArray(BigInteger number) {
        return number.toByteArray();
    }
}
