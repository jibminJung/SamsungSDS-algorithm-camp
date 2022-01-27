package P2098;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int n,visitAll;
    static int[][] cost,dp;
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        visitAll = (1<<n) -1;
        cost = new int[n+1][n+1];
        dp = new int[n+1][visitAll+1];
        for (int i = 1; i <= n; i++) {
            st= new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[1][1] = 0;
        dfs(1,1);
        System.out.println(answer);


    }
    static void dfs(int node, int state){
        //재귀는 탈출을 먼저 써준다
        if(state==visitAll){//모두 방문했을 경우
            if(cost[node][1]==0){//시작점으로 돌아갈 수 없을 경우
                return;
            }
            answer = Math.min(answer,dp[node][state]+cost[node][1]);
        }
        //연결된 정점들을 탐색한다.
        for (int i = 1; i < n + 1; i++) {
            int nextVisit = (1<<(i-1))|state;
            if(nextVisit==state) continue;
            if(cost[node][i]==0) continue;

            if(dp[i][nextVisit]>dp[node][state]+cost[node][i]){
                dp[i][nextVisit] = Math.min(dp[i][nextVisit], dp[node][state] + cost[node][i]);
                dfs(i, nextVisit);
            }
        }


    }
}
