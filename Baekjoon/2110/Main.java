import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int C;
    static int[] houses;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        houses = new int[N];
        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(houses);

        int left = 0;
        int right = houses[N - 1];
        while (left <= right) {
            int mid = (left + right) / 2;
            int prevCoor = houses[0];
            int count = 1;
            for (int i = 1; i < N; i++) {
                if (houses[i] - prevCoor >= mid) {
                    count++;
                    prevCoor = houses[i];
                }

                if (count > C)
                    break;
            }

            if (count >= C)
                left = mid + 1;
            else
                right = mid - 1;
        }

        System.out.println(right);

    }
}
