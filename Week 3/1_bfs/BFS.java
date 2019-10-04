    import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS {
    private static int distance(ArrayList<Integer>[] adj, int s, int t) {
        if(s == t )
            return -1;
        boolean seen[] = new boolean[adj.length];
        int depth[] = new int[adj.length];
        depth[s] = 0;
        seen[s] = true;

        Queue<Integer> queue = new LinkedList<>();
        ((LinkedList<Integer>) queue).add(s);
        while (!queue.isEmpty()){
            int v = queue.poll();

            for(int i=0; i<adj[v].size(); ++i){
                if(!seen[adj[v].get(i)]){
                    ((LinkedList<Integer>) queue).add(adj[v].get(i));
                    seen[adj[v].get(i)] = true;
                    depth[adj[v].get(i)] = depth[v]+1;

                }
                if(t == adj[v].get(i))
                    return depth[adj[v].get(i)];


            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, x, y));
    }
}

