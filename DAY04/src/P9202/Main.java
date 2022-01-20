package P9202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static boolean[][] visit = new boolean[4][4];
    static char[][] board = new char[4][4];
    static int[] dx = {0, 1, 0, -1, 1, -1, -1, 1};
    static int[] dy = {1, 0, -1, 0, 1, -1, 1, -1};
    static int size = 'Z' - 'A' + 1;
    static int[] score = {0, 0, 0, 1, 1, 2, 3, 5, 11};
    static Node root;
    static StringBuilder sb,resultSb;
    static String answer="";
    static int sum, count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        root = new Node(' ', new Node[size], false, false);
        sb = new StringBuilder();
        resultSb = new StringBuilder();
        int w = Integer.parseInt(br.readLine());
        for (int i = 0; i < w; i++) {
            String s = br.readLine();
            insertTireNode(s);
        }br.readLine();
        int b = Integer.parseInt(br.readLine());
        for (int k = 0; k < b; k++) {
            sum =0;
            answer="";
            count=0;
            for (int i = 0; i < 4; i++) {
                board[i] = br.readLine().toCharArray();
            }
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (root.hasChild(board[i][j])) {
                        search(i, j, 1, root.child[board[i][j]-'A']);
                    }
                }
            }
            resultSb.append(sum).append(' ');
            resultSb.append(answer).append(' ');
            resultSb.append(count).append('\n');
            root.clearHit();
            br.readLine();
        }
        System.out.println(resultSb);

    }

    static void search(int y, int x, int length, Node node) {

//        1.체크인
        visit[y][x] = true;
        sb.append(board[y][x]);
//        2.목적지인지?
        if (node.isEnd && !node.isHit) {
            node.isHit = true;
            sum += score[length];
            count++;
            String foundWord = sb.toString();
            if (compare(answer, foundWord) > 0) {
                answer = foundWord;
            }
        }
//        3. 연결된 곳 8방향
        for (int i = 0; i < 8; i++) {
            int ty = y + dy[i];
            int tx = x + dx[i];
            if (0 <= ty && ty < 4 && 0 <= tx && tx < 4) {
//        4. 가능한지, 방문 여부 , 자식 보유 여부
                if (visit[ty][tx] == false && node.hasChild(board[ty][tx])) {
//        5. 간다.
                    search(ty, tx, length + 1, node.child[board[ty][tx] - 'A']);
                }
            }
        }
//        6. 체크아웃
        visit[y][x] = false;
        sb.deleteCharAt(length-1);

    }

    static int compare(String arg0, String arg1) {
        int result = Integer.compare(arg1.length(), arg0.length());
        if (result == 0) {
            return arg0.compareTo(arg1);
        } else {
            return result;
        }
    }

    static void insertTireNode(String word) {
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            if (!current.hasChild(word.charAt(i))) {
                current.child[word.charAt(i) - 'A'] = new Node(word.charAt(i), new Node[size], false, false);
            }
            current = current.child[word.charAt(i) - 'A'];
        }
        current.isEnd = true;
    }

    static class Node {
        char data;
        Node[] child;
        boolean isEnd;
        boolean isHit;

        public Node(char data, Node[] child, boolean isEnd, boolean isHit) {
            this.data = data;
            this.child = child;
            this.isEnd = isEnd;
            this.isHit = isHit;
        }

        void clearHit(){
            isHit =false;
            for (int i = 0; i < child.length; i++) {
                if(child[i]!=null){
                    child[i].clearHit();
                }

            }
        }

        boolean hasChild(char c) {
            return child[c - 'A'] != null;
        }
    }
}
