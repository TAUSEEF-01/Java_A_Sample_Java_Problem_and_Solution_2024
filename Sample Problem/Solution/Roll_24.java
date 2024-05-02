import java.util.*;

public class Roll_24 {

    static boolean vis[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        solve(sc);

        sc.close();
    }

    static void solve(Scanner sc) {
        int n, k, x;
        n = sc.nextInt();
        k = sc.nextInt();
        x = sc.nextInt();

        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] adj = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        String s[] = new String[n];
        int sn[] = new int[n];

        String temp = sc.nextLine();

        for (int i = 0; i < n; i++) {
            String str = sc.nextLine();

            String[] parts = str.split(" ");

            if (parts.length == 2) {
                String ss = parts[0];
                int val = Integer.parseInt(parts[1]);

                s[i] = ss;
                sn[i] = val;
            } else {
                System.out.println(i);
                System.out.println("Invalid input format. Please enter in the format 'text number'.");
                return;
            }
        }

        for (int i = 0; i < n; i++) {
            int u, v;
            u = sn[i];

            if (s[i].length() < k)
                continue;

            for (int j = i + 1; j < n; j++) {
                v = sn[j];

                if (s[j].length() < k)
                    continue;

                if (is_substring(s[i], s[j]) == true) {
                    adj[u].add(v);
                    adj[v].add(u);
                }
            }
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

        int ans = islands * (islands - 1) / 2 - x;

        System.out.println(max_val(ans, 0));
    }

    static boolean is_substring(String a, String b) {
        boolean flag = false;
        int n = a.length();
        int m = b.length();

        if (n < m) {
            int temp = n;
            n = m;
            m = temp;

            String stemp = a;
            a = b;
            b = stemp;
        }

        for (int i = 0; i <= n - m; i++) {
            boolean check = true;
            int l = i;

            for (int j = 0; j < m; j++) {
                if (a.charAt(l) != b.charAt(j)) {
                    check = false;
                    break;
                }
                l++;
            }
            if (check == true) {
                flag = true;
                break;
            }
        }

        return flag;
    }

    static int max_val(int a, int b) {
        if (a > b)
            return a;
        else
            return b;
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
