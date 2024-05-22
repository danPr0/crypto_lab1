package org.example;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Utils {
    private static final SecureRandom random = new SecureRandom();

    public static BigInteger generatePrime(int numBits) {
        BigInteger prime;
        do {
            prime = new BigInteger(numBits, random);
        } while (!isPrime(prime, 20));
        return prime;
    }

    public static boolean isPrime(BigInteger n, int k) {
        if (n.compareTo(BigInteger.TWO) <= 0) {
            return false;
        }
        if (n.equals(BigInteger.TWO) || n.equals(BigInteger.valueOf(3))) {
            return true;
        }
        if (n.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            return false;
        }

        BigInteger d = n.subtract(BigInteger.ONE);
        while (d.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            d = d.divide(BigInteger.TWO);
        }

        for (int i = 0; i < k; i++) {
            if (!millerTest(n, d)) {
                return false;
            }
        }

        return true;
    }

    private static boolean millerTest(BigInteger n, BigInteger d) {
        BigInteger a = BigInteger.TWO.add(new BigInteger(n.bitLength() - 2, random));
        BigInteger x = Utils.modExp(a, d, n);
        if (x.equals(BigInteger.ONE) || x.equals(n.subtract(BigInteger.ONE))) {
            return true;
        }

        while (!d.equals(n.subtract(BigInteger.ONE))) {
            x = Utils.modExp(x, BigInteger.TWO, n);
            d = d.multiply(BigInteger.TWO);
            if (x.equals(BigInteger.ONE)) {
                return false;
            }
            if (x.equals(n.subtract(BigInteger.ONE))) {
                return true;
            }
        }

        return false;
    }

    public static BigInteger modExp(BigInteger base, BigInteger exp, BigInteger mod) {
        BigInteger result = BigInteger.ONE;
        base = base.mod(mod);
        while (exp.compareTo(BigInteger.ZERO) > 0) {
            if (exp.and(BigInteger.ONE).equals(BigInteger.ONE)) {
                result = result.multiply(base).mod(mod);
            }
            base = base.multiply(base).mod(mod);
            exp = exp.shiftRight(1);
        }
        return result;
    }
}
