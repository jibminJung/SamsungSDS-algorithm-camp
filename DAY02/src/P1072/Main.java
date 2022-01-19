package P1072;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long x = Integer.parseInt(st.nextToken());
        long y = Integer.parseInt(st.nextToken());
        if (x == 0) {
            System.out.println("1");
            System.exit(0);
        }
        int z = (int) (y * 100 / x);
        if (z >= 99) {
            System.out.println("-1");
            System.exit(0);
        }
        int l = 0;
        int r = 1000000000;
        while (l < r) {
            int mid = (l + r) / 2;
            int nz = (int) (((y + mid) * 100) / (x + mid));
            if (nz == z) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        System.out.println(l);

    }
}
