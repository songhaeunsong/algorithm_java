import java.io.*;
import java.util.*;

public class main {
    static int N;
    static int M;
    static int L;
    static int[] input;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        input = new int[N+1];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }
        input[N] = L;

        Arrays.sort(input);

        int left = 1; 
        int right = L;

        while(left <= right){
            int mid = (left + right) / 2;

            int prev = 0;
            int count = 0;

            for(int i = 0; i <= N; i++){
                int d = input[i] - prev;
                count += (d-1)/mid;
                if(count > M) break;
                prev = input[i];
            }
            if(count > M) left = mid + 1;
            else right = mid - 1;
        }

        System.out.println(left);
    }
}