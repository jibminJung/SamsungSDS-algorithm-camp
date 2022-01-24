package P3830;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long[] arr;
    static int[] group;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb= new StringBuilder();
        StringTokenizer st;
        while(true){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            if(n==0&&m==0)break;
            arr = new long[n+1];
            group = new int[n+1];
            for (int i = 0; i < n + 1; i++) {
                group[i] = i;
            }
            for (int i = 0; i < m; i++) {
                st= new StringTokenizer(br.readLine());
                if(st.nextToken().equals("!")){//측정
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());
                    int diff = Integer.parseInt(st.nextToken());
                    union(a,b,diff);
                }else{//질문
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());
                    if(find(a)==find(b)){
                        sb.append(arr[b]-arr[a]).append('\n');
                    }else{
                        sb.append("UNKNOWN").append('\n');
                    }

                }
            }
        }
        System.out.println(sb);
    }
    static int find(int a){
        if(group[a]==a) return a;
        int parent = find(group[a]);
        arr[a] += arr[group[a]];
        return group[a] = parent;
    }
    static void union(int a, int b,long diff){
        int repA = find(a);
        int repB = find(b);
        if(repA==repB) return;
        arr[repB] = arr[a]-arr[b] + diff;
        group[repB] = repA;
    }
}
