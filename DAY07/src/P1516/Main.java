package P1516;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Integer>> arr =new ArrayList<>();
    static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] done = new int[n+1];
        int[] count = new int[n+1];

        for (int i = 0; i < n + 1; i++) {
            arr.add(new ArrayList<>());
        }
        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            while(true){
                int before = Integer.parseInt(st.nextToken());
                if(before==-1) break;
                arr.get(before).add(i);
                count[i]++;
            }
            done[i] = time;
        }
        PriorityQueue<Job> q = new PriorityQueue<>(Comparator.comparingInt(Job::getTime));
        for (int i = 0; i < count.length; i++) {
            if(count[i] ==0){
                q.offer(new Job(i,done[i]));
            }
        }
        while(!q.isEmpty()){
            Job nowJob = q.poll();
            int now = nowJob.number;
            done[now] = nowJob.time;
            for (int next :arr.get(now)) {
                count[next]--;
                if(count[next]==0){
                    q.offer(new Job(next,nowJob.time+done[next]));
                }
            }
        }
        StringBuilder sb= new StringBuilder();
        for (int i = 1; i < done.length; i++) {
            sb.append(done[i]).append('\n');
        }
        System.out.println(sb);
    }
}
class Job{
    int number;
    int time;

    public int getTime() {
        return time;
    }

    public Job(int number, int time) {
        this.number = number;
        this.time = time;
    }
}
