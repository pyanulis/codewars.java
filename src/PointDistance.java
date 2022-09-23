import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class PointDistance {

    private static HashMap<Double, List<Point>> m_distances = new HashMap<Double, List<Point>>();

    public static List<Point> closestPair(List<Point> points) {
        points.sort(Comparator.comparing((Point p) -> p.x));

        return m_distances.get(getMin(points));
    }

    private static double getMin(List<Point> pxList){
        if (pxList.size() == 1) {
            return Double.MAX_VALUE;
        }

        int mid = pxList.size()/2;

        List<Point> left = pxList.subList(0, mid);
        List<Point> right = pxList.subList(mid, pxList.size());
        Point pMid = pxList.get(mid - 1);

        Double d = Math.min(getMin(left), getMin(right));
        List<Point> strip = new ArrayList<Point>();
        for (Point p :
                pxList) {
            if (Math.abs(p.x - pMid.x) < d){
                strip.add(p);
            }
        }

        return strip(strip, pMid, d);
    }

    private static double strip(List<Point> stripList, Point mid, double minD){
        stripList.sort(Comparator.comparing((Point p) -> p.y));

        double minStrip = minD;
        for (int i = 0; i < stripList.size(); ++i){
            for (int j = i + 1; j < stripList.size(); ++j){
                if (Math.abs(stripList.get(i).y - stripList.get(j).y) < minStrip){
                    double dist = getDistance(stripList.get(i), stripList.get(j));
                    if (dist < minStrip){
                        minStrip = dist;
                        addPair(minStrip, stripList.get(i), stripList.get(j));
                    }
                }
            }
        }

        return Math.min(minStrip, minD);
    }

    private static void addPair(double dist, Point p1, Point p2){
        List<Point> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        m_distances.put(dist, list);
    }

    private static double getDistance(Point p1, Point p2){
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    private class PointPair{
        public Point P1;
        public Point P2;
    }
}
