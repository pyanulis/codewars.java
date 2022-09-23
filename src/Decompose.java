import java.util.Stack;

public class Decompose {

    public String decompose(long n) {

        if (n < 5) return null;

        Stack<Long> stack = new Stack<Long>();

        long s = n * n;
        long d = 1;
        while (s != 0) {
            long r = (long) Math.floor(Math.sqrt(s - d));
            if (!stack.isEmpty() && stack.peek() <= r) {
                r = stack.pop();
                s += r * r;
                if (stack.isEmpty()) {
                    return null;
                }
                r = stack.pop();
                s += r * r;
                --r;
            }
            s -= r * r;
            stack.push(r);
            d = 0;
        }

        if (stack.isEmpty()) return null;

        String res = "";
        while (!stack.isEmpty()){
            res += stack.pop() + " ";
        }

        return res.trim();
    }
}