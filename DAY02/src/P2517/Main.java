package P2517;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static HashMap<Integer,Integer> hm= new HashMap<>();
    static int[] tree;
    static int s=1;
    static StringBuilder sb= new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        while(s<n){
            s = s*2;
        }
        tree = new int[s*2+1];
        int[] arr = new int[n];
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            temp.add(arr[i]);
        }
        Collections.sort(temp);
        for (int i = 0; i < temp.size(); i++) {
            hm.put(temp.get(i),i+1);
        }
        for (int i = 0; i < arr.length; i++) {
            insert(hm.get(arr[i]));
            count(hm.get(arr[i]));
        }
        System.out.println(sb);
    }
    static void insert(int number){
        int index = s+number-1;
        while(index>0){
            tree[index]++;
            index /= 2;
        }
    }
    static void count(int power){//현재까지 나보다 큰 실력인 사람 카운트
        int left = s+power-1;
        int right = s+s;
        int sum = 0;
        while(left<right){
            if(left%2==1){
                sum += tree[left];
                left++;
            }
            if(right%2==0){
                sum += tree[right];
                right--;
            }
            left /= 2;
            right /=2;
        }
        if(left==right){
            sum += tree[left];
        }
        sb.append(sum).append('\n');
    }
}
