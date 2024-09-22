// 마법사 상어와 비바라기

import java.util.*;
import java.io.*;

class Main {
    static int[] dx = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
    static int[] dy = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
    static int[] crossDx = { 1, 1, -1, -1 };
    static int[] crossDy = { 1, -1, 1, -1 };
    static int N;
    static int M;
    static int[][] buckets;
    static Deque<int[]> cloudy;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int total = 0;

        cloudy = new ArrayDeque<>();

        cloudy.addLast(new int[] { N - 1, 0 });
        cloudy.addLast(new int[] { N - 1, 1 });
        cloudy.addLast(new int[] { N - 2, 0 });
        cloudy.addLast(new int[] { N - 2, 1 });

        buckets = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                buckets[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            simulate(d, s);
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                total += buckets[r][c];

            }
        }
        System.out.println(total);

    }

    static void simulate(int d, int s) {
        int len = cloudy.size();
        int[][] targets = new int[len][2];
        int[][] visited = new int[N][N];

        for (int i = 0; i < len; i++) {
            int[] tmp = cloudy.poll();
            int x = (tmp[0] + (dx[d] * s) % N + N) % N;
            int y = (tmp[1] + (dy[d] * s) % N + N) % N;

            buckets[x][y]++;
            targets[i][0] = x;
            targets[i][1] = y;
            visited[x][y] = 1;
        }
        int[] counts = new int[len];

        for (int i = 0; i < len; i++) {

            int x = targets[i][0];
            int y = targets[i][1];

            for (int di = 0; di < 4; di++) {
                int nx = x + crossDx[di];
                int ny = y + crossDy[di];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N)
                    continue;
                if (buckets[nx][ny] > 0) {
                    counts[i]++;

                }
            }
        }

        for (int i = 0; i < len; i++) {
            int x = targets[i][0];
            int y = targets[i][1];

            buckets[x][y] += counts[i];

        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (buckets[r][c] >= 2 && visited[r][c] == 0) {
                    buckets[r][c] -= 2;
                    cloudy.addLast(new int[] { r, c });
                }
            }
        }
    }
}
