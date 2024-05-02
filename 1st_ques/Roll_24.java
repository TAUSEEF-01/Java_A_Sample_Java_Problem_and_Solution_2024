import java.util.*;

public class Roll_24 {

    static boolean vis[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        solve(sc);
    }

    static void solve(Scanner sc) {
        int n, m, k;
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();

        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] adj = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int u, v;
            u = sc.nextInt();
            v = sc.nextInt();

            adj[u].add(v);
            adj[v].add(u);
        }

        int islands = 0;
        vis = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            vis[i] = false;
        }

        for (int i = 1; i <= n; i++) {
            if (!vis[i]) {
                dfs(i, adj);
                islands++;
            } else {
                continue;
            }
        }

        int ans = islands * (islands - 1) / 2 - k;

        System.out.println(ans);
    }

    static void dfs(int child, ArrayList<Integer>[] adj) {
        vis[child] = true;
        for (var u : adj[child]) {
            if (vis[u] == false) {
                dfs(u, adj);
            }
        }
    }

}


/*
test case 1:
10 7 2
1 3
2 3
4 5
7 5
5 4
6 8
9 8
answer: 4


test case 2:
13 7 2
5 4
5 11
7 12
1 8
2 8
6 8
10 9
answer: 13


test case 3:
20 12 4
1 2 
4 5
6 2 
7 5 
9 1
12 14
14 15
10 20
18 20
13 3
7 3
3 15
answer: 24


test case 4:
25 5 5
1 2
2 4 
5 7 
7 2
10 1
answer: 185


test case 5:
100 23 50
1 3 
4 2
5 7
3 8
9 10
16 44
44 88
26 56
64 34
28 97
20 31
48 83
12 48
33 77
90 2
29 49
61 81
61 7
64 33
26 99
44 55
97 7
31 53
answer: 2876

 */