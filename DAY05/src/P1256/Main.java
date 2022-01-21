package P1256;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] pascal = new int[201][201];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int z = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        if (combination(a+z, z) < k) {
            System.out.println("-1");
        } else {
            query(a, z, k);
            System.out.println(sb);
        }

    }

    static void query(int a, int z, int k) {
        if (a + z == 0) {
            return;
        } else if (a == 0) {
            sb.append('z');
            query(a, z - 1, k);
        } else if (z == 0) {
            sb.append('a');
            query(a - 1, z, k);
        } else {
            int leftCount = combination(a + z - 1, z);
            if (leftCount >= k) {
                sb.append('a');
                query(a - 1, z, k);
            } else {
                sb.append('z');
                query(a, z - 1, k - leftCount);
            }
        }
    }

    static int combination(int n, int r) {
        if (n == r || r == 0) {
            return 1;
        } else if (pascal[n][r] != 0) {
            return pascal[n][r];
        } else {
            return pascal[n][r] = (int) Math.min(1e9, combination(n - 1, r) + combination(n - 1, r - 1));
        }
    }
}
