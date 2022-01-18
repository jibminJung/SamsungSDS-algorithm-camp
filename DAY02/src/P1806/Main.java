package P1806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int sum = arr[0];
        int length = Integer.MAX_VALUE;
        for (int i = 0, j =0 ; i < arr.length && j < arr.length; ) {
            if (sum < m) { // 합이 목표보다 작다
                j++;
                if(j==arr.length) break;
                sum += arr[j];
            } else { // 합이 목표보다 크다.
                length = Math.min(j-i +1,length);
                sum -= arr[i++];
            }
        }
        System.out.println(length==Integer.MAX_VALUE?0:length);

    }
}
