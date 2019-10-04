import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Acyclicity {
    private static int acyclic(ArrayList<Integer>[] adj) {
        boolean seen[] = new boolean[adj.length];
        boolean stack[] = new boolean[adj.length];

        for(int i=0; i<adj.length; ++i){
            if(!seen[i])
                if(dfs(adj, i, seen, stack))
                    return 1;
        }
        return 0;
    }

    private static boolean dfs(ArrayList<Integer>[] adj, int i, boolean[] seen, boolean[] stack) {
        if(stack[i])
            return true;
        if(seen[i])
            return false;

        stack[i] = true;
        seen[i] = true;

        for(int k=0; k<adj[i].size(); ++k){
            if(dfs(adj, adj[i].get(k), seen, stack))
                return true;
        }
        stack[i] = false;
        return false;
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
        }
        System.out.println(acyclic(adj));
    }
}

