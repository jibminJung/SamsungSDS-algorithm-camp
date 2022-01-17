package P3055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int n, m;
    static char[][] map;
    static int[][] record;
    static int[] d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        record = new int[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        bfs();
        int dRecord = record[d[0]][d[1]];
        System.out.println(dRecord == 0 ? "KAKTUS" : dRecord - 1);


    }

    static void bfs() {
        /*
        1. 큐에서 꺼내옴
        2. 목적지인가?
        3. 연결된 곳을 순회
	        A. 갈 수 있는가?
	        B. 체크인
	        C. 큐에 넣는다
        4. 체크아웃 ( 생략 가능 )
         */
        Queue<Integer[]> q = new LinkedList<>();
        for (int i = 0; i < map.length; i++) {
            char[] chars = map[i];
            for (int j = 0; j < chars.length; j++) {
                char c = chars[j];
                if (c == '*') {
                    q.offer(new Integer[]{i, j});
                } else if (c == 'D') {
                    d = new int[]{i, j};
                }
            }
        }
        for (int i = 0; i < map.length; i++) {
            char[] chars = map[i];
            for (int j = 0; j < chars.length; j++) {
                char c = chars[j];
                if (c == 'S') {
                    q.offer(new Integer[]{i, j});
                    record[i][j] = 1;
                }
            }
        }
        while (!q.isEmpty()) {
            Integer[] temp = q.poll();
            int tx = temp[0];
            int ty = temp[1];
            char c = map[tx][ty];
            for (int i = 0; i < 4; i++) {
                int ntx = tx + dx[i];
                int nty = ty + dy[i];

                if (ntx < 0 || ntx >= n || nty < 0 || nty >= m) { //OOB
                    continue;
                }

                char nextC = map[ntx][nty];

                if (c == '*') {//물
                    if (nextC !='.') continue;
                    q.offer(new Integer[]{ntx, nty});
                    map[ntx][nty] = '*';
                } else if (c == 'S') {
                    if (nextC == 'X' || nextC == '*' || nextC == 'S') {
                        continue;
                    }
                    q.offer(new Integer[]{ntx, nty});
                    map[ntx][nty] = 'S';
                    record[ntx][nty] = record[tx][ty] + 1;
                }
            }
        }
    }
}
