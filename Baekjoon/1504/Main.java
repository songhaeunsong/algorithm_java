import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int E;
    static int n1;
    static int n2;
    static ArrayList<Node>[] edges;
    static int[] distFromN1Tostart;
    static int[] distFromN2Tostart;
    static int[] distFromN1ToEnd;
    static int[] distFromN2ToEnd;
    static int[] distFromN1ToN2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        edges = new ArrayList[N + 1];
        distFromN1Tostart = new int[N + 1];
        distFromN2Tostart = new int[N + 1];
        distFromN1ToEnd = new int[N + 1];
        distFromN2ToEnd = new int[N + 1];
        distFromN1ToN2 = new int[N + 1];

        Arrays.fill(distFromN1Tostart, 200000001);
        Arrays.fill(distFromN2Tostart, 200000001);
        Arrays.fill(distFromN1ToEnd, 200000001);
        Arrays.fill(distFromN2ToEnd, 200000001);
        Arrays.fill(distFromN1ToN2, 200000001);

        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            edges[from].add(new Node(to, cost));
            edges[to].add(new Node(from, cost));

        }

        st = new StringTokenizer(br.readLine());
        n1 = Integer.parseInt(st.nextToken());
        n2 = Integer.parseInt(st.nextToken());

        dijkstra(n1, 1, distFromN1Tostart);
        dijkstra(n2, 1, distFromN2Tostart);
        dijkstra(n1, N, distFromN1ToEnd);
        dijkstra(n2, N, distFromN2ToEnd);
        dijkstra(n1, n2, distFromN1ToN2);

        int min = Math.min(distFromN1Tostart[1] + distFromN2ToEnd[N], distFromN2Tostart[1] + distFromN1ToEnd[N]);

        System.out.println(min + distFromN1ToN2[n2] >= 200000001 ? -1 : min + distFromN1ToN2[n2]);

    }

    static void dijkstra(int start, int end, int[] dist) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        dist[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node n = pq.poll();
            if (n.cost > dist[n.to])
                continue;

            for (Node next : edges[n.to]) {

                if (next.cost + n.cost < dist[next.to]) {
                    dist[next.to] = next.cost + n.cost;
                    pq.add(new Node(next.to, dist[next.to]));
                }
            }

        }

    }

}

class Node {
    int to;
    int cost;

    Node(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }
}
