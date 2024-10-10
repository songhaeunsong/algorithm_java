import java.io.*;
import java.util.*;

public class Main {
    static int a;
    static int b;
    static int d;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken()) + 1;

        int[] dp = new int[N + 1];
        dp[1] = 1;

        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1];

            if (i > a) {
                dp[i] = (dp[i] + dp[i - a]) % 1000;

            }

            if (i > b) {
                dp[i] = (dp[i] - dp[i - b] + 1000) % 1000;

            }
        }
        int answer = dp[N] % 1000;
        if (N >= d) {
            answer = (dp[N] - dp[N - d] + 1000) % 1000;
        }
        System.out.println(answer);
    }
}
