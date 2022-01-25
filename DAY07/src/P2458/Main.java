package P2458;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n+1][n+1];
        for (int i = 0; i < arr.length; i++) {
            Arrays.fill(arr[i],INF);
            arr[i][i] = 0;
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from =Integer.parseInt(st.nextToken());
            int to =Integer.parseInt(st.nextToken());
            arr[from][to] = 1;
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
        int answer = 0;
        for (int i = 1; i < arr.length; i++) {
            int cnt =0;
            for (int j = 1; j < arr.length; j++) {
                if(i==j) continue;
                if(arr[i][j]!=INF){
                    cnt++;
                }
                if(arr[j][i]!=INF){
                    cnt++;
                }
            }
            if(cnt==n-1) answer++;
        }
        System.out.println(answer);


    }
}
