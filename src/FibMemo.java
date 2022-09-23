import java.math.BigInteger;

public class FibMemo {

    public static BigInteger fib(BigInteger N) {
        BigInteger a = BigInteger.valueOf(0);
        BigInteger b = BigInteger.valueOf(1);
        BigInteger x = BigInteger.valueOf(0);
        BigInteger y = BigInteger.valueOf(1);

        boolean isNegative = N.compareTo(BigInteger.ZERO) == -1;
        BigInteger n = isNegative ? N.negate() : N;

        while (n != BigInteger.ZERO)
        {
            if (n.mod(BigInteger.TWO) == BigInteger.ZERO)
            {
                BigInteger al = a;
                a = al.multiply(al).add(b.multiply(b));
                b = al.multiply(b).add(b.multiply(al)).add(b.multiply(b));
                n = n.divide(BigInteger.TWO);
            }
            else
            {
                BigInteger xl = x;
                x = a.multiply(x).add(b.multiply(y));
                y = b.multiply(xl).add(a.multiply(y)).add(b.multiply(y));
                n = n.subtract(BigInteger.ONE);
            }
        }
        if (isNegative && N.mod(BigInteger.TWO) == BigInteger.ZERO)
        {
            x = x.negate();
        }
        return x;
    }
}
