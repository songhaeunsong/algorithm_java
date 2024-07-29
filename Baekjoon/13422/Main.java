// 도둑

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int[] input = new int[st.countTokens()];

            for (int j = 0; j < input.length; j++) {
                input[j] = Integer.parseInt(st.nextToken());
            }

            int result = slidingWindow(N, M, K, input);
            sb.append(result);
            sb.append("\n");

        }
        System.out.println(sb.deleteCharAt(sb.length() - 1).toString());
    }

    public static int slidingWindow(int N, int M, int K, int[] input) {
        int left = 0;
        int right = left + M - 1;
        int cnt = 0;
        int stealCount = 0;

        // 첫번째 값 구하기
        for (int i = 0; i <= right; i++) {
            cnt += input[i];
        }

        if (cnt < K)
            stealCount++;

        if (N == M)
            return stealCount;

        // 두번째부터
        while (left < N - 1) {
            right++;
            if (right >= N)
                right = right % N;
            cnt += input[right];
            cnt -= input[left++];

            if (cnt < K)
                stealCount++;
        }
        return stealCount;
    }
}
