import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bipartite {
    private static int bipartite(ArrayList<Integer>[] adj) {
        int color[] = new int[adj.length];
        for(int i=0; i<adj.length; ++i)
            color[i] = -1;
        Queue<Integer> queue = new LinkedList<>();
        ((LinkedList<Integer>) queue).add(0);
        color[0] = 1;
        while (!queue.isEmpty()){

            int v = queue.poll();

            for(int i=0; i<adj[v].size(); ++i){
                if(color[adj[v].get(i)] == -1){
                    queue.add(adj[v].get(i));
                    color[adj[v].get(i)] = 1 - color[v];
                }
                else if(color[adj[v].get(i)] == color[v])
                    return 0;
            }
        }
        return 1;
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
        System.out.println(bipartite(adj));
    }
}

