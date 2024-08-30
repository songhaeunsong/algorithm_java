import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int k;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        k = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i += (N / k)) {
            sortArr(i, i + N / k);
        }

        System.out.println(sb);
    }

    private static void sortArr(int start, int end) {
        int[] tmp = new int[end - start];
        for (int i = start; i < end; i++) {
            tmp[i - start] = arr[i];
        }
        Arrays.sort(tmp);
        for (int i = 0; i < end - start; i++) {
            sb.append(tmp[i]).append(" ");
        }
    }

}