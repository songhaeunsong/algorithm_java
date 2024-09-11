import java.io.*;
import java.util.*;

class Main {
    static int[][] board;
    static int[][] visited;
    static int N;
    static int M;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int count = 0;

        board = new int[N][M];
        // visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            List<int[]> list = checkCheese(0, 0);

            if (list.size() == 0)
                break;

            for (int[] l : list) {
                board[l[0]][l[1]] = 0;
            }

            count++;
        }

        System.out.println(count);
    }

    static List<int[]> checkCheese(int r, int c) {
        Deque<int[]> dq = new ArrayDeque<>();
        List<int[]> list = new ArrayList<>();
        visited = new int[N][M];
        dq.addLast(new int[] { r, c });
        visited[r][c] = 2;

        while (!dq.isEmpty()) {
            int[] tmp = dq.pollFirst();
            int x = tmp[0];
            int y = tmp[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] == 2)
                    continue;

                if (visited[nx][ny] == 0 && board[nx][ny] == 0) { // 공기
                    visited[nx][ny] = 1;
                    dq.addLast(new int[] { nx, ny });
                    continue;

                }

                if (board[nx][ny] == 1) { // 치즈
                    visited[nx][ny]++;

                }

                if (visited[nx][ny] == 2)
                    list.add(new int[] { nx, ny });

            }

        }

        return list;

    }
}