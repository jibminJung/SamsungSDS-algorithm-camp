package P2748;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static long[] dp = new long[91];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        System.out.println(fib(n));



    }
    static long fib(int n){
        if(n==0){
            return 0;
        }
        if(n==1||n==2){
            return 1;
        }
        if(dp[n]!=0) return dp[n];
        int k;
        if(n%2==0) {//짝수
            k = n/2;
            return dp[n] = (2*fib(k-1)+fib(k))*fib(k);
        }else{
            k = (n+1)/2;
            return dp[n] = fib(k)*fib(k) + fib(k-1)*fib(k-1);
        }

    }
}
