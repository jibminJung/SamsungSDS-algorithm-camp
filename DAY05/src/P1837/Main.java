package P1837;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean[] isNotPrime = new boolean[(int)1e6+1];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String p = st.nextToken();
        int k = Integer.parseInt(st.nextToken());
        initPrime();
        for (int i = 2; i < k; i++) {
            if(!isNotPrime[i]){
                if(modular(p,i)==0){
                    System.out.println("BAD "+i);
                    System.exit(0);
                }
            }
        }
        System.out.println("GOOD");
    }
    static int modular(String number,int primeNumber){
        for (int i = 1; i <= number.length(); i++) {
            int pizesu = Integer.parseInt(number.substring(0,i));
            if(pizesu>=primeNumber){
                int d = pizesu % primeNumber;
                number = String.valueOf(d)+ number.substring(i);
                i=1;
            }
        }
        return Integer.parseInt(number);
    }
    static void initPrime(){
        for (int i = 2; i <= Math.sqrt(isNotPrime.length); i++) {
            if(!isNotPrime[i]){
                for (int j = i+i; j < isNotPrime.length; j+=i) {
                    isNotPrime[j] = true;
                }
            }
        }
    }
}
