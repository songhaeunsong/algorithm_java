import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int L;
    static int K;
    static int[][] stars;
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        max = 0;

        stars = new int[K][2];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            stars[i][0] = Integer.parseInt(st.nextToken());
            stars[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < K; i++) {
            for (int j = i; j < K; j++) {
                int row = Math.min(stars[i][0], stars[j][0]);
                int column = Math.min(stars[i][1], stars[j][1]);

                check(row, column);
            }
        }

        System.out.println(K - max);

    }

    static void check(int r, int c) {
        int count = 0;
        for (int[] star : stars) {
            if (star[0] >= r && star[0] <= r + L && star[1] >= c && star[1] <= c + L)
                count++;
        }

        max = Math.max(max, count);
    }

}