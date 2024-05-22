import static org.junit.jupiter.api.Assertions.*;

import org.example.Utils;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

public class PrimeTest {
    @Test
    void testIsPrimeMethod() {
        assertTrue(Utils.isPrime(new BigInteger("38291080145259975415465649338692025031137934711681"), 20));
        assertTrue(Utils.isPrime(new BigInteger("35758816890673462038699519765739674075761481332297"), 20));
        assertTrue(Utils.isPrime(new BigInteger("86275531863544003879881610491710483497700852004829"), 20));
        assertTrue(Utils.isPrime(new BigInteger("65667734093642264957810014445938144217847628501337"), 20));
        assertTrue(Utils.isPrime(new BigInteger("10896062607711940443738244090106076669560260646177"), 20));

        assertFalse(Utils.isPrime(BigInteger.valueOf(337 * 999), 1));
        assertFalse(Utils.isPrime(BigInteger.valueOf(3769 * 89), 1));
        assertFalse(Utils.isPrime(BigInteger.valueOf(5443 * 45), 1));
        assertFalse(Utils.isPrime(BigInteger.valueOf(29 * 4567), 1));
        assertFalse(Utils.isPrime(BigInteger.valueOf(7881 * 7889), 1));
    }
}
