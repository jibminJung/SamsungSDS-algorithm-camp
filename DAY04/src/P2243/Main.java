package P2243;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static int MAX = 1000000;
    static int s = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while(s<MAX){
            s = s*2;
        }
        arr = new int[s*2];
        int n = Integer.parseInt(br.readLine());
        while(n-->0){
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            if(order==1){
                int rank = Integer.parseInt(st.nextToken());
                pick(0,rank);
            }else{
                int taste = Integer.parseInt(st.nextToken());
                int amount = Integer.parseInt(st.nextToken());
                update(taste,amount);
            }
        }
    }
    static void pick(int node, int rank){
        if(node>=s){ //leaf node?
            System.out.println(node-s+1);
            update(node-s+1,-1);
            return;
        }
        //왼쪽을 본다
        int left = arr[node*2];
        if(left<rank){ //오른쪽으로 보내기
            pick(node*2 +1, rank-left);
        }else{//왼쪽으로 보내기
            pick(node*2,rank);
        }

    }
    static void update(int taste, int update){
        int index = s+taste-1;
        while(index>0){
            arr[index] += update;
            index = index/2;
        }
    }
}
