import java.util.*;

public class StronglyConnected {
    private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj) {

        ArrayList<Integer>[] reversed = reverseGraph(adj);
        Stack<Integer> finish = new Stack<>();
        boolean seen[] = new boolean[adj.length];
        
        for(int i=0; i<adj.length; ++i){
            if(!seen[i])
                order(i, seen, adj, finish);
        }

        for(int i=0; i<adj.length; ++i)
            seen[i] = false;

        int cnt = 0;
        while (!finish.isEmpty()){
            int v = finish.pop();
            if(!seen[v]){
                dfs(reversed, v, seen);
                ++cnt;
            }
        }
        return cnt;
    }



    static void dfs(ArrayList<Integer>[] adj, int v, boolean seen[]) {
        seen[v] = true;
        for (int i = 0; i < adj[v].size(); ++i) {
            int child = adj[v].get(i);
            if (!seen[child]) {
                dfs(adj, child, seen);
            }
        }
    }
    static ArrayList<Integer>[] reverseGraph(ArrayList<Integer>[]adj){

        ArrayList<Integer>[] newAdj = (ArrayList<Integer>[])new ArrayList[adj.length];
        for (int i = 0; i < adj.length; i++) {
            newAdj[i] = new ArrayList<Integer>();
        }

        for(int i=0; i<adj.length; ++i){

            for(int j=0; j<adj[i].size(); ++j){
                newAdj[adj[i].get(j)].add(i);
            }
        }

        return newAdj;
    }

    static void order(int v, boolean seen[], ArrayList<Integer>[] adj, Stack<Integer> finish){
        seen[v] = true;

        for(int i=0; i<adj[v].size(); ++i)
            if(!seen[adj[v].get(i)])
                order(adj[v].get(i), seen, adj, finish);

        finish.push(v);
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
        System.out.println(numberOfStronglyConnectedComponents(adj));
    }
}

