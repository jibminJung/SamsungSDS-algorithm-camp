package P1922;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Node>> arr = new ArrayList<>();
    static boolean[] visit;
    static int[] group;
    static ArrayList<Edge> edges = new ArrayList<>();
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        //프림 알고리즘
//        visit = new boolean[n + 1];
//        for (int i = 0; i <= n; i++) {
//            arr.add(new ArrayList<>());
//        }
//        while (m-- > 0) {
//            st = new StringTokenizer(br.readLine());
//            int a = Integer.parseInt(st.nextToken());
//            int b = Integer.parseInt(st.nextToken());
//            int c = Integer.parseInt(st.nextToken());
//            arr.get(a).add(new Node(b, c));
//            arr.get(b).add(new Node(a, c));
//        }
//        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(Node::getCost));
//        pq.offer(new Node(1, 0));
//        while (!pq.isEmpty()) {
//            Node now = pq.poll();
//            if (!visit[now.number]) {
//                visit[now.number] = true;
//                answer += now.cost;
//                for (Node next :
//                        arr.get(now.number)) {
//                    if (!visit[next.number]) pq.offer(next);
//                }
//            }
//        }

        //크루스칼 알고리즘
        group = new int[n+1];
        for (int i = 0; i < n+1; i++) {
            group[i] = i;
        }
        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges.add(new Edge(a,b,c));
        }
        Collections.sort(edges,Comparator.comparingInt(Edge::getCost));
        int count = 0;
        for (Edge temp : edges) {
            if (find(temp.from) != find(temp.to)) {
                answer += temp.cost;
                union(temp.from, temp.to);
                if (++count == n - 1) break;
            }
        }

        System.out.println(answer);
    }
    static int find(int a){
        if(group[a]==a) return a;
        return group[a] = find(group[a]);
    }
    static void union(int a, int b){
        int repA = find(a);
        int repB = find(b);
        group[repA] = repB;
    }
}

class Node {
    int number;
    int cost;

    public Node(int number, int cost) {
        this.number = number;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }
}
class Edge{
    int from;
    int to;
    int cost;

    public Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }
}