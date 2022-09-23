import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrayPeaks {
    public static Map<String, List<Integer>> getPeaks(int[] arr) {

        List<Integer> positions = new ArrayList<Integer>();
        List<Integer> peaks = new ArrayList<Integer>();
        Map<String, List<Integer>> map = new HashMap<String, List<Integer>>() {{
            put("pos", positions);
            put("peaks", peaks);
        }};

        if (arr.length < 2) return map;

        int index = 0;
        int p = arr[index];
        boolean rising = false;
        boolean plato = false;
        for (int i = index + 1; i < arr.length; i++) {
            plato = p == arr[i];

            if (p < arr[i]){
                p = arr[i];
                index = i;
                rising = true;
                continue;
            }
            else if (!plato){
                if (rising) {
                    positions.add(index);
                    peaks.add(p);
                }
                rising = false;
                p = arr[i];
                index = i;
                continue;
            }
        }

        /*if (plato){
            positions.remove(positions.size() - 1);
            peaks.remove(peaks.size() - 1);
        }*/

        return map;
    }
}
