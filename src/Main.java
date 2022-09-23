import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<Point> points = Arrays.asList(
                new Point(2, 2), //A
                new Point(2, 8), //B
                new Point(5, 5), //C
                new Point(6, 3), //D
                new Point(6, 7), //E
                new Point(7, 4), //F
                new Point(7, 9)  //G
        );

        List<Point> pointsReal = Arrays.asList(
                new Point(51.088666, 153.635029), //A
                new Point(51.621162, 152.265044), //B
                new Point(47.287940, 154.338925)
        );

        List<Point> pointsTwo = Arrays.asList(
                new Point(2, 2), //A
                new Point(2, 8) //B
        );

        //List<Point> list = PointDistance.closestPair(pointsReal);
        //ScreenLockPatternFinder finder = new ScreenLockPatternFinder();
        //int count = finder.calculateCombinations('E', 8);
        //System.out.println(count);
        //System.out.println(list.get(0).toString() + " " + list.get(1).toString());

        System.out.println(InfiniteDigitString.findPosition("123456798"));
    }

    private static void guess(int count){
        for (int i = 0; i < count; i++) {
            System.out.println(Psychic.guess());
            System.out.println(Math.random());
        }
    }

    private static void decompose(int n){
        Decompose d = new Decompose();
        System.out.println(d.decompose(n));
    }

    private static void line2(){
        long s = System.currentTimeMillis();
        System.out.println(dblLinear(60000));
        long e = System.currentTimeMillis();
        System.out.println(e - s);
    }

    private static void line(){
        long s = System.currentTimeMillis();
        System.out.println(DoubleLinear.dblLinear(60000));
        long e = System.currentTimeMillis();
        System.out.println(e - s);
    }

    public static int dblLinear(int n) {
        if (n == 0) return 1;
        SortedSet<Integer> u = new TreeSet<>();
        u.add(1);
        for(int i=0; i<n; i++) {
            int x = u.first();
            u.add(x*2+1);
            u.add(x*3+1);
            u.remove(x);
        }
        return u.first();
    }

    private static void sortInsert(){
        List<Integer> list = new ArrayList<>();

        int index = insert(list, 0, list.size() - 1, 6);

        System.out.println(index);
    }

    private static int insert(List<Integer> list, int start, int end, int n){
        //System.out.println(n);
        if (end < start){
            if (start == -1){
                list.add(end, n);
                return end;
            }
            list.add(start, n);
            return start;
        }
        int middle = (start + end) / 2;

        if (n == list.get(middle)) return -1;

        if (n > list.get(middle)) return insert(list,middle + 1, end, n);

        return insert(list, start, middle - 1, n);
    }

    private static void printBPSb(int n) {
        List<String> list = BalancedParentheses.balancedParensSb(n);
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println();
    }

    private static void printBP(int n) {
        List<String> list = BalancedParentheses.balancedParens(n);
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println();
    }

    private static boolean parity(int v){
        boolean parity = false;
        while (v > 0)
        {
            parity = !parity;
            v = v & (v - 1);
        }

        return parity;
    }

    public static void printMegaBytesAnfKiloBytes(int kiloBytes){
        String result = "";
        if (kiloBytes < 0){
            result = "Invalid value";
        }
        else{
            int mb = kiloBytes / 1024;
            int kb = kiloBytes % 1024;

            result = kiloBytes + " KB = " + mb + " MB and " + kb + " KB";
        }

        System.out.println(result);
    }

    public static boolean bark(boolean barking, int hourOfDay){
        return (barking && hourOfDay >= 0 && hourOfDay <= 23 && hourOfDay > 22 && hourOfDay < 8);
    }
}
