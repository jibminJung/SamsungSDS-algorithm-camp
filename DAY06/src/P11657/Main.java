package P11657;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 987654321;
    static ArrayList<Edge> edges = new ArrayList<>();
    static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        arr = new long[n + 1];
        Arrays.fill(arr, INF);
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges.add(new Edge(from, to, cost));
        }
        arr[1] = 0;
        boolean flag = false;
        for (int i = 1; i < n + 1; i++) {
            for (Edge edge : edges) {
                if (arr[edge.from] != INF && arr[edge.from] + edge.cost < arr[edge.to]) {
                    arr[edge.to] = arr[edge.from] + edge.cost;
                    if (i == n) {
                        flag = true;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        if (flag) {
            System.out.println("-1");
        } else {
            for (int i = 2; i < n + 1; i++) {
                if (arr[i] == INF) {
                    sb.append("-1").append('\n');
                } else {
                    sb.append(arr[i]).append('\n');
                }
            }
            System.out.println(sb);
        }



    }

}

class Edge {
    int from;
    int to;
    int cost;

    public Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}
