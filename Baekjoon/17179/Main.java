import java.util.*;
import java.io.*;

class Main {
    static int N;
    static int M;
    static int L;
    static int[] coordinates;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        coordinates = new int[M];

        for (int i = 0; i < M; i++) {
            coordinates[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < N; i++) {
            sb.append(binarySearch(Integer.parseInt(br.readLine()))).append("\n");
        }

        System.out.print(sb);
    }

    static int binarySearch(int totalCount) {
        int left = 0;
        int right = L;

        while (left <= right) {
            int mid = (left + right) / 2;

            int prev = 0;
            int count = 0;
            for (int coor : coordinates) {
                if (coor - prev >= mid && L - coor >= mid) {
                    prev = coor;
                    count++;
                }

                if (count >= totalCount) {
                    break;
                }

            }

            if (count >= totalCount)
                left = mid + 1;
            else
                right = mid - 1;
        }

        return right;
    }
}