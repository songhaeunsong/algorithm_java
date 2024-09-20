import java.util.*;
import java.io.*;

class Main {
    static int N;
    static int[][] eggs;
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        eggs = new int[N][2];
        max = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            eggs[i][0] = Integer.parseInt(st.nextToken());
            eggs[i][1] = Integer.parseInt(st.nextToken());
        }

        backtracking(0, eggs);

        System.out.println(max);
    }

    static void backtracking(int nth, int[][] eggs) {

        if (nth == N) {
            int count = 0;
            for (int[] egg : eggs) {
                if (egg[0] <= 0)
                    count++;
            }
            max = Math.max(max, count);
            return;
        }

        if (eggs[nth][0] <= 0) {
            backtracking(nth + 1, eggs);
            return;
        }
        int conflict = 0;
        for (int i = 0; i < N; i++) {
            if (nth == i)
                continue;
            if (eggs[i][0] > 0) {
                conflict++;
                eggs[i][0] -= eggs[nth][1];
                eggs[nth][0] -= eggs[i][1];
                backtracking(nth + 1, eggs);
                eggs[i][0] += eggs[nth][1];
                eggs[nth][0] += eggs[i][1];
            }
        }
        if (conflict == 0)
            backtracking(N, eggs);
    }
}