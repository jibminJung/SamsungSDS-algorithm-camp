package P5639;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static StringBuilder sb= new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Node root = new Node(Integer.parseInt(br.readLine()));

        while (true) {
            String s = br.readLine();
            if(s==null||s.equals("")){
                break;
            }
            int temp = Integer.parseInt(s);
            root.insert(temp);
        }
        postOrder(root);
        System.out.println(sb);
    }
    static void postOrder(Node node){
        if(node==null) return;
        postOrder(node.l);
        postOrder(node.r);
        sb.append(node.number).append("\n");
    }

    static class Node {
        int number;
        Node l, r;

        Node(int number) {
            this.number = number;
            l = null;
            r = null;
        }

        void insert(int n){
            if(n<number){
                if(l==null){
                    this.l = new Node(n);
                }else{
                    this.l.insert(n);
                }
            }else{
                if(r==null){
                    this.r = new Node(n);
                }else{
                    this.r.insert(n);
                }

            }
        }
    }
}
