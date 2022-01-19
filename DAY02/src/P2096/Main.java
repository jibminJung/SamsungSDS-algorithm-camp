package P2096;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] arr,dpMax,dpMin;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n=Integer.parseInt(br.readLine());
        arr = new int[n][3];
        dpMax = new int[n][3];
        dpMin = new int[n][3];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dpMax[0] = arr[0];
        dpMin[0] = arr[0];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                if(j==0){
                    dpMax[i][j] = Math.max(dpMax[i-1][j],dpMax[i-1][j+1])+arr[i][j];
                    dpMin[i][j] = Math.min(dpMin[i-1][j],dpMin[i-1][j+1])+arr[i][j];
                }else if(j==1){
                    dpMax[i][j] = Math.max(dpMax[i-1][j],Math.max(dpMax[i-1][j-1],dpMax[i-1][j+1]))+arr[i][j];
                    dpMin[i][j] = Math.min(dpMin[i-1][j],Math.min(dpMin[i-1][j-1],dpMin[i-1][j+1]))+arr[i][j];
                }else{
                    dpMax[i][j] = Math.max(dpMax[i-1][j],dpMax[i-1][j-1])+arr[i][j];
                    dpMin[i][j] = Math.min(dpMin[i-1][j],dpMin[i-1][j-1])+arr[i][j];
                }
            }
        }
        StringBuilder sb= new StringBuilder();
        sb.append(Math.max(dpMax[n-1][0],Math.max(dpMax[n-1][1],dpMax[n-1][2]))).append(" ");
        sb.append(Math.min(dpMin[n-1][0],Math.min(dpMin[n-1][1],dpMin[n-1][2])));
        System.out.println(sb);

    }
}
