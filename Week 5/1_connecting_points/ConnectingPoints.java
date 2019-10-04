import java.util.*;

public class ConnectingPoints {
    private static Map<Point, Point> map = new HashMap<>();


    static class Point{
        int x, y, rank;
        Point parent;

    }

    static class Edge implements Comparable<Edge>{

        Point p1, p2;

        double dis;

        public Edge(Point p1, Point p2, double dis) {
            this.p1 = p1;
            this.p2 = p2;
            this.dis = dis;
        }

        @Override
        public int compareTo(Edge o) {

            if(this.dis < o.dis)
                return -1;
            else if(this.dis > o.dis)
                return 1;
            else
                return 0;
        }
    }

    public static Point makeSet(int x, int y){
        Point point = new Point();
        point.x = x;
        point.y = y;
        point.rank = 0;
        point.parent = point;
        map.put(point, point);
        return point;
    }

    public static boolean union(Point p1, Point p2) {
        Point parentOfP1 = map.get(p1);
        Point parentOfP2 = map.get(p2);

        Point parent1 = findSet(parentOfP1);
        Point parent2 = findSet(parentOfP2);

        if (parent1.x == parent2.x && parent1.y == parent2.y ) {
            return false;
        }

        if (parent1.rank >= parent2.rank) {
            parent1.rank = (parent1.rank == parent2.rank) ? parent1.rank + 1 : parent1.rank;
            parent2.parent = parent1;
        } else {
            parent1.parent = parent2;
        }
        return true;
    }
    private static Point findSet(Point point) {
        Point parent = point.parent;
        if (parent == point) {
            return parent;
        }
        point.parent = findSet(point.parent);
        return point.parent;
    }


    private static double minimumDistance(int[] x, int[] y) {
        List<Point> points = new ArrayList<>();

        PriorityQueue<Edge> edges = new PriorityQueue<>();

        for(int i=0; i<x.length; ++i){
            points.add(makeSet(x[i], y[i]));
        }
        for(int i=0; i<points.size(); ++i){
            Point p1 = points.get(i);
            int x1 = p1.x, y1 = p1.y;
            for(int j=i+1; j<points.size(); ++j){
                Point p2 = points.get(j);
                int x2 = p2.x, y2 = p2.y;
                double dis = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
                edges.add(new Edge(p1, p2, dis));

            }
        }

        double result = 0.;

        while (!edges.isEmpty()){
            Edge edge = edges.poll();
            Point p1 = edge.p1, p2 = edge.p2;
            if(findSet(p1) != findSet(p2)){
                result+=edge.dis;
                union(p1, p2);
            }
        }
        return result;
    }



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        System.out.println(minimumDistance(x, y));
    }
}

