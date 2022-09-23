import java.util.ArrayList;
import java.util.List;

public class Prime {
    //finds k-prime numbers in the given range
    //k-prime: number which has k prime factors
    public static long[] getKPrimes(int k, long start, long end){
        List<Long> kprimes = new ArrayList<>();

        for (long n = start; n <= end; n++) {
            if (isKPrime(n, k)) kprimes.add(n);
        }

        long[] lkprimes = kprimes.stream().mapToLong(l -> l).toArray();

        return lkprimes;
    }

    public static long[] gap(int gap, long start, long end) {
        long[] primes = getKPrimes(1, start, end);
        if (primes.length < 2) return null;

        long l1 = primes[0];
        for (int i = 1; i < primes.length; i++) {
            if (primes[i] - l1 == gap) return new long[] {l1, primes[i]};
            l1 = primes[i];
        }

        return null;
    }

    //counts number of solutions where s = 1-prime + 3-prime + 7-prime
    public static int sumCount(int s) {
        long[] primes1 = getKPrimes(1, 0, s);
        long[] primes3 = getKPrimes(3, 0, s);
        long[] primes7 = getKPrimes(7, 0, s);

        int count = 0;
        for (long p7 : primes7) {
            for (long p3 : primes3) {
                for (long p1 : primes1) {
                    if (p1 == s - p7 - p3){
                        count++;
                        break;
                    }
                }
            }
        }
        return count;
    }

    private static boolean isKPrime(long num, int k) {
        int primes = 0;
        for (long p = 2; p * p <= num && primes < k; ++p) {
            while (num % p == 0) {
                num /= p;
                primes++;
            }
        }

        if (num > 1){
            primes++;
        }

        return primes == k;
    }
}
