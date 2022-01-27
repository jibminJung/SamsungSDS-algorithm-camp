package P11049;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] row,col;
    static int[][] dp;
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        row = new int[n+1];
        col = new int[n+1];
        dp = new int[n+1+1][n+1+1];
        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            row[i] = Integer.parseInt(st.nextToken());
            col[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = n-1; i >=1; i--) {
            for (int j = i+1; j < n+1; j++) {
                dp[i][j] = INF;
                for (int k = i; k <= j; k++) {
                    dp[i][j] = Math.min(dp[i][j],dp[i][k]+dp[k+1][j]+row[i]*col[k]*col[j]);
                }
            }

        }
        System.out.println(dp[1][n]);


    }
}
