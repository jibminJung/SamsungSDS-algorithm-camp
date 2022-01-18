package P2805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        long l =0;
        long r = 1000000000;
        while(l<r){
            long mid = (l+r+1)/2;
            if(pos(mid)){
                l=mid;
            }else{
                r=mid-1;
            }
        }
        System.out.println(l);


    }
    static boolean pos(long set){
        long sum =0;
        for (int i = 0; i < arr.length; i++) {
            sum = sum + Math.max(arr[i]-set,0);
            if(sum>=m)return true;
        }
        return false;
    }
}
