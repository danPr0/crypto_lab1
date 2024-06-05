package org.example;

import java.math.BigInteger;

public class BaillieUtils {

    private static boolean millerRabinBase2(BigInteger n) {
        if (n.equals(BigInteger.valueOf(2))) return true;
        if (n.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) return false;

        BigInteger d = n.subtract(BigInteger.ONE);
        int s = 0;
        while (d.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
            d = d.divide(BigInteger.valueOf(2));
            s++;
        }

        BigInteger a = BigInteger.valueOf(2);
        BigInteger x = a.modPow(d, n);
        if (x.equals(BigInteger.ONE) || x.equals(n.subtract(BigInteger.ONE))) return true;

        for (int r = 0; r < s - 1; r++) {
            x = x.modPow(BigInteger.valueOf(2), n);
            if (x.equals(BigInteger.ONE)) return false;
            if (x.equals(n.subtract(BigInteger.ONE))) return true;
        }

        return false;
    }

    private static int jacobi(BigInteger a, BigInteger n) {
        if (n.equals(BigInteger.ONE)) return 1;
        a = a.mod(n);
        if (a.equals(BigInteger.ZERO)) return 0;

        int e = 0;
        while (a.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
            a = a.divide(BigInteger.valueOf(2));
            e++;
        }

        int s = (e % 2 == 0) ? 1 : (n.mod(BigInteger.valueOf(8)).equals(BigInteger.ONE) || n.mod(BigInteger.valueOf(8)).equals(BigInteger.valueOf(7))) ? 1 : -1;

        if (n.mod(BigInteger.valueOf(4)).equals(BigInteger.valueOf(3)) && a.mod(BigInteger.valueOf(4)).equals(BigInteger.valueOf(3))) {
            s = -s;
        }

        if (a.equals(BigInteger.ONE)) return s;
        return s * jacobi(n.mod(a), a);
    }

    private static BigInteger[] lucasSequence(BigInteger P, BigInteger Q, BigInteger n) {
        BigInteger U = BigInteger.ZERO;
        BigInteger V = BigInteger.valueOf(2);
        BigInteger k = n.add(BigInteger.ONE);

        BigInteger D = P.multiply(P).subtract(Q.multiply(BigInteger.valueOf(4)));
        BigInteger[] result = new BigInteger[]{U, V};

        for (int i = k.bitLength() - 1; i >= 0; i--) {
            U = U.multiply(V).mod(n);
            V = V.multiply(V).subtract(D.multiply(BigInteger.valueOf(2).mod(n))).mod(n);

            if (k.testBit(i)) {
                BigInteger temp = U;
                U = P.multiply(U).add(V).mod(n).divide(BigInteger.valueOf(2));
                V = D.multiply(temp).add(V.multiply(P)).mod(n).divide(BigInteger.valueOf(2));
            }
        }

        return result;
    }

    private static boolean strongLucasTest(BigInteger n) {
        if (n.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) return n.equals(BigInteger.valueOf(2));

        BigInteger D = BigInteger.valueOf(5);
        while (jacobi(D, n) != -1) {
            D = D.negate().add((D.compareTo(BigInteger.ZERO) < 0) ? BigInteger.ONE : BigInteger.valueOf(2));
        }

        BigInteger P = BigInteger.ONE;
        BigInteger Q = (BigInteger.ONE.subtract(D)).divide(BigInteger.valueOf(4));

        BigInteger[] lucas = lucasSequence(P, Q, n);
        return lucas[0].equals(BigInteger.ZERO) || lucas[1].equals(BigInteger.ZERO);
    }

    public static boolean isPrime(BigInteger n) {
        return millerRabinBase2(n) && strongLucasTest(n);
    }
}
