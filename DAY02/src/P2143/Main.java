package P2143;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {
    static long T, n, m;
    static long[] a, b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        a = new long[(int) n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        b = new long[(int) m];
        for (int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }
        ArrayList<Long> subA = new ArrayList<>();
        ArrayList<Long> subB = new ArrayList<>();
        extract(subA, a);
        extract(subB, b);
        Collections.sort(subA);
        Collections.sort(subB,Collections.reverseOrder());
        long sum = 0;
        long count = 0;
        for (int i = 0,j=0; i < subA.size() && j<subB.size();) {
            long a = subA.get(i);
            long b = subB.get(j);
            sum = a+b;
            if(sum==T){ //합이 같다
                int mI = i;
                int mJ = j;
                while(i<subA.size()&&a==subA.get(i)){
                    i++;
                }
                while(j<subB.size()&&b==subB.get(j)){
                    j++;
                }
                count += (long) (i - mI) *(j-mJ);
            }else if(sum<T){ //합이 작다
                i++;
            }else { // 합이 크다
                j++;
            }
        }
        System.out.println(count);

    }

    private static void extract(ArrayList<Long> subB, long[] b) {
        for (int i = 0; i < b.length; i++) {
            long temp = 0;
            for (int j = i; j < b.length; j++) {
                temp += b[j];
                subB.add(temp);
            }
        }
    }
}
