package org.example;

import java.math.BigInteger;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        BigInteger n = Utils.generatePrime(128);
        System.out.println("Generated prime in base2: " + OutputFormatter.toBase2(n));
        System.out.println("Generated prime in base10: " + OutputFormatter.toBase10(n));
        System.out.println("Generated prime in base64: " + OutputFormatter.toBase64(n));
        System.out.println("Generated prime in base[]: " + Arrays.toString(OutputFormatter.toByteArray(n)));
    }
}
