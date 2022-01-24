package P1753;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Edge>> map = new ArrayList<>();
    static int[] cost;
    static boolean[] visit;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());
        cost = new int[v + 1];
        visit = new boolean[v + 1];
        Arrays.fill(cost, INF);
        for (int i = 0; i < v + 1; i++) {
            map.add(new ArrayList<>());
        }
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            map.get(from).add(new Edge(to, cost));
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getCost));
        pq.offer(new Edge(start, 0));
        cost[start] = 0;
        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            if (visit[now.to]) {
                continue;
            }
            visit[now.to] = true;
            for (Edge edge : map.get(now.to)) {
                int newCost = now.cost+edge.cost;
                if (newCost < cost[edge.to]) {
                    cost[edge.to] = newCost;
                    pq.offer(new Edge(edge.to, newCost));
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < cost.length; i++) {
            int c = cost[i];
            if (c == INF) {
                sb.append("INF").append('\n');
            } else {
                sb.append(c).append('\n');
            }
        }
        System.out.println(sb);

    }
}

class Edge {
    int to;
    int cost;

    public Edge(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }
}
