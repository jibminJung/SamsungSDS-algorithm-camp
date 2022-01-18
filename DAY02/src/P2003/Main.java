package P2003;

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

        /*
        슬라이딩 윈도우
        이동하면서 구간의 합을 계산해주고,
        합이 목표보다 크면 밑의 포인터를 올리고,
        작으면 위의 포인터를 올린다.
        합이 같으면 답에 더해주고, 밑의 포인터를 올린다.
        */
        int sum = 0;
        int answer = 0;
        for (int i = -1, j = -1; i < arr.length && j < arr.length; ) {
            if (sum == m) { // 합이 목표와 같다
                answer ++;
                i++;
                sum -= arr[i];
            } else if (sum < m) { // 합이 목표보다 작다
                j++;
                if(j==arr.length) break;
                sum += arr[j];
            } else { // 합이 목표보다 크다.
                i++;
                sum -= arr[i];
            }
        }
        System.out.println(answer);

    }
}
