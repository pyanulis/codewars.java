import java.util.ArrayList;
import java.util.List;

public class DoubleLinear {
    private static List<Integer> m_set;
    private static List<Integer> m_unprocessed;

    public static int dblLinear(int n) {
        m_set = new ArrayList<Integer>();
        m_set.add(1);

        m_unprocessed = m_set;

        boolean edge = false;
        while (!edge) {
            edge = true;
            List<Integer> buffer = new ArrayList<Integer>();

            for (int j : m_unprocessed) {

                buffer.add(getY(j));
                buffer.add(getZ(j));
            }

            m_unprocessed = new ArrayList<Integer>();
            for (int b : buffer) {
                int index = insert(0, m_set.size() - 1, b, n);
                edge = edge && (index == -1 || index > n);
            }
        }

        return m_set.get(n);
    }

    private static int insert(int start, int end, int n, int max){
        if (end < start){
            if (start == -1){
                m_set.add(end, n);
                m_unprocessed.add(n);
                return end;
            }
            if (start <= max){
                m_set.add(start, n);
                m_unprocessed.add(n);
                return start;
            }
            return -1;
        }
        int middle = (start + end) / 2;

        if (n == m_set.get(middle)) return -1;

        if (n > m_set.get(middle)) return insert(middle + 1, end, n, max);

        return insert(start, middle - 1, n, max);
    }

    private static int getY(int n) {
        return n * 2 + 1;
    }

    private static int getZ(int n) {
        return n * 3 + 1;
    }
}
