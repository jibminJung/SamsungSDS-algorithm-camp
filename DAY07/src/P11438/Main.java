package P11438;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<ArrayList<Integer>> map = new ArrayList<>();
    static int[][] lca;
    static int[] depth;
    static int n, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n + 1; i++) {
            map.add(new ArrayList<>());
        }
        int size = 1;
        k = 0;
        while (size < n) {
            size *= 2;
            k++;
        }
        lca = new int[n + 1][k + 1];
        depth = new int[n + 1];
        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            map.get(from).add(to);
            map.get(to).add(from);
        }
        //initialize depth using bfs;
        Queue<Integer> q = new LinkedList<>();
        depth[1] = 1;
        q.offer(1);
        while (!q.isEmpty()) {
            int now = q.poll();
            for (int next :
                    map.get(now)) {
                if (depth[next] == 0) {
                    depth[next] = depth[now] + 1;
                    lca[next][0] = now;
                    q.offer(next);
                }
            }
        }
        //calculate lca array
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j < n + 1; j++) {
                lca[j][i] = lca[lca[j][i - 1]][i - 1];
            }
        }

        int m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(getLca(a, b)).append('\n');
        }
        System.out.println(sb);
    }

    static int getLca(int a, int b) {
        if (depth[a] < depth[b]) {//a가 더 깊도록
            return getLca(b, a);
        }
        for (int i = 0; i<=k; i++) { // a를 끌어올린다.
            if (((depth[a] - depth[b]) & (1 << i)) >= 1) {
                a = lca[a][i];
            }
        }
        if (a == b) return a;
        for (int i = k; i >= 0; i--) {
            if (lca[a][i] != lca[b][i]) {
                a = lca[a][i];
                b = lca[b][i];
            }
        }
        return lca[a][0];
    }
}
