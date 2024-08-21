import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine().trim());
        }
        br.close();

        int[] dp = new int[100001];
        Arrays.fill(dp, -1);

        for (int c = 0; c < n; c++) {
            dp[coins[c]] = 1;
        }

        for (int target = 1; target <= k; target++) {
            if (dp[target] != -1) continue;
            int min = Integer.MAX_VALUE;
            for (int c = 0; c < n; c++) {
                int prev = (target - coins[c] >= 0) ? dp[target - coins[c]] : -1;
                if (prev != -1) min = Math.min(min, prev);
            }
            if (min != Integer.MAX_VALUE) dp[target] = min + 1;
        }

        System.out.println(dp[k]);
    }
}
