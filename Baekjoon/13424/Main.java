// 비밀 모임

import java.util.*;
import java.io.*;

class Main {
    static int N;
    static int M;
    static ArrayList<int[]>[] edges;
    static int friendNum;
    static List<Integer> friends;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            int min = Integer.MAX_VALUE;
            int minRoom = -1;

            edges = new ArrayList[N];
            for (int e = 0; e < N; e++) {
                edges[e] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                edges[from - 1].add(new int[] { to - 1, cost });
                edges[to - 1].add(new int[] { from - 1, cost });

            }

            friendNum = Integer.parseInt(br.readLine());
            friends = new ArrayList<>();

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < friendNum; i++) {
                friends.add(Integer.parseInt(st.nextToken()) - 1);
            }

            for (int room = 0; room < N; room++) {

                int[] dist = new int[N];

                for (int d = 0; d < N; d++) {
                    dist[d] = Integer.MAX_VALUE;
                }

                PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
                pq.add(new int[] { room, 0 });
                dist[room] = 0;

                while (!pq.isEmpty()) {
                    int[] tmp = pq.poll();
                    int f = tmp[0];
                    int cost = tmp[1];

                    if (dist[f] < cost)
                        continue;

                    for (int[] next : edges[f]) {
                        if (dist[next[0]] > next[1] + cost) {
                            dist[next[0]] = next[1] + cost;

                            pq.add(new int[] { next[0], dist[next[0]] });

                        }

                    }
                }

                int count = 0;

                for (int friend : friends) {
                    count += dist[friend];
                }

                if (min > count) {
                    min = count;
                    minRoom = room + 1;
                }

            }

            System.out.println(minRoom);
        }
    }

}