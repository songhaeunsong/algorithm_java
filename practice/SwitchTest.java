// 스위치온오프

import java.util.*;
import java.io.*;

public class SwitchTest {
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // 10
        int M = Integer.parseInt(st.nextToken()); // 3

        st = new StringTokenizer(br.readLine(), " ");
        int[] input = new int[M];
        for (int i = 0; i < M; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        // 구현
        boolean[] switches = new boolean[N];

        for (int num : input) {
            int target = num;
            while (target <= N) {
                switches[target - 1] = !switches[target - 1];
                target += num;
            }
        }

        // 출력
        StringBuilder sb = new StringBuilder();

        for (boolean i : switches) {
            sb.append(i ? 1 : 0).append(" ");
        }
        System.out.println(sb.toString().trim());

        br.close();

    }
}
