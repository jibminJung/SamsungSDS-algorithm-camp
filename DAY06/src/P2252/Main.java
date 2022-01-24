package P2252;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        arr = new int[n+1];
        for (int i = 0; i < n+1; i++) {
            graph.add(new ArrayList<>());
        }
        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int before = Integer.parseInt(st.nextToken());
            int after = Integer.parseInt(st.nextToken());
            graph.get(before).add(after);
            arr[after]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i < n+1; i++) {
            if(arr[i]==0){
                q.offer(i);
            }
        }

        while(!q.isEmpty()){
            int now = q.poll();
            sb.append(now).append(' ');
            for (int next :
                    graph.get(now)) {
                arr[next]--;
                if(arr[next]==0) q.offer(next);
            }
        }
        System.out.println(sb);


    }
}
