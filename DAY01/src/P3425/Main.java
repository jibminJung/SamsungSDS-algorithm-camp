package P3425;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Main {
    static HashMap<String,Integer> orderMap = new HashMap<>(){{
        put("POP",1);
        put("INV",2);
        put("DUP",3);
        put("SWP",4);
        put("ADD",5);
        put("SUB",6);
        put("MUL",7);
        put("DIV",8);
        put("MOD",9);
    }};
    static Stack<Integer> stack;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> program = new ArrayList<>();
        while (true) {
            String order = br.readLine();
            program.add(order);
            if (order.equals("END")) {
                break;
            }
        }
        int input = Integer.parseInt(br.readLine());
        for (int i = 0; i < input; i++) {
            stack = new Stack<>();
            stack.push(input);
            for (String order :
                    program) {
                int action = orderMap.getOrDefault(order,0);
                if(action==0){
                    int s = Integer.parseInt(order.split(" ")[1]);
                    stack.push(s);
                }else{

                }
            }
        }


    }
    static int process(int action){
        int n1,n2;
        switch (action){
            case 1://POP
                if(stack.isEmpty()) return -1;
                stack.pop();
                break;
            case 2://INV
                if(stack.isEmpty()) return -1;
                stack.push(-stack.pop());
                break;
            case 3://DUP
                if(stack.isEmpty()) return -1;
                stack.push(stack.peek());
                break;
            case 4://SWP
                if(stack.size()<2) return -1;
                n1 = stack.pop();
                n2 = stack.pop();
                stack.push(n1);
                stack.push(n2);
                break;
            case 5://ADD
                if(stack.size()<2) return -1;
                n1 = stack.pop();
                n2 = stack.pop();
                stack.push(n2+n1);
                break;
            case 6://SUB
                if(stack.size()<2) return -1;
                n1 = stack.pop();
                n2 = stack.pop();
                stack.push(n2-n1);
                break;
            case 7://MUL

                break;
            case 8://DIV

                break;
            case 9://MOD

                break;
        }
    }
}
