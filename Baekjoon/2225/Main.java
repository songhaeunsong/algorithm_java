import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[K + 1][N + 1];
        
        for (int i = 0; i <= N; i++) {
            dp[1][i] = 1;
        }

        for (int k = 2; k <= K; k++) {
            for (int n = 0; n <= N; n++) {
                for (int x = 0; x <= n; x++) {
                    dp[k][n] = (dp[k][n] + dp[k-1][n-x]) % 1000000000;
                }
            }
        }

        System.out.println(dp[K][N]);
    }
}
