import java.util.ArrayList;
import java.util.Scanner;

public class Reachability {
    private static int reach(ArrayList<Integer>[] adj, int x, int y) {
        boolean seen[] = new boolean[1001];
        if(dfs(adj, x, y, seen))
            return 1;
        return 0;
    }
    private static boolean  dfs(ArrayList<Integer>[] adj, int x, int y, boolean [] seen){
        if(x == y){
            return true;
        }
        boolean ans = false;
        seen[x] = true;
        for(int i=0; i<adj[x].size(); ++i){
            if(!seen[adj[x].get(i)]){
                ans =  dfs(adj, adj[x].get(i), y, seen);
            }
        }
        return ans;
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
        System.out.println(reach(adj, x, y));
    }
}

