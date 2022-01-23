package P7453;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static ArrayList<ArrayList<Integer>> arr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < 4; i++) {
            arr.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                arr.get(j).add(Integer.parseInt(st.nextToken()));
            }
        }
        ArrayList<Integer> arr12 = sum(0, 1, true);
        ArrayList<Integer> arr34 = sum(2, 3, false);
        long answer = 0;
        int l = 0;
        int r = 0;
        while (l < arr12.size() && r < arr34.size()) {
            int temp = arr12.get(l) + arr34.get(r);
            if (temp == 0) {
                int prevL = l;
                int prevR = r;
                while (l < arr12.size() && arr12.get(prevL) == arr12.get(l)) {
                    l++;
                }
                while (r < arr34.size() && arr34.get(prevR) == arr34.get(r)) {
                    r++;
                }
                answer += ((long) (l - prevL)) * (long) (r - prevR);
            } else if (temp < 0) {
                l++;
            } else {
                r++;
            }
        }
        System.out.println(answer);

    }

    static ArrayList<Integer> sum(int a, int b, boolean isAsc) {
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < arr.get(a).size(); i++) {
            for (int j = 0; j < arr.get(b).size(); j++) {
                temp.add(arr.get(a).get(i) + arr.get(b).get(j));
            }
        }
        if (isAsc) {
            Collections.sort(temp);
        } else {
            Collections.sort(temp, Comparator.reverseOrder());
        }
        return temp;
    }
}
