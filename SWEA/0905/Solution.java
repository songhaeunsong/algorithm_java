import java.io.*;
import java.util.*;

public class Solution {
    static ArrayList<Integer>[] edges;
    static ArrayList<Integer>[] edgesReverse;
    static int T, N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int totalCount = 0;

            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());

            edges = new ArrayList[N + 1];
            edgesReverse = new ArrayList[N + 1];

            for (int i = 0; i <= N; i++) {
                edges[i] = new ArrayList<>();
                edgesReverse[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                edges[from].add(to);
                edgesReverse[to].add(from);

            }

            for (int n = 1; n <= N; n++) {
                int count = countConnected(n, edges) + countConnected(n, edgesReverse);
                if (count == N - 1)
                    totalCount++;
            }

            sb.append("#").append(tc).append(" ").append(totalCount).append("\n");
        }

        System.out.print(sb);
    }

    static int countConnected(int n, ArrayList<Integer>[] edgeList) {
        Deque<Integer> dq = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        dq.addLast(n);
        visited[n] = true;
        int count = 0;

        while (!dq.isEmpty()) {
            int node = dq.pollFirst();

            for (int next : edgeList[node]) {
                if (!visited[next]) {
                    visited[next] = true;
                    count++;
                    dq.addLast(next);
                }
            }
        }
        return count;
    }
}
