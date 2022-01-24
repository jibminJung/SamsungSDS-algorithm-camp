package P1717;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb= new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        arr = new int[n+1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        while (m-->0){
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(q==0){
                union(a,b);
            }else{
                if(find(a)==find(b)){
                    sb.append("YES").append('\n');
                }else{
                    sb.append("NO").append('\n');
                }
            }
        }
        System.out.println(sb);


    }
    static int find(int a){
        if(arr[a] == a) return a;
        else
            return arr[a] = find(arr[a]);
    }
    static void union(int a, int b){
        int repA = find(a);
        int repB = find(b);
        arr[repA] = repB;
    }

}
