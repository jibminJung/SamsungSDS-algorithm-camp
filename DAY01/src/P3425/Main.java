package P3425;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final long LIMIT = 1000000000;
    static HashMap<String, Integer> orderMap = new HashMap<>() {{
        put("POP", 1);
        put("INV", 2);
        put("DUP", 3);
        put("SWP", 4);
        put("ADD", 5);
        put("SUB", 6);
        put("MUL", 7);
        put("DIV", 8);
        put("MOD", 9);
    }};
    static Stack<Long> stack;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            ArrayList<String> program = new ArrayList<>();
            while (true) {
                String order = br.readLine();
                program.add(order);
                if (order.equals("END")) {
                    break;
                } else if (order.equals("QUIT")) {
                    System.out.println(sb);
                    System.exit(0);
                }
            }

            int input = Integer.parseInt(br.readLine());
            for (int i = 0; i < input; i++) {
                stack = new Stack<>();
                stack.push(Long.parseLong(br.readLine()));
                for (String order :
                        program) {
                    if (order.equals("END")) {
                        if (stack.size() != 1) {
                            sb.append("ERROR\n");
                        } else {
                            sb.append(stack.pop()).append('\n');
                        }
                        break;
                    }
                    if (order.equals("")) continue;
                    int action = orderMap.getOrDefault(order, 0);
                    if (action == 0) {
                        long s = Long.parseLong(order.split(" ")[1]);
                        stack.push(s);
                    } else {
                        if (process(action) == -1) {
                            sb.append("ERROR\n");
                            break;
                        }
                    }
                }//프로그램 종료

            }
            sb.append('\n');
        }
    }

    static int process(int action) {
        long n1, n2;
        long result;
        switch (action) {
            case 1://POP
                if (stack.isEmpty()) return -1;
                stack.pop();
                break;
            case 2://INV
                if (stack.isEmpty()) return -1;
                stack.push(-stack.pop());
                break;
            case 3://DUP
                if (stack.isEmpty()) return -1;
                stack.push(stack.peek());
                break;
            case 4://SWP
                if (stack.size() < 2) return -1;
                n1 = stack.pop();
                n2 = stack.pop();
                stack.push(n1);
                stack.push(n2);
                break;
            case 5://ADD
                if (stack.size() < 2) return -1;
                n1 = stack.pop();
                n2 = stack.pop();
                result = n2 + n1;
                if (result > LIMIT || result < -LIMIT) return -1;
                stack.push(result);
                break;
            case 6://SUB
                if (stack.size() < 2) return -1;
                n1 = stack.pop();
                n2 = stack.pop();
                result = n2 - n1;
                if (result > LIMIT || result < -LIMIT) return -1;
                stack.push(result);
                break;
            case 7://MUL
                if (stack.size() < 2) return -1;
                n1 = stack.pop();
                n2 = stack.pop();
                result = n2 * n1;
                if (result > LIMIT || result < -LIMIT) return -1;
                stack.push(n2 * n1);
                break;
            case 8://DIV
                if (stack.size() < 2) return -1;
                n1 = stack.pop();
                n2 = stack.pop();
                if (n1 == 0) return -1;
                if ((n1 < 0) ^ (n2 < 0)) {
                    result = -(Math.abs(n2) / Math.abs(n1));
                } else {
                    result = (Math.abs(n2) / Math.abs(n1));
                }
                if (result > LIMIT || result < -LIMIT) return -1;
                stack.push(result);
                break;
            case 9://MOD
                if (stack.size() < 2) return -1;
                n1 = stack.pop();
                n2 = stack.pop();
                if (n1 == 0) return -1;
                if ((n2 < 0)) {
                    result = -(Math.abs(n2) % Math.abs(n1));
                } else {
                    result = (Math.abs(n2) % Math.abs(n1));
                }
                if (result > LIMIT || result < -LIMIT) return -1;
                stack.push(result);
                break;
        }
        return 1;
    }
}
