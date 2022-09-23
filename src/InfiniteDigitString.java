public class InfiniteDigitString {
    public static long findPosition(final String substring) {
        int pos = 0;
        String increment = "";
        for (Long i = 1L; i < Long.MAX_VALUE; i++)
        {
            String newNum = i.toString();
            increment += newNum;
            if (increment.length() > (substring.length()*2))
            {
                int offset = increment.length() - (substring.length() * 2);
                increment = increment.substring(offset);
                pos += offset;
            }
            if (increment.contains(substring))
            {
                return pos + increment.indexOf(substring);
            }
        }

        return -1;
    }
}
