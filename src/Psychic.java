public class Psychic {

    private static long m_seed = -1;
    private static boolean m_inited;

    private static long c_multiplier = 0x5DEECE66DL;
    private static long c_addend = 0xBL;
    private static long c_modulus = (1L << 48) - 1;
    private static final long c_divisor = (1L << 53);

    public static double guess() {

        if (!m_inited) {
            double r = Math.random();
            m_seed = findPrevSeed(r);

            m_inited = true;
        }
        return nextDouble();
    }

    private static long findPrevSeed(double random) {
        long l = (long) (random * c_divisor);

        //seed has 48 bits
        //numerator has 53 bits where 26 leftmost are from seed 1 (1st call of next())
        // and 27 rightmost are from seed 2 (2nd call of next())
        final int c_lowHalf = 27;
        final int c_shift1 = 22;
        final int c_shift2 = 21;

        long s1 = (l >> c_lowHalf) << c_shift1;

        long s2 = (l & ((1L << c_lowHalf) - 1)) << c_shift2;

        for (int i = 0; i <= (1 << c_shift1) - 1; ++i) {
            long guess = s1 + i;
            long next = nextSeed(guess);
            if (((next >> c_shift2) ^ (s2 >> c_shift2)) == 0) {
                return nextSeed(guess);
            }
        }

        return -1;
    }

    protected static synchronized long nextSeed(long seed) {
        return (seed * c_multiplier + c_addend) & c_modulus;
    }

    protected static synchronized int next(int bits) {
        m_seed = nextSeed(m_seed);
        return (int) (m_seed >>> (48 - bits));
    }

    public static double nextDouble() {
        return (((long) next(26) << 27) + next(27)) / (double) (1L << 53);
    }
}