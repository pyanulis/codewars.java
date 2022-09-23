import java.util.Arrays;
import java.util.List;

public class ConnectFour {

    private static final int c_intervalDiag1 = 6;
    private static final int c_intervalHor = 7;
    private static final int c_intervalDiag2 = 8;
    private static final int c_intervalVert = 1;
    private static final int c_maxHeightIndex = 6;

    private static long m_redBoard;
    private static long m_yellowBoard;

    private static int[] m_columnFreeIndex = new int[7];

    public static String whoIsWinner(List<String> moveList){
        m_redBoard = 0;
        m_yellowBoard = 0;

        Arrays.fill(m_columnFreeIndex, 0);

        int moveNum = 1;
        for (String move : moveList) {
            String[] parts = move.split("_");
            int column = (int) parts[0].charAt(0) - 65;

            if (parts[1].equals("Red")){
                addToRed(column);
            }
            else{
                addToYellow(column);
            }

            if (moveNum >= 7){
                if (isWinner(m_redBoard)) {
                    return "Red";
                }
                if (isWinner(m_yellowBoard)) {
                    return "Yellow";
                }
            }

            ++moveNum;
        }
        return "Draw";
    }

    private static void addToRed(int column){
        m_redBoard |= 1L << (column * c_maxHeightIndex + m_columnFreeIndex[column] + column);
        ++m_columnFreeIndex[column];
    }

    private static void addToYellow(int column){
        m_yellowBoard |= 1L << (column * c_maxHeightIndex + m_columnFreeIndex[column] + column);
        ++m_columnFreeIndex[column];
    }

    private static final boolean isWinner(long board) {

        long n = board & (board >> c_intervalDiag1);
        if ((n & (n >> c_intervalDiag1 * 2)) != 0)
            return true;

        n = board & (board >> c_intervalDiag2);
        if ((n & (n >> c_intervalDiag2 * 2)) != 0)
            return true;

        n = board & (board >> c_intervalHor);
        if ((n & (n >> c_intervalHor * 2)) != 0)
            return true;

        n = board & (board >> c_intervalVert);
        return (n & (n >> c_intervalVert * 2)) != 0;
    }

    public static void printBoard(long board){

        for (int i = 5; i >= 0; --i){
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 7; ++j){
                int x = i + 6 * j;
                char c = (board & (1L << x)) > 0?'1':'0';
                sb.append(c);
            }
            System.out.println(sb.toString());
        }
    }

    public static void printBitboardLine(long bitboard){
        System.out.println(String.format("%64s", Long.toBinaryString(bitboard)).replace(' ', '0'));
    }

    public static void logDiag2Check(long bitboard){
        printBitboardLine(bitboard);
        printBitboardLine(bitboard >> c_intervalDiag2);

        long n = bitboard & (bitboard >> c_intervalDiag2);

        printBitboardLine(n);
        printBitboardLine(n >> 2 * c_intervalDiag2);

        n = n & (n >> 2 * c_intervalDiag2);
        printBitboardLine(n);

        System.out.println(n != 0);
    }
}
