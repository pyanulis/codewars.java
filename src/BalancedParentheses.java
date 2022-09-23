import java.util.ArrayList;
import java.util.List;

public class BalancedParentheses {
    public static List<String> balancedParens (int n) {

        if (n < 0) return null;
        if (n == 0) return new ArrayList<>() {{
            add("");
        }};
        if (n == 1) return new ArrayList<>() {{
            add("()");
        }};

        List<String> list = new ArrayList<>();

        // "(" -> 0
        // ")" -> 1
        int min = (int) Math.pow(2, n) - 1; // min number: 000111

        n *= 2;
        int max = (int) Math.pow(2, n) - 1 - min - 1; // max number format: 111..000, the second (-1) is take out leading 1 which is ")"

        for (int i = min; i < max; i++) {
            if (checkBit(i, n - 1)) continue; // skip starting with 1
            if (i % 2 == 0) continue; // skip ending with 0

            checkBalance(i, n, list);
        }

        return list;
    }

    private static void checkBalance(int n, int size, List<String> list){

        StringBuilder sb = new StringBuilder();
        int counter = 0;
        for (int i = size - 1; i >= 0; --i) {

            boolean one = checkBit(n, i);
            if (one){
                if (counter == 0) return; // 1s > 0s
                sb.append(')');
                --counter;
            }
            else{
                sb.append('(');
                ++counter;
            }
        }

        if (counter == 0){
            list.add(sb.toString());
        }
    }

    private static boolean checkBit(int num, int bit){
        return (num & (1 << bit)) == (1 << bit);
    }

    public static ArrayList <String> balancedParensSb (int n) {
        ArrayList<String> lst = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(sb,lst,0,0,n);
        return lst;
    }

    private static void dfs(StringBuilder sb, ArrayList<String> lst, int open, int close, int max) {
        if (close==max) {
            lst.add(sb.toString());
            return;
        }
        if (open>close)  {
            sb.append(')');
            dfs(sb,lst,open,close+1,max);
            sb.deleteCharAt(sb.length()-1);
        }
        if (open<max) {
            sb.append('(');
            dfs(sb,lst,open+1,close,max);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
