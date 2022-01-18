package P2517;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        StringBuilder sb = new StringBuilder();
        TreeSet<Integer> ts = new TreeSet<>(Comparator.reverseOrder());
        for (int i = 0; i < arr.length; i++) {
            ts.add(arr[i]);
            int cnt = 0;
            for (Iterator<Integer> iter = ts.iterator(); iter.hasNext(); ) {
                Integer next = iter.next();
                cnt ++;
                if(next==arr[i]){
                   sb.append(cnt).append('\n');
                }
            }
        }
        System.out.println(sb);

    }
}
