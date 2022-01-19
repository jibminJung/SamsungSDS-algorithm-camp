package P1202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static Jewel[] jewels;
    static int[] bags;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        jewels = new Jewel[n];
        bags = new int[k];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            jewels[i] = new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        for (int i = 0; i < k; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }
        //가방 무게 능력 오름차순 정렬
        Arrays.sort(bags);
        //보석 무게 오름차순 정렬
        Arrays.sort(jewels, Comparator.comparingInt(Jewel::getWeight));
        //보석 가격 내림차순 정렬
        PriorityQueue<Jewel> pq = new PriorityQueue<>(Comparator.comparingInt(Jewel::getValue).reversed());

        int jIndex = 0;
        long result = 0;
        for (int i = 0; i < bags.length; i++) {
            int bag = bags[i];
            while (jIndex<n && jewels[jIndex].weight <= bag) {
                pq.add(jewels[jIndex++]);
            }
            if(!pq.isEmpty()){
                result += pq.poll().value;
            }
        }
        System.out.println(result);
    }
}

class Jewel {
    int weight;
    int value;

    Jewel(int m, int v) {
        weight = m;
        value = v;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }
}
