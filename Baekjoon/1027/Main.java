import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static double[] buildings;
    static double[][] inclination;
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        buildings = new double[N];
        inclination = new double[N][N];
        st = new StringTokenizer(br.readLine());
        max = 0;
        for (int i = 0; i < N; i++) {
            buildings[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {

                double dy = buildings[i] - buildings[j];
                double dx = i - j;
                if (dy != 0) {
                    inclination[i][j] = dy / dx;
                    inclination[j][i] = dy / dx;
                }

            }
        }

        for (int i = 0; i < N; i++) {
            int count = 0;
            int left = i - 1;
            double leftIncln = Double.MAX_VALUE; // 기울기가 작아야
            while (left >= 0) {
                if (inclination[i][left] < leftIncln) {
                    count++;
                    leftIncln = inclination[i][left];
                }
                left--;
            }

            int right = i + 1;
            double rightIncln = -Double.MAX_VALUE; // 기울기가 커야
            while (right < N) {
                if (inclination[i][right] > rightIncln) {
                    count++;
                    rightIncln = inclination[i][right];
                }
                right++;
            }

            max = Math.max(max, count);
        }

        System.out.println(max);
    }
}
