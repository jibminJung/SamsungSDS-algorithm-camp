package P1759;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static char[] vowels = {'a', 'e', 'i', 'o', 'u'};
    static char[] chars;
    static int L, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        String str = br.readLine().replaceAll(" ", "");
        chars = str.toCharArray();
        Arrays.sort(chars);

        dfs(-1, 0, 0, 0, "");
    }

    static void dfs(int idx, int length, int vowel, int consonant, String code) {
        /*
        1.체크인
        2. 목적지?
        3. 연결된 곳 순회
        4. 갈 수 있는지?
        5. 간다.
        6. 체크아웃
         */
        if (length == L) {
            if (vowel > 0 && consonant > 1) {
                System.out.println(code);
            }
            return;
        }


        for (int i = idx + 1; i < chars.length; i++) {
            if (isVowel(chars[i])) {
                dfs(i, length + 1, vowel + 1, consonant, code + chars[i]);
            } else {
                dfs(i, length + 1, vowel, consonant + 1, code + chars[i]);
            }
        }


    }

    static boolean isVowel(char c) {
        for (char vowel :
                vowels) {
            if (c == vowel) return true;
        }
        return false;
    }
}
