package P11404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 987654321;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        arr= new int[n+1][n+1];
        for (int i = 0; i < arr.length; i++) {
            Arrays.fill(arr[i],INF);
            arr[i][i] = 0;
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            arr[from][to] = Math.min(cost,arr[from][to]);
        }
        for (int mid = 1; mid < n + 1; mid++) {
            for (int start = 1; start < n + 1; start++) {
                for (int end = 1; end < n + 1; end++) {
                    if(start==end||mid==end||start==mid) continue;
                    if(arr[start][end]>arr[start][mid]+arr[mid][end]){
                        arr[start][end]=arr[start][mid]+arr[mid][end];
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < arr.length; j++) {
                if(arr[i][j]==INF){
                    sb.append("0").append(" ");
                }else{
                    sb.append(arr[i][j]).append(' ');
                }
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}
