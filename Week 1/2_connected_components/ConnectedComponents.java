import java.util.ArrayList;
import java.util.Scanner;

public class ConnectedComponents {
    public static int N;

    private static int numberOfComponents(ArrayList<Integer>[] adj) {
        int result = 0;
        boolean seen[] = new boolean[1001];
        for(int i=0; i<N; ++i){
            if(!seen[i]){
                dfs(adj, i,seen);
                result+=1;
            }
        }
        return result;
    }
    private static void  dfs(ArrayList<Integer>[] adj, int x, boolean [] seen){

        seen[x] = true;
        for(int i=0; i<adj[x].size(); ++i){
            if(!seen[adj[x].get(i)]){
                dfs(adj, adj[x].get(i),  seen);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        N = n;
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
        System.out.println(numberOfComponents(adj));
    }
}
