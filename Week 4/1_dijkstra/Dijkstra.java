import java.util.*;

public class Dijkstra {

    static final int INF = Integer.MAX_VALUE;

    static class node implements Comparable<node>{
        int vertex;
        int distance;

        public node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(node o) {
            if(this.distance < o.distance)
                return -1;
            if(this.distance > o.distance)
                return 1;
            else
                return 0;
        }
    }
    private static int distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {

        PriorityQueue<node> p = new PriorityQueue<node>();

        int dep[] = new int[adj.length];
        for(int i=0; i<dep.length; ++i)
            dep[i] = INF;

        dep[s] = 0;
        p.add(new node(s, dep[s]));

        while (!p.isEmpty()){
            node n = p.poll();
            int vertex = n.vertex, distance = n.distance;
            for(int i=0; i<adj[vertex].size(); ++i){
                int current = adj[vertex].get(i);
                int index = adj[vertex].indexOf(current);
                if(dep[current] > dep[vertex] + cost[vertex].get(index)){
                    dep[current] = dep[vertex] + cost[vertex].get(index);
                    p.add(new node(current, dep[vertex] + cost[vertex].get(index)));
                }
            }
        }
        if(dep[t] == INF)
            return -1;

        return dep[t];



    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, cost, x, y));
    }
}