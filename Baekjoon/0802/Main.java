import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine().trim());
        String[] fc = new String[2];
        fc[0] = br.readLine().trim();
        fc[1] = br.readLine().trim();

        int[] input = new int[N];
        int[] output = new int[N];
        
        for (int i = 0; i < N; i++) {
            input[i] = fc[0].charAt(i) - '0';
            output[i] = fc[1].charAt(i) - '0';
        }

        int min = Integer.MAX_VALUE;
        
        int[] arr = input.clone();
        
        arr[0] = arr[0] == 1 ? 0 : 1;
        arr[1] = arr[1] == 1 ? 0 : 1;
        int result1 = simulate(arr, output, N);
        
        if (result1 != -1) min = Math.min(min, result1 + 1);
        
        int result2 = simulate(input, output, N);
        if (result2 != -1) min = Math.min(min, result2);
        
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }
    
    private static int simulate(int[] input, int[] output, int N) {
        int count = 0;
        for (int i = 0; i < N - 1; i++) {
            if (input[i] != output[i]) {
                count++;
                for (int j = i; j < i + 3; j++) {
                    if (j < N) {
                        input[j] = input[j] == 1 ? 0 : 1;
                    }
                }
            }
        }
        for (int i = 0; i < N; i++) {
            if (input[i] != output[i]) return -1;
        }
        return count;
    }
}