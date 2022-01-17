package P1713;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean[] students = new boolean[101];
    static ArrayList<Student> frame = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int frameLimit = Integer.parseInt(br.readLine());
        int recommend = Integer.parseInt(br.readLine());
        TreeMap<Integer, Student> tm = new TreeMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < recommend; i++) {
            int number = Integer.parseInt(st.nextToken());

            if (students[number]) { //있으면 추천수만 증가
                incRefer(number);
            } else {// 없으면

                if (frame.size() >= frameLimit) {
                    Collections.sort(frame);
                    Student temp = frame.get(frame.size()-1);
                    students[temp.number] = false;
                    frame.remove(frame.size() - 1);
                }
                frame.add(new Student(number, 1, i));
                students[number] = true;
            }
        }
        Collections.sort(frame,Comparator.comparingInt(Student::getNumber));
        StringBuilder sb= new StringBuilder();
        for (Iterator<Student> iterator = frame.iterator(); iterator.hasNext(); ) {
            Student next = iterator.next();
            sb.append(next.number).append(" ");
        }
        System.out.println(sb);

    }

    static void incRefer(int number) {
        for (Iterator<Student> iterator = frame.iterator(); iterator.hasNext(); ) {
            Student s = iterator.next();
            if (s.number == number) {
                s.refer++;
                return;
            }
        }
    }

    static class Student implements Comparable<Student> {
        int number;
        int refer;
        int time;

        @Override
        public int compareTo(Student o) {
            int comp1 = Integer.compare(o.refer,refer);
            if(comp1==0){
                return Integer.compare(o.time,time);
            }
            return comp1;
        }

        public Student(int number, int refer, int time) {
            this.number = number;
            this.refer = refer;
            this.time = time;
        }

        public int getNumber() {
            return number;
        }
    }
}
