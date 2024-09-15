import java.io.*;
import java.util.*;

public class Main {
    static String input;
    static String bomb;
    static int count;
    static int bombLen;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        count = 0;

        input = br.readLine();
        bomb = br.readLine();
        bombLen = bomb.length();
        char lastBomb = bomb.charAt(bombLen - 1);

        for (char c : input.toCharArray()) {
            stack.add(c);
            if (stack.size() >= bombLen && c == lastBomb) {
                int count = 0;
                for (int i = 0; i < bombLen; i++) {
                    if (stack.get(stack.size() - bombLen + i) != bomb.charAt(i))
                        break;
                    count++;
                    if (count == bombLen) {
                        for (int j = 0; j < bombLen; j++) {
                            stack.pop();
                        }
                    }
                }
            }
        }

        for (char c : stack) {
            sb.append(c);
        }

        String answer = sb.toString();
        System.out.print(answer.equals("") ? "FRULA" : answer);

    }

}