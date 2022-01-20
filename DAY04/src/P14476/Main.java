package P14476;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] arr,lr,rl;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n];
        lr = new int[n];
        rl = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        lr[0]=arr[0];
        rl[n-1] = arr[n-1];
        for (int i = 1; i < arr.length; i++) {
            lr[i] = gcd(lr[i-1],arr[i]);
            rl[n-1-i] = gcd(arr[n-i-1],rl[n-i]);
        }
        int answer = Integer.MIN_VALUE;
        int rid = 0;
        for (int i = 0; i < arr.length; i++) {
            int k = arr[i];
            if(i==0){
                if(k%rl[i+1]==0) continue;
                if(answer<rl[i+1]){
                    answer = rl[i+1];
                    rid = k;
                }
            }else if(i==arr.length-1){
                if(k%lr[i-1]==0) continue;
                if(answer<lr[i-1]){
                    answer = lr[i-1];
                    rid = k;
                }
            }else{
                int gcd = gcd(lr[i-1],rl[i+1]);
                if(k%gcd==0) continue;
                if(answer<gcd){
                    answer = gcd;
                    rid = k;
                }
            }
        }
        if(answer==Integer.MIN_VALUE){
            System.out.println("-1");
        }else{
            System.out.print(answer);
            System.out.print(" ");
            System.out.println(rid);
        }
    }
    static int gcd(int a,int b){
        int r = a%b;
        while(r !=0){
            a = b;
            b = r;
            r = a%b;
        }
        return b;
    }
}
