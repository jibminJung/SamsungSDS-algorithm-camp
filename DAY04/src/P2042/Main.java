package P2042;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, k;
    static long[] tree;
    static int s=1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        while (s < n) {
            s = s * 2;
        }
        tree = new long[s * 2];
        for (int i = 1; i <= n; i++) {
            insert(i, Long.parseLong(br.readLine()));
        }
        int loop = m + k;
        while (loop-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a == 1) {//update
                long c = Long.parseLong(st.nextToken());
                update(b, c);
            } else {//query
                int c = Integer.parseInt(st.nextToken());
                query(b, c);
            }
        }
    }

    static void update(int index, long num) {
        int n = s + index -1;
        long diff = num-tree[n];
        insert(index, diff);
    }

    static void query(int b, int c) {
        long sum = 0;
        b= s+b-1;
        c= s+c-1;
        while (b < c) {
            if (b % 2 == 1) {
                sum += tree[b];
                b++;
            }
            if (c % 2 == 0) {
                sum += tree[c];
                c--;
            }
            b /= 2;
            c /= 2;
        }
        if (b == c) {
            sum += tree[b];
        }
        System.out.println(sum);
    }

    static void insert(int index, long num) {
        int n = s + index-1;
        while (n > 0) {
            tree[n] += num;
            n /= 2;
        }
    }
}
