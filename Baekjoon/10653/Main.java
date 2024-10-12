import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int K;
    static int[][] input;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        input = new int[N + 1][2];
        dp = new int[K + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            input[i][0] = Integer.parseInt(st.nextToken());
            input[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i <= K; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][1] = 0;

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                if (dp[j][i - 1] != Integer.MAX_VALUE) {
                    dp[j][i] = Math.min(dp[j][i],
                            dp[j][i - 1] + calculateD(input[i][0], input[i - 1][0], input[i][1], input[i - 1][1]));
                }

                for (int jump = 1; jump <= j && i - jump - 1 >= 1; jump++) {
                    if (dp[j - jump][i - jump - 1] != Integer.MAX_VALUE) {
                        dp[j][i] = Math.min(dp[j][i], dp[j - jump][i - jump - 1]
                                + calculateD(input[i][0], input[i - jump - 1][0], input[i][1], input[i - jump - 1][1]));
                    }
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i <= K; i++) {
            result = Math.min(result, dp[i][N]);
        }

        System.out.println(result);
    }

    static int calculateD(int x1, int x2, int y1, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
