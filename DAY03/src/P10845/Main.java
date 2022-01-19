package P10845;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        Queue q = new Queue(n);
        StringBuilder sb = new StringBuilder();
        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            char c = st.nextToken().charAt(0);
            switch (c) {
                case 'p':
                    if (st.hasMoreTokens()) {
                        q.push(Integer.parseInt(st.nextToken()));
                    } else {
                        sb.append(q.pop()).append('\n');
                    }
                    break;
                case 's':
                    sb.append(q.size()).append('\n');
                    break;
                case 'e':
                    sb.append(q.empty()).append('\n');
                    break;
                case 'f':
                    sb.append(q.front()).append('\n');
                    break;
                case 'b':
                    sb.append(q.back()).append('\n');
                    break;
            }

        }
        System.out.println(sb);
    }

    static class Queue {
        int front;
        int back;
        int[] data;

        Queue(int n) {
            front = 0;
            back = 0;
            data = new int[n + 1];
        }

        void push(int k) {
            data[back++] = k;
        }

        public int pop() {
            if (back - front == 0) {
                return -1;
            }
            return data[front++];
        }

        public int size() {
            return back - front;
        }

        public int empty() {
            return (back - front)==0?1:0;
        }

        public int front() {
            if (back - front == 0) {
                return -1;
            }
            return data[front];
        }
        public int back() {
            if (back - front == 0) {
                return -1;
            }
            return data[back-1];
        }
    }
}
