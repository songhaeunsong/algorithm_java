import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            edges.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            edges.get(from).add(to);
            edges.get(to).add(from);
        }

        int count = 0;
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{1, 0, 0});

        while (!stack.isEmpty()) {
            int[] node = stack.pop();
            int n = node[0];
            int p = node[1];
            int d = node[2];

            if (n != 1 && edges.get(n).size() == 1) {
                count += d;
                continue;
            }

            for (int next : edges.get(n)) {
                if (p != next) {
                    stack.push(new int[]{next, n, d + 1});
                }
            }
        }

        System.out.println(count % 2 == 0 ? "No" : "Yes");
    }
}
