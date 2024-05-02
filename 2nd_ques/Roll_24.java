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

                // System.out.println(s[i] + ' ' + s[j]);

                if (is_substring(s[i], s[j]) == true) {
                    // System.out.println(i + " " + j);
                    // System.out.println(s[i] + ' ' + s[j]);

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

        // System.out.println(islands);

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

        // System.out.println(n + " " + m);
        // System.out.print(a + ' ' + b + ' ');
        // System.out.println(a + " " + b);

        for (int i = 0; i <= n - m; i++) {
            boolean check = true;
            int l = i;

            for (int j = 0; j < m; j++) {
                if (a.charAt(l) != b.charAt(j)) {
                    // System.out.println(a.charAt(l) + " " + b.charAt(j) + " " + l + " " + j);
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

        // System.out.println(flag);

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










/*
test case 1:
10 3 2
abca 3
abc 1
bca 5
xyzaa 6
x 10
yza 2
aaaaa 4
uba 7
oleo 8
ole 9
answer: 13


test case 2:
13 2 8
abcd 1
cd 3
ab 2
xyz 10
yzcd 5
asdf 6
sd 8
ghjk 7
hj 4
poiu 9
iu 2 
vbnm 11
vbn 13
answer: 7


test case 3:
20 5 4
ghfjghu 1
jsfs 2
jdsfs 3
mmocmco 11
sdkfishduhf 5
djfeqqaa 6
asfcfs 20
dfkgg 9
tyririreoo 12
mnvcbcmnbc 13
lplojrwyaav 14
fhgneor 15
dgskdghs 18
alloawtwerrwrwr 17
dsjfhs 4
ababsssbbbbbb 7
edfkjeiofuev 8
lorooportpe 10
uqequgeq 19
skfgeuf 16
answer: 186


test case 4:
7 2 0
aac 1
gjhh 2
kgr 3
fhfg 5
fhfg 7
aacf 4
kgr 6
answer: 6


test case 5:
7 2 54
aac 1
gjhh 2
kgr 3
fhfg 5
fhfg 7
aacf 4
kgr 6
answer: 0

*/