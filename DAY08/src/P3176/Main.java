package P3176;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<ArrayList<Edge>> map = new ArrayList<>();
    static int[][] parent;
    static int[] depth;
    static int[][] minCost, maxCost;
    static int n, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        getK();
        parent = new int[n + 1][k + 1];
        minCost = new int[n + 1][k + 1];
        maxCost = new int[n + 1][k + 1];
        depth = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            map.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            map.get(from).add(new Edge(to, cost));
            map.get(to).add(new Edge(from, cost));
        }
        //get depth of nodes and cost from node 1
        getDepthAndCost();
        //make sparse table;
        getSparseTable();

        StringBuilder sb = new StringBuilder();
        int m = Integer.parseInt(br.readLine());
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int[] ans = getLca(a, b);
            sb.append(ans[0]).append(' ').append(ans[1]).append('\n');
        }
        System.out.println(sb);


    }

    static void getK() {
        k = 0;
        for (int i = 1; i < n; i *= 2) {
            k++;
        }
    }

    static void getDepthAndCost() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        depth[1] = 1;
        while (!q.isEmpty()) {
            int now = q.poll();
            for (Edge next :
                    map.get(now)) {
                if (depth[next.to] == 0) {
                    q.offer(next.to);
                    depth[next.to] = depth[now] + 1;
                    minCost[next.to][0] = next.cost;
                    maxCost[next.to][0] = next.cost;
                    parent[next.to][0] = now;
                }
            }
        }
    }

    private static void getSparseTable() {
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j < n + 1; j++) {
                parent[j][i] = parent[parent[j][i - 1]][i - 1];
                minCost[j][i] = Math.min(minCost[j][i - 1], minCost[parent[j][i - 1]][i - 1]);
                maxCost[j][i] = Math.max(maxCost[j][i - 1], maxCost[parent[j][i - 1]][i - 1]);
            }
        }
    }

    static int[] getLca(int a, int b) {
        if (depth[a] < depth[b]) {
            return getLca(b, a);
        }
        int[] rtn = {Integer.MAX_VALUE, -1};//{min,max}
        for (int i = 0; i <= k; i++) {
            if (((depth[a] - depth[b]) & (1 << i)) >= 1) {
                rtn[0] = Math.min(rtn[0], minCost[a][i]);
                rtn[1] = Math.max(rtn[1], maxCost[a][i]);
                a = parent[a][i];

            }
        }
        if (a == b) return rtn;
        //위에서부터 비교해서 다르면 대입
        for (int i = k; i >= 0; i--) {
            if (parent[a][i] != parent[b][i]) {
                rtn[0] = Math.min(rtn[0], Math.min(minCost[a][i], minCost[b][i]));
                rtn[1] = Math.max(rtn[1], Math.max(maxCost[a][0], maxCost[b][0]));
                a = parent[a][i];
                b = parent[b][i];
            }
        }
        rtn[0] = Math.min(rtn[0], Math.min(minCost[a][0], minCost[b][0]));
        rtn[1] = Math.max(rtn[1], Math.max(maxCost[a][0], maxCost[b][0]));
        return rtn;
    }

}

class Edge {
    int to;
    int cost;

    public Edge(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }
}
