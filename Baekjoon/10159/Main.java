// 저울

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static List<Integer>[] biggerNodes;
    static List<Integer>[] smallerNodes;
    static int known;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        int[] result = new int[N];

        biggerNodes = new ArrayList[N + 1];
        smallerNodes = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            biggerNodes[i] = new ArrayList<>();
            smallerNodes[i] = new ArrayList<>();
        }

        // 간선 정보 삽입
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            smallerNodes[from].add(to);
            biggerNodes[to].add(from);
        }

        // countKnownNodes(n) 호출
        for (int n = 1; n <= N; n++) {
            known = 1;
            boolean[] isVisited = new boolean[N + 1];
            isVisited[n] = true;

            countBigger(n, isVisited);
            countSmaller(n, isVisited);

            result[n - 1] = N - known;
        }

        // 출력 포맷 맞추기
        for (int i = 0; i < N; i++) {
            sb.append(result[i]);
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    // node보다 큰 노드 개수 세기
    public static void countBigger(int node, boolean[] isVisited) {
        for (int next : biggerNodes[node]) {
            if (!isVisited[next]) {
                isVisited[next] = true;
                known++;
                countBigger(next, isVisited);
            }
        }

    }

    // node보다 작은 노드 개수 세기
    public static void countSmaller(int node, boolean[] isVisited) {
        for (int next : smallerNodes[node]) {
            if (!isVisited[next]) {
                isVisited[next] = true;
                known++;
                countSmaller(next, isVisited);
            }
        }
    }

}
