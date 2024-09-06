import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static String input;
    static Map<Character, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int left = 0;
        int right = 0;
        int max = 0;

        N = Integer.parseInt(br.readLine());
        input = br.readLine();
        map.put(input.charAt(0), 1);

        while (left <= right) {

            int len = map.size();

            if (len > N) {
                char alphabet = input.charAt(left);
                map.put(alphabet, map.get(alphabet) - 1);
                if (map.get(alphabet) == 0)
                    map.remove(alphabet);
                left++;
                continue;
            }

            if (len == N) {
                max = Math.max(max, right - left + 1);
            }

            if (++right >= input.length())
                break;

            map.put(input.charAt(right), map.getOrDefault(input.charAt(right), 0) + 1);

        }

        System.out.println(max == 0 ? input.length() : max);
    }
}
